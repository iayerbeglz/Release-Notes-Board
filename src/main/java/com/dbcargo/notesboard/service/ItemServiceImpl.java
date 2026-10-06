package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.request.ItemRequest;
import com.dbcargo.notesboard.domain.dto.response.ItemResponse;
import com.dbcargo.notesboard.domain.entities.ItemEntity;
import com.dbcargo.notesboard.domain.exception.DuplicatedItemException;
import com.dbcargo.notesboard.domain.exception.ItemNotFoundException;
import com.dbcargo.notesboard.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;

    @Override
    public List<ItemResponse> findAll() {
        return itemsRepository.findAll().stream()
                .map(entity -> ItemResponse.builder().release(entity.getRelease()).description(entity.getDescription()).status(entity.getStatus()).build())
                .toList();
    }

    @Override
    public Optional<ItemResponse> save(ItemRequest request) {
        ItemEntity item = itemsRepository.findByRelease(request.getRelease());
        if (item != null) {
            throw new DuplicatedItemException(String.format("Item %s already exist in database", item.getRelease()));
        }

        item = itemsRepository.save(ItemEntity.builder().release(request.getRelease()).description(request.getDescription()).status(request.getStatus()).build());
        return Optional.of(ItemResponse.builder().release(item.getRelease()).description(item.getDescription()).status(item.getStatus()).build());
    }

    @Override
    public Optional<ItemResponse> update(Long id, String status) {
        return Optional.ofNullable(itemsRepository.findById(id)
                .map(entity -> {
                    entity.setStatus(status);
                    ItemEntity item = itemsRepository.save(entity);
                    return ItemResponse.builder().release(item.getRelease()).description(item.getDescription()).status(item.getStatus()).build();
                })
                .orElseThrow(() -> new ItemNotFoundException()));
    }
}
