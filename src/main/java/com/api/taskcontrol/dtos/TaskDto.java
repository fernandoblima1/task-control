package com.api.taskcontrol.dtos;

import com.api.taskcontrol.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDto {

    @NotBlank
    @Size(max = 70)
    private String title;
    @NotBlank
    @Size(max = 126)
    private String description;
    @NotNull
    private Status status;
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
