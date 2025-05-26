package com.scm.backend.scm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.backend.scm_backend.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {

    // Custom query methods can be defined here if needed
    // For example, to find contacts by user:
    // List<Contact> findByUser(User user);

    // Or to find contacts by favorite status:
    // List<Contact> findByFavorite(boolean favorite);

}
