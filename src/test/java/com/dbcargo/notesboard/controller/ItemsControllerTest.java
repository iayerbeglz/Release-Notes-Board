package com.dbcargo.notesboard.controller;

import com.dbcargo.notesboard.domain.dto.request.ItemRequest;
import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;
import com.dbcargo.notesboard.domain.dto.response.ItemResponse;
import com.dbcargo.notesboard.service.ApplicationService;
import com.dbcargo.notesboard.service.ItemServiceImpl;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemsControllerTest {

    @Mock
    private ItemServiceImpl itemService;

    @InjectMocks
    private ItemsController itemsController;

    @Test
    void testGetAllItemsOK() {
        when(itemService.findAll()).thenReturn(List.of(ItemResponse.builder().release("Note1").status("DRAFT").build()));

        List<ItemResponse> result = itemsController.getItems();

        assertNotNull(result);
        assertFalse(result.isEmpty());

    }

    @Test
    void testSaveOK() {
        ItemRequest request = new ItemRequest();
        request.setRelease("Note1");
        request.setStatus("DRAFT");

        when(itemService.save(any())).thenReturn(Optional.of(ItemResponse.builder().release("Note1").status("DRAFT").build()));

        ResponseEntity<ItemResponse> result = itemsController.save(request);

        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), result.getStatusCode());
    }

    @Test
    void testUpdateOK() {
        ItemRequest request = new ItemRequest();
        request.setRelease("Note1");
        request.setStatus("DRAFT");

        when(itemService.update(any(), any())).thenReturn(Optional.of(ItemResponse.builder().release("Note1").status("DRAFT").build()));

        ResponseEntity<ItemResponse> result = itemsController.updateItemStatus(1l, request);

        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), result.getStatusCode());
    }
}
