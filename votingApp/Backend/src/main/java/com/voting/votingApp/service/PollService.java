package com.voting.votingApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.voting.votingApp.model.OptionVote;
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

    public List<Poll> getAllPoll() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public Optional<Poll> vote(Long pollId, int optionIndex) {
        Poll pollData = pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found"));
        System.out.println("================" + pollData);
        List<OptionVote> options = pollData.getOptions();
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index");
        }
        OptionVote selectedOption = options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        pollRepository.saveAndFlush(pollData);
        Optional<Poll> newPollData = pollRepository.findById(pollId);
        return newPollData;
    }
}
