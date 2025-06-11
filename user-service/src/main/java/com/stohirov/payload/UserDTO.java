package com.stohirov.payload;

import com.stohirov.entity.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private UUID userId;

    @NotNull(message = "Please enter the birth date!")
    private Timestamp birthDate;

    @NotNull(message = "Gender cannot be null!")
    private Gender gender;

    private ContactDTO contactDTO;

}
