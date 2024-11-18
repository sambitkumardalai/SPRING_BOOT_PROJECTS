package com.pollingApp.pollingApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pollingApp.pollingApp.models.Poll;
import com.pollingApp.pollingApp.repositories.PollRepository;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
         return pollRepository.findById(id);
    }
}
