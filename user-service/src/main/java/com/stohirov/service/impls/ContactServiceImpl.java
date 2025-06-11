package com.stohirov.service.impls;

import com.stohirov.entity.Contact;
import com.stohirov.payload.ContactDTO;
import com.stohirov.repository.ContactRepository;
import com.stohirov.service.ContactService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO getContactById(Long id) {
        log.info("Fetching contact with id: {}", id);
        return entityToDTO(findByIdOrElseThrow(id));
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        log.info("Fetching all contacts");
        return contactRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Contact create(ContactDTO contactDTO) {
        log.info("Creating contact with email: {}", contactDTO.getEmail());

        if (contactRepository.existsByEmail(contactDTO.getEmail())) {
            throw new EntityExistsException("Contact with this email address already exists!");
        }

        Contact contact = dtoToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);

        log.info("Contact created successfully with id: {}", savedContact.getId());
        return savedContact;
    }

    @Transactional
    public Contact update(Long id, ContactDTO contactDTO) {
        log.info("Updating contact with id: {}", id);

        Contact existingContact = findByIdOrElseThrow(id);

        existingContact.setFirstName(contactDTO.getFirstName());
        existingContact.setLastName(contactDTO.getLastName());
        existingContact.setPrimaryPhoneNumber(contactDTO.getPrimaryPhoneNumber());
        existingContact.setSecondaryPhoneNumber(contactDTO.getSecondaryPhoneNumber());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setAbout(contactDTO.getAbout());

        Contact updatedContact = contactRepository.save(existingContact);

        log.info("Contact updated successfully with id: {}", updatedContact.getId());
        return updatedContact;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting contact with id: {}", id);

        Contact contact = findByIdOrElseThrow(id);
        contactRepository.delete(contact);

        log.info("Contact deleted successfully with id: {}", id);
    }

    private Contact findByIdOrElseThrow(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
    }

    public ContactDTO entityToDTO(Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPrimaryPhoneNumber(),
                contact.getSecondaryPhoneNumber(),
                contact.getEmail(),
                contact.getAbout()
        );
    }

    public Contact dtoToEntity(ContactDTO contactDTO) {
        return new Contact(
                contactDTO.getFirstName(),
                contactDTO.getLastName(),
                contactDTO.getPrimaryPhoneNumber(),
                contactDTO.getSecondaryPhoneNumber(),
                contactDTO.getEmail(),
                contactDTO.getAbout()
        );
    }
}
