package com.example.todoapp.service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Lấy tất cả Todo
    public List<Todo> getAllTodos() {
        try {
            return todoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all todos: " + e.getMessage());
        }
    }

    // Lấy Todo theo id
    public Todo getTodoById(Long id) {
        try {
            return todoRepository.findById(id)
                    .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching todo with id " + id + ": " + e.getMessage());
        }
    }

    // Lưu Todo mới
    public Todo saveTodo(Todo todo) {
        try {
            return todoRepository.save(todo);
        } catch (Exception e) {
            throw new RuntimeException("Error saving todo: " + e.getMessage());
        }
    }

    // Xóa Todo theo id
    public void deleteTodoById(Long id) {
        try {
            todoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting todo with id " + id + ": " + e.getMessage());
        }
    }

    // Cập nhật Todo
    public Todo updateTodo(Todo todo) {
        try {
            if (todoRepository.existsById(todo.getId())) {
                return todoRepository.save(todo);
            } else {
                throw new TodoNotFoundException("Todo not found with id: " + todo.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating todo with id " + todo.getId() + ": " + e.getMessage());
        }
    }
}
