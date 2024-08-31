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

 @Entity
 @Data
 public class Task {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String title;

     @Column(columnDefinition = "TEXT")
     private String description;

     private LocalDate dueDate;

     @Enumerated(EnumType.STRING)
     private Priority priority;

     private boolean completed;

     enum Priority {
         LOW, MEDIUM, HIGH

     }
 }
