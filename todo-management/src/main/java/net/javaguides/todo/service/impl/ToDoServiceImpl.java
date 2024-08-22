package net.javaguides.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.ToDoDTO;
import net.javaguides.todo.entity.ToDo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.ToDoRepository;
import net.javaguides.todo.service.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public ToDoDTO addTodo(ToDoDTO todoDTO) {

//      Convert ToDo entity into Todo JPA
        ToDo todo=modelMapper.map(todoDTO,ToDo.class);

        // ToDo JPA entity
        ToDo savedTodo= todoRepository.save(todo);

        // Convert savedToDo jpa entity object into ToDoDto object
        ToDoDTO savedTodoDto=modelMapper.map(savedTodo,ToDoDTO.class);
        return savedTodoDto;
    }

    @Override
    public ToDoDTO getTodo(Long id) {
        ToDo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:"+id)) ;
        return modelMapper.map(todo,ToDoDTO.class);
    }

    @Override
    public List<ToDoDTO> getAllTodos() {

        List<ToDo> todos=todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo,ToDoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDTO updateTodo(ToDoDTO todoDTO, Long id) {
        ToDo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:"+id));
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        ToDo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,ToDoDTO.class);
    }

    @Override
    public void deleteTodo(Long id) {
        ToDo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public ToDoDTO completeTodo(Long id) {

        ToDo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id"+id));
        todo.setCompleted(Boolean.TRUE);

        ToDo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,ToDoDTO.class);
    }

    @Override
    public ToDoDTO InCompleteTodo(Long id) {
        ToDo todo=todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id"+id));
        todo.setCompleted(Boolean.FALSE);

        ToDo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo,ToDoDTO.class);
    }


}
