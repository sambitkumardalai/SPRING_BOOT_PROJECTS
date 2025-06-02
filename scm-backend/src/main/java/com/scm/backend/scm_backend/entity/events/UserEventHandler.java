package com.scm.backend.scm_backend.entity.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.scm.backend.scm_backend.entity.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    private Logger log = LoggerFactory.getLogger(UserEventHandler.class);
    @HandleBeforeCreate
    public void handleUserCreate(User user) {
        log.info("Handling user creation event for user: {}", user.getEmail());
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
    }
}
