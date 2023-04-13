package com.api.taskcontrol.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.api.taskcontrol.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TASKS")
public class TaskEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime creationDate;
    @Column
    private LocalDateTime conclusionDate;
    @Column
    private Status status;
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getConclusionDate() {
        return conclusionDate;
    }
    public void setConclusionDate(LocalDateTime conclusionDate) {
        this.conclusionDate = conclusionDate;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
