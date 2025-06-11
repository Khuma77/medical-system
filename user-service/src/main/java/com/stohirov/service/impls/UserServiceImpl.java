package com.stohirov.service.impls;

import com.stohirov.entity.Contact;
import com.stohirov.entity.User;
import com.stohirov.payload.ContactDTO;
import com.stohirov.payload.UserDTO;
import com.stohirov.repository.UserRepository;
import com.stohirov.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactServiceImpl contactService;

    public UserServiceImpl(UserRepository userRepository, ContactServiceImpl contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO, UUID userId) {
        log.info("Creating user with userId: {}", userId);

        Contact contact = contactService.create(userDTO.getContactDTO());
        User user = new User(userId, userDTO.getBirthDate(), userDTO.getGender(), contact);

        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());

        return entityToDTO(savedUser);
    }

    @Override
    public UserDTO getUser(UUID userId) {
        log.info("Fetching user with userId: {}", userId);

        User user = getUserByUserId(userId);
        return entityToDTO(user);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        log.info("Updating user with userId: {}", userId);

        User user = getUserByUserId(userId);

        // Update user's fields
        user.setBirthDate(userDTO.getBirthDate());
        user.setGender(userDTO.getGender());

        // Update contact
        ContactDTO updatedContactDTO = userDTO.getContactDTO();
        Contact updatedContact = contactService.update(user.getContact().getId(), updatedContactDTO);

        user.setContact(updatedContact);

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with id: {}", updatedUser.getId());

        return entityToDTO(updatedUser);
    }

    @Transactional
    @Override
    public boolean delete(UUID userId) {
        log.info("Deleting user with userId: {}", userId);

        User user = getUserByUserId(userId);

        contactService.delete(user.getContact().getId());

        userRepository.delete(user);
        log.info("User deleted successfully with id: {}", user.getId());

        return true;
    }

    private User getUserByUserId(UUID userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + userId));
    }

    private UserDTO entityToDTO(User user) {
        ContactDTO contact = contactService.getContactById(user.getContact().getId());
        return new UserDTO(
                user.getId(),
                user.getUserId(),
                user.getBirthDate(),
                user.getGender(),
                contact
        );
    }
}
