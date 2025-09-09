package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;

public abstract class AbstractModel<T> {
    private T id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters e Setters
    public T getId() { return id; }
    public void setId(T id) { this.id = id; }
    //...

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}