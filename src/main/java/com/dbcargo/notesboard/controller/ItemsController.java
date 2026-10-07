package com.dbcargo.notesboard.controller;

import com.dbcargo.notesboard.domain.dto.request.ItemRequest;
import com.dbcargo.notesboard.domain.dto.response.ItemResponse;
import com.dbcargo.notesboard.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;

    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemResponse> getItems() {
        return itemService.findAll();
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemResponse> save(@RequestBody @Valid ItemRequest request) {
        return itemService.save(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ItemResponse> updateItemStatus(@PathVariable Long id, @RequestBody @Valid ItemRequest request) {
        return itemService.update(id, request.getStatus())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
