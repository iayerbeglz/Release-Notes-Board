package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.request.ItemRequest;
import com.dbcargo.notesboard.domain.dto.response.ItemResponse;
import com.dbcargo.notesboard.domain.entities.ApplicationEntity;
import com.dbcargo.notesboard.domain.entities.ItemEntity;
import com.dbcargo.notesboard.domain.exception.DuplicatedItemException;
import com.dbcargo.notesboard.domain.exception.ItemNotFoundException;
import com.dbcargo.notesboard.repository.ApplicationsRepository;
import com.dbcargo.notesboard.repository.ItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @Mock
    private ItemsRepository itemsRepository;

    @Mock
    private ApplicationsRepository applicationsRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void findAll() {
        when(itemsRepository.findAll())
                .thenReturn(List.of(
                        ItemEntity.builder().release("Note1").status("DRAFT").application(ApplicationEntity.builder().build()).build(),
                        ItemEntity.builder().release("Note2").status("PREVIEW").application(ApplicationEntity.builder().build()).build()));

        assertNotNull(itemService.findAll());
    }

    @Test
    void testSaveOk() {
        ItemRequest request = new ItemRequest();
        request.setRelease("Note1");
        request.setStatus("DRAFT");

        ApplicationEntity application = ApplicationEntity.builder().name("App1").build();
        when(applicationsRepository.findById(any())).thenReturn(Optional.of(application));
        when(itemsRepository.findByRelease(anyString())).thenReturn(null);
        when(itemsRepository.save(any())).thenReturn(ItemEntity.builder().release("Note1").status("DRAFT").application(application).build());

        Optional<ItemResponse> result = itemService.save(request);
        assertTrue(result.isPresent());
        assertEquals("DRAFT", result.get().getStatus());
    }

    @Test
    void testSaveKO_duplicatedItem() {
        ItemRequest request = new ItemRequest();
        request.setRelease("Note1");
        request.setStatus("DRAFT");

        when(itemsRepository.findByRelease(anyString())).thenReturn(ItemEntity.builder().release("Note1").status("DRAFT").build());

        assertThrows(DuplicatedItemException.class, () -> itemService.save(request));
    }

    @Test
    void testUpdateOk() {
        when(itemsRepository.findById(any()))
                .thenReturn(Optional.ofNullable(ItemEntity.builder().release("Note1").status("DRAFT").build()));
        when(itemsRepository.save(any())).thenReturn(ItemEntity.builder().release("Note1").status("REVIEW").build());

        Optional<ItemResponse> result = itemService.update(1l, "DRAFT");
        assertTrue(result.isPresent());
        assertEquals("REVIEW", result.get().getStatus());
    }

    @Test
    void testUpdateKO() {
        when(itemsRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.update(1l, "DRAFT"));
    }
}
