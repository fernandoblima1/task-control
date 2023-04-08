package com.api.taskcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.taskcontrol.entities.TaskEntity;
import com.api.taskcontrol.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity save(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    public Page<TaskEntity> findAll(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

}
