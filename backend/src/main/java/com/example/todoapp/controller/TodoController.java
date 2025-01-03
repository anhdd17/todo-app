package com.example.todoapp.controller;

import com.example.todoapp.exception.TodoNotFoundException;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = Optional.ofNullable(todoService.getTodoById(id));
        return todo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.saveTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        todoDetails.setId(id); // Gán ID từ đường dẫn vào đối tượng todoDetails
        try {
            Todo updatedTodo = todoService.updateTodo(todoDetails); // Cập nhật todo
            return ResponseEntity.ok(updatedTodo); // Trả về todo đã cập nhật
        } catch (TodoNotFoundException ex) {
            return ResponseEntity.notFound().build(); // Trả về lỗi nếu không tìm thấy todo
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Trả về lỗi nếu có vấn đề khác
        }
    }



}
