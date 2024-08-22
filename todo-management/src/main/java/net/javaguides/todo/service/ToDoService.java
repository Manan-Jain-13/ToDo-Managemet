package net.javaguides.todo.service;

import net.javaguides.todo.dto.ToDoDTO;

import java.util.List;

public interface ToDoService {

    ToDoDTO addTodo(ToDoDTO todoDTO);

    ToDoDTO getTodo(Long id);

    List<ToDoDTO> getAllTodos();

    ToDoDTO updateTodo(ToDoDTO todoDTO, Long id);

    void deleteTodo(Long id);

    ToDoDTO completeTodo(Long id);

    ToDoDTO InCompleteTodo(Long id);
}
