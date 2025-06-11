package com.stohirov.controllers;

import com.stohirov.payload.ContactDTO;
import com.stohirov.service.impls.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable("id") Long id) {
        return contactService.getContactById(id);
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        return contactService.entityToDTO(contactService.create(contactDTO));
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable("id") Long id, @RequestBody ContactDTO contactDTO) {
        return contactService.entityToDTO(contactService.update(id, contactDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") Long id) {
        contactService.delete(id);
    }
}
