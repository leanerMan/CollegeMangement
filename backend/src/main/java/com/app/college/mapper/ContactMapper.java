package com.app.college.mapper;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.ContactDtoRequest;
import com.app.college.models.Contact;
import com.app.college.util.DateTimeGenerator;

@Component
public class ContactMapper {
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;

	public Contact toEntity(ContactDtoRequest contactDtoRequest, MultipartFile contactImageFile) {
		Contact contact = new Contact();
		contact.setName(contactDtoRequest.getName());
		contact.setAddress(contactDtoRequest.getAddress());
		contact.setBirthDate(contactDtoRequest.getBirthDate());
		contact.setMobile(contactDtoRequest.getMobile());
		contact.setEmail(contactDtoRequest.getEmail());
		contact.setNote(contactDtoRequest.getNote());
		contact.setCreatedAt(DateTimeGenerator.getAutoLocalDateTime());
		try {
			if (contactImageFile != null && !contactImageFile.isEmpty()
					&& !contactImageFile.getOriginalFilename().isEmpty()) {
				// Create a temporary file
				File tempFile = File.createTempFile("temp", null);
				contactImageFile.transferTo(tempFile);
				File imageFile = tempFile;
				tempFile.deleteOnExit();
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(contactImageFile.getContentType());
				int lastDotIndex = contactImageFile.getOriginalFilename().lastIndexOf('.');
				String fileExtension = lastDotIndex == -1 ? ""
						: contactImageFile.getOriginalFilename().substring(lastDotIndex + 1);
				String filename = System.currentTimeMillis() + "Cprofile." + fileExtension;
				String key = "contacts/" + filename;
				contact.setContactImageName(filename);
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
				putObjectRequest.setMetadata(metadata);
				if (contact != null) {
					// Upload the image to S3
					s3Client.putObject(putObjectRequest);
				}
				imageFile.delete();
			} else {
				contact.setContactImageName(contactDtoRequest.getContactImageName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return contact;
	}
	
	
	public ContactDtoRequest toDto(Contact contact) {
		ContactDtoRequest contactDtoRequest= new ContactDtoRequest();
		contactDtoRequest.setId(contact.getId());
		contactDtoRequest.setName(contact.getName());
		contactDtoRequest.setAddress(contact.getAddress());
		contactDtoRequest.setBirthDate(contact.getBirthDate());
		contactDtoRequest.setEmail(contact.getEmail());
		contactDtoRequest.setMobile(contact.getMobile());
		contactDtoRequest.setContactImageName(contact.getContactImageName());
		contactDtoRequest.setNote(contact.getNote());
		return contactDtoRequest;
	}
}
