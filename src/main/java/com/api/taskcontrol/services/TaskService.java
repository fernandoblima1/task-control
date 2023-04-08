package com.api.taskcontrol.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.taskcontrol.entities.TaskEntity;
import com.api.taskcontrol.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public TaskEntity save(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    public Page<TaskEntity> findAll(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    public Object count() {
        return taskRepository.count();
    }

    @Transactional
    public void deleteAll(){
        taskRepository.deleteAll();
    }

    @Transactional
    public void deleteById(UUID id){
        taskRepository.deleteById(id);
    }

    public Optional<TaskEntity> findById(UUID id) {
        return taskRepository.findById(id);
    }

}
