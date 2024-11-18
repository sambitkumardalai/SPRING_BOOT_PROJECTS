package com.pollingApp.pollingApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pollingApp.pollingApp.models.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    @Query(value = "SELECT * FROM poll limit 1", nativeQuery = true)
    List<Poll> findAllPolls(); // Custom method using SQL

}
