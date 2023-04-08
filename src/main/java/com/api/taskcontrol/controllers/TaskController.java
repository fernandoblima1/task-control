package com.api.taskcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import com.api.taskcontrol.dtos.TaskDto;
import com.api.taskcontrol.entities.TaskEntity;
import com.api.taskcontrol.services.TaskService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskDto taskDto){
        var taskEntity = new TaskEntity();
        BeanUtils.copyProperties(taskDto, taskEntity);
        taskEntity.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskEntity));
    }

    @GetMapping()
    public ResponseEntity<Page<TaskEntity>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(pageable));
    }

    // @GetMapping()
}
