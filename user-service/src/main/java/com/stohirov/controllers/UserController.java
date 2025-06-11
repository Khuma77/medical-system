package com.stohirov.controllers;

import com.stohirov.payload.UserDTO;
import com.stohirov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/profile/{user-id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('admin', 'role_doctor', 'role_nurse', 'user')")
    public UserDTO createUser(@RequestBody UserDTO userDTO,
                              @PathVariable("user-id") UUID userId) {
        return userService.createUser(userDTO, userId);
    }

    @GetMapping("/profile/{user-id}")
    @PreAuthorize("hasAnyRole('admin', 'role_doctor', 'role_nurse', 'user')")
    public UserDTO getUser(@PathVariable("user-id") UUID userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/profile/{user-id}")
    @PreAuthorize("hasAnyRole('admin', 'role_doctor', 'role_nurse', 'user')")
    public UserDTO updateUser(@PathVariable("user-id") UUID userId,
                              @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("/profile/{user-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('admin', 'role_doctor', 'role_nurse', 'user')")
    public void deleteUser(@PathVariable("user-id") UUID userId) {
        userService.delete(userId);
    }
}
