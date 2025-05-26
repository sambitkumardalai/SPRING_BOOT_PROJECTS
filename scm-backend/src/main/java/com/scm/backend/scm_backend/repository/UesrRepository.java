package com.scm.backend.scm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.backend.scm_backend.entity.User;

public interface UesrRepository extends  JpaRepository<User, String> {

}
