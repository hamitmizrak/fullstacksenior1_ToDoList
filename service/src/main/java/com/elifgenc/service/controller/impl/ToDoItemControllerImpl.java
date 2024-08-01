package com.elifgenc.service.controller.impl;

import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.ToDoItemController;
import com.elifgenc.service.business.dto.CreateToDoItemDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.ToDoItemDTO;
import com.elifgenc.service.business.services.ToDoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoItemControllerImpl implements ToDoItemController {
    private final ToDoItemService toDoItemService;


    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ToDoItemDTO>> getToDoItems(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(toDoItemService.getAllToDo(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<SuccessResponseDTO> createToDoItem(@Valid @RequestBody CreateToDoItemDTO createToDoItemDTO) {
        toDoItemService.createToDo(createToDoItemDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_ADD_MESSAGE.getMessage()));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> updateToDoItem(@PathVariable(name = "id")Long id, @Valid @RequestBody CreateToDoItemDTO updateToDoItemDTO) {
        toDoItemService.updateToDo(id, updateToDoItemDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_EDIT_MESSAGE.getMessage()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteToDoItem(@PathVariable(name = "id")Long id) {
        toDoItemService.deleteToDo(id);
        return ResponseEntity.ok(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_DELETE_MESSAGE.getMessage()).build());

    }
}
