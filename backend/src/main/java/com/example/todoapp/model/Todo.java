package com.example.todoapp.model;

import jakarta.persistence.*;

@Entity // Đánh dấu lớp này là một thực thể JPA
@Table(name = "todos") // Ánh xạ với bảng "todos" trong cơ sở dữ liệu
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    private Long id;

    @Column(nullable = false) // Cột "title" không được để null
    private String title;

    @Column(length = 500) // Giới hạn độ dài cho mô tả
    private String description;

    @Column(nullable = false) // Mặc định không được null
    private boolean completed;

    // Constructor không tham số (yêu cầu của JPA)
    public Todo() {
    }

    // Constructor có tham số
    public Todo(Long id, String title, boolean completed, String description) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
