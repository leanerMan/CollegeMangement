package com.app.college.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.college.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("select c from Contact c where LOWER(c.name) LIKE %:keyword% OR LOWER(c.email) LIKE %:keyword")
	Page<Contact> searchContact(Pageable pageable, String keyword);

}
