package com.api.taskcontrol.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.taskcontrol.entities.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID>{

}
