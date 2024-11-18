package com.pollingApp.pollingApp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollingApp.pollingApp.models.Poll;
import com.pollingApp.pollingApp.services.PollService;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollservice;

    public PollController(PollService pollservice) {
        this.pollservice = pollservice;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {

        return pollservice.createPoll(poll);
    }

}
