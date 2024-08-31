/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.JonesInForCode.task_management_api;

/**
 *
 * @author JonesInForCode
 */

 import jakarta.persistence.*;
 import lombok.Data;
 import java.time.LocalDate;
 import jakarta.validation.constraints.FutureOrPresent;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.Size;

 @Entity
 @Data
 public class Task {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotBlank(message = "Title is required")
     @Size(max = 255, message = "Title must be between 1 and 255 characters")
     @Column(nullable = false)
     private String title;

     @Column(columnDefinition = "TEXT")
     private String description;

    @FutureOrPresent(message = "Due date must be in the future or present")
     private LocalDate dueDate;

     @Enumerated(EnumType.STRING)
     private Priority priority;

     private boolean completed;

     enum Priority {
         LOW, MEDIUM, HIGH

     }
 }
