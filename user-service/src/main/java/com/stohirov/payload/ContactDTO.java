package com.stohirov.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    private Long id;

    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @NotBlank(message = "Primary phone number cannot be empty!")
    @Pattern(
            regexp = "^\\+?[0-9]{9,15}$",
            message = "Invalid phone number format. Use 9 to 15 digits, optional '+' at start."
    )
    private String primaryPhoneNumber;

    @Pattern(
            regexp = "^\\+?[0-9]{9,15}$",
            message = "Invalid phone number format. Use 9 to 15 digits, optional '+' at start."
    )
    private String secondaryPhoneNumber;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Invalid email format!")
    private String email;

    private String about;
}
