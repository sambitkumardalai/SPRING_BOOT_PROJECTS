package com.voting.votingApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.votingApp.model.Poll;
import com.voting.votingApp.service.PollService;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public String createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.createPoll(poll);
        return "Poll created with ID: " + createdPoll.getId();
    }

}
