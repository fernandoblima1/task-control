package com.api.taskcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import com.api.taskcontrol.dtos.TaskDto;
import com.api.taskcontrol.entities.TaskEntity;
import com.api.taskcontrol.enums.Status;
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
        if(taskEntity.getStatus() == Status.COMPLETED){
            taskEntity.setConclusionDate(LocalDateTime.now(ZoneId.of("UTC")));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskEntity));
    }

    @GetMapping
    public ResponseEntity<Page<TaskEntity>> getAllTasks(@PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") UUID id){
        Optional<TaskEntity> taskEntityOptional = taskService.findById(id);
        if(!taskEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The task was not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskEntityOptional);
    }

    @GetMapping(path = "/summary")
    public ResponseEntity<Object> getSummary(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.count());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody @Valid TaskDto taskDto, @PathVariable UUID id){
        Optional<TaskEntity> taskEntityOptional = taskService.findById(id);
        if(!taskEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: The task was not found.");
        }
        taskEntityOptional.get().setTitle(taskDto.getTitle());
        taskEntityOptional.get().setDescription(taskDto.getDescription());
        taskEntityOptional.get().setStatus(taskDto.getStatus());
        if(taskEntityOptional.get().getStatus() == Status.COMPLETED && taskEntityOptional.get().getConclusionDate() == null){
            taskEntityOptional.get().setConclusionDate(LocalDateTime.now(ZoneId.of("UTC")));
        }
        if(!(taskEntityOptional.get().getStatus() == Status.COMPLETED) && !(taskEntityOptional.get().getConclusionDate() == null)){
            taskEntityOptional.get().setConclusionDate(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskEntityOptional.get()));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") UUID id){
        Optional<TaskEntity> taskEntityOptional = taskService.findById(id);
        if(!taskEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: The task was not found.");
        }
        taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success: The task was deleted.");
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllTasks(){
        taskService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Conflict: All tasks was deleted.");
    }

}
