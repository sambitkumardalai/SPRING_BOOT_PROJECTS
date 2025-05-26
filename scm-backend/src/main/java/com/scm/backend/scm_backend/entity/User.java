package com.scm.backend.scm_backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)    
    private String email;
    private String password;
    @Lob
    private String about;
    @Column(length=1000)
    private String profilePicture;
    private String phoneNumber;
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneNumberVerified=false;

    @Enumerated(value=EnumType.STRING)
    private Providers provider = Providers.SELF;

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
} 
