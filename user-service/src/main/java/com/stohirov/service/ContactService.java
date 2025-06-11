package com.stohirov.service;

import com.stohirov.entity.Contact;
import com.stohirov.payload.ContactDTO;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    ContactDTO getContactById(Long id);

    List<ContactDTO> getAllContacts();

    Contact create(ContactDTO contactDTO);

}
