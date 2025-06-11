package com.stohirov.service;

import com.stohirov.payload.UserDTO;

import java.util.UUID;

public interface UserService {
    UserDTO createUser(UserDTO userDTO, UUID userId);

    UserDTO getUser(UUID userId);

    UserDTO updateUser(UUID userId, UserDTO userDTO);

    boolean delete(UUID userId);
}
