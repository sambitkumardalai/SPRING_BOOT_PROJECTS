package com.voting.votingApp.service;

import org.springframework.stereotype.Service;

import com.voting.votingApp.model.Poll;
import com.voting.votingApp.repository.PollRepository;

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
