package com.app.todoapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todoapp.model.Task;
import com.app.todoapp.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void createTask(String title) {
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setCompleted(false);
        taskRepo.save(newTask);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task taskData = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskData.setCompleted(!taskData.isCompleted());
        taskRepo.save(taskData);
    }

}
