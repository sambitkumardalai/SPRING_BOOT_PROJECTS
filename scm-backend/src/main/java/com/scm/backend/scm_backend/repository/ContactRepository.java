package com.scm.backend.scm_backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.scm.backend.scm_backend.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @RestResource(path = "by-email")
    List<Contact> findByEmailContaining(@Param("email") String email, Pageable pageable);

    @RestResource(path = "by-name")
    List<Contact> findByNameContaining(@Param("name") String name, Pageable pageable);

    @RestResource(path = "by-phone")
    List<Contact> findByPhoneNumberContaining(@Param("phone") String phoneNumber, Pageable pageable);

}
