package com.app.college.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.ContactDtoRequest;
import com.app.college.models.Contact;

import jakarta.validation.Valid;

public interface ContactService {

	ContactDtoRequest createContacts( @Valid ContactDtoRequest contactDtoRequest, MultipartFile contactImageFile);

	ContactDtoRequest updateContactById(Long id, @Valid ContactDtoRequest contactDtoRequest, MultipartFile contactImageFile);

	String deleteContactById(Long id);

	Page<ContactDtoRequest> getAllContact(Pageable pageable);

	Page<Contact> searchContact(Pageable pageable, String keyword);

	ContactDtoRequest getContactById(Long id);


}
