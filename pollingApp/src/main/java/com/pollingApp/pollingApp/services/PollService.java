package com.pollingApp.pollingApp.services;

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
}
