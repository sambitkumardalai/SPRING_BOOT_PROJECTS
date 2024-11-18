package com.pollingApp.pollingApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollingApp.pollingApp.models.Poll;
import com.pollingApp.pollingApp.services.PollService;

import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollservice.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollservice.getPollById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
