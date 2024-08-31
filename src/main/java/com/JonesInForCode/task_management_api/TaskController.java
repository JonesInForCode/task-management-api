/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.JonesInForCode.task_management_api;

/**
 *
 * @author Shadow
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
        Task createdTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setTitle(taskDetails.getTitle());
                task.setDescription(taskDetails.getDescription());
                task.setDueDate(taskDetails.getDueDate());
                task.setPriority(taskDetails.getPriority());
                task.setCompleted(taskDetails.isCompleted());
                Task updatedTask = taskRepository.save(task);
                return ResponseEntity.ok(updatedTask);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
            } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> toggleTaskCompletion(@PathVariable Long id) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setCompleted(!task.isCompleted());
                Task updatedTask = taskRepository.save(task);
                return ResponseEntity.ok(updatedTask);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
