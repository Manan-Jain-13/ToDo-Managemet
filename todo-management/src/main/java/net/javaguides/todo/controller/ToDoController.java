package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.ToDoDTO;
import net.javaguides.todo.entity.ToDo;
import net.javaguides.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {

    private ToDoService todoService;

    // Build Add ToDo Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDTO> addToDo(@RequestBody ToDoDTO todoDto){
        ToDoDTO savedTodo=todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<ToDoDTO> getTodo(@PathVariable("id") Long todoId){
        ToDoDTO todoDTO=todoService.getTodo(todoId);
         return new ResponseEntity<>(todoDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ToDoDTO>> getAllTodos(){
        List<ToDoDTO> todos=todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ToDoDTO> updateTodo(@RequestBody ToDoDTO todoDto,@PathVariable("id") Long todoId){
        ToDoDTO updatedTodo=todoService.updateTodo(todoDto,todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDTO> completeTodo(@PathVariable("id") Long todoId){
        ToDoDTO updatedTodo=todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<ToDoDTO> InCompleteTodo(@PathVariable("id") Long todoId){
        ToDoDTO updatedTodo=todoService.InCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}
