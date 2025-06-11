package stohirov.dev.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import stohirov.dev.template.RegistrationDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-client-id}")
    private String adminClientId;

    @Value("${keycloak.admin-client-secret}")
    private String adminClientSecret;

    private final WebClient webClient = WebClient.create();

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO userDto) {
        log.info("Entered the /api/register!");
        // 1. Get admin token
        String adminToken = getAdminAccessToken();

        log.info("Got the token: {}", adminToken);

        if (adminToken == null) {
            log.warn("Token not found!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get admin access token from Keycloak");
        }

        log.info("Creating user in Keycloak: {}", userDto);
        // 2. Create user in Keycloak
        boolean success = createUserInKeycloak(adminToken, userDto);

        log.info("User created: {}", success);

        if (success) {
            return ResponseEntity.ok("User created successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create user in Keycloak");
        }
    }

    private String getAdminAccessToken() {
        log.info("Getting the admin access token!");
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token",
                keycloakServerUrl, realm);

        log.info("Token url for access token: {}", tokenUrl);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", adminClientId);
        formData.add("client_secret", adminClientSecret);

        log.info("Form data for auth: {}", formData);

        try {

            String rawResponse = webClient.post()
                    .uri(tokenUrl)
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Token endpoint response: {}", rawResponse);

            Map<String, Object> response = webClient.post()
                    .uri(tokenUrl)
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            log.info("Token response: {}", response);

            if (response != null && response.containsKey("access_token")) {
                return (String) response.get("access_token");
            }
        } catch (Exception e) {
            log.warn(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private boolean createUserInKeycloak(String adminToken, RegistrationDTO userDto) {
        String createUserUrl = String.format("%s/admin/realms/%s/users",
                keycloakServerUrl, realm);

        log.info("Creating user in {}", createUserUrl);

        Map<String, Object> userPayload = Map.of(
                "username", userDto.getUsername(),
                "email", userDto.getEmail(),
                "enabled", true,
                "firstName", userDto.getFirstName(),
                "lastName", userDto.getLastName(),
                "credentials", Collections.singletonList(
                        Map.of(
                                "type", "password",
                                "value", userDto.getPassword(),
                                "temporary", false
                        )
                )
        );

        log.info("User payload: {}", userDto.getUsername());

        try {
            ResponseEntity<Void> response = webClient.post()
                    .uri(createUserUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(userPayload)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            log.info("Response creating a user: {}", response);

            return response != null && response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.warn(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return false;
    }
}
