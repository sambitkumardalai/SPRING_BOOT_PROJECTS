package com.pollingApp.pollingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pollingApp.pollingApp.models.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

}
