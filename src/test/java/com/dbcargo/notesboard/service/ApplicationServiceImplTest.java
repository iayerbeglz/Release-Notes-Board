package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.entities.ApplicationEntity;
import com.dbcargo.notesboard.repository.ApplicationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceImplTest {

    @Mock
    private ApplicationsRepository applicationsRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    void testFindAllOK() {
        when(applicationsRepository.findAll())
                .thenReturn(List.of(
                        ApplicationEntity.builder().name("App1").build(),
                        ApplicationEntity.builder().name("App2").build()));

        assertNotNull(applicationService.findAll());
    }

    @Test
    void testFindAllEmpty() {
        when(applicationsRepository.findAll()).thenReturn(List.of());

        assertTrue(applicationService.findAll().isEmpty());
    }

    @Test
    void testFindByIdOK() {
        when(applicationsRepository.findById(1l)).thenReturn(Optional.of(ApplicationEntity.builder().name("App1").build()));

        assertNotNull(applicationService.findById(1l));
    }
}
