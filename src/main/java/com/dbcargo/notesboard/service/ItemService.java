package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.request.ItemRequest;
import com.dbcargo.notesboard.domain.dto.response.ItemResponse;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<ItemResponse> findAll();

    Optional<ItemResponse> save(ItemRequest item);

    Optional<ItemResponse> update(Long id, String status);
}
