package com.app.college.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.ContactDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.ContactMapper;
import com.app.college.models.Contact;
import com.app.college.repository.ContactRepository;
import com.app.college.service.ContactService;
import com.app.college.util.DateTimeGenerator;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;


	@Autowired
	private ContactMapper contactMapper;

	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
	private String bucketName;

	@Override
	public ContactDtoRequest createContacts(ContactDtoRequest contactDtoRequest, MultipartFile contactImageFile) {
		Contact contact = contactRepo.save(contactMapper.toEntity(contactDtoRequest, contactImageFile));
		return contactMapper.toDto(contact);
	}

	@Override
	public ContactDtoRequest updateContactById(Long id, ContactDtoRequest contactDtoRequest,
			MultipartFile contactImageFile) {
		Contact contact = contactRepo.findById(id)
				.orElseThrow(() -> new ConfigDataNotFound("No Contact found with id: " + id));
		BeanUtils.copyProperties(contactDtoRequest, contact, "profilePhoto");
		try {
			if (contactImageFile != null && !contactImageFile.isEmpty()) {
				File tempFile = File.createTempFile("temp", null);
				tempFile.deleteOnExit();
				contactImageFile.transferTo(tempFile);
				File imageFile = tempFile;
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(contactImageFile.getContentType());
				String fileName = contactDtoRequest.getContactImageName();
				String key = "contacts/" + fileName;
				contact.setContactImageName(fileName);
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
				putObjectRequest.setMetadata(metadata);
				contact.setUpdatedAt(DateTimeGenerator.getAutoLocalDateTime());
				contactRepo.save(contact);
				s3Client.putObject(putObjectRequest);
				imageFile.delete();
			} else {
				contact.setUpdatedAt(DateTimeGenerator.getAutoLocalDateTime());
				contactRepo.save(contact);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error occurred while saving profile photo!!!", e);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return contactMapper.toDto(contact);
	}
	

	@Override
	public ContactDtoRequest getContactById(Long id) {
		Contact contact = contactRepo.findById(id).orElseThrow(()-> new ConfigDataNotFound("No Contact found with Id: "+id));
		return contactMapper.toDto(contact);
	}

	@Override
	public String deleteContactById(Long id) {
		if (contactRepo.existsById(id)) {
			contactRepo.deleteById(id);
			return "Contact deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Contact found with id: " + id);
	}

	@Override
	public Page<ContactDtoRequest> getAllContact(Pageable pageable) {
		Page<Contact> pageOfContacts = contactRepo.findAll(pageable);
		List<ContactDtoRequest> newDtoList = new ArrayList<>();
		for (Contact contact : pageOfContacts.getContent()) {
			newDtoList.add(contactMapper.toDto(contact));
		}
		return new PageImpl<>(newDtoList, pageable, pageOfContacts.getTotalElements());
	}

	@Override
	public Page<Contact> searchContact(Pageable pageable, String keyword) {
		String keywordLowerCase = keyword != null ? keyword.toLowerCase() : null;
		Page<Contact> contacts = contactRepo.searchContact(pageable, keywordLowerCase);
		return contacts;
	}

}
