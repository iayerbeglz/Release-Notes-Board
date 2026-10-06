package com.dbcargo.notesboard.controller;

import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;
import com.dbcargo.notesboard.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApplicationsControllerTest {

    @Mock
    private ApplicationService applicationsService;

    @InjectMocks
    private ApplicationsController applicationsController;

    @Test
    void testGetAllApplicationsOK() {
        when(applicationsService.findAll()).thenReturn(List.of(ApplicationResponse.builder().name("app1").build()));

        List<ApplicationResponse> result = applicationsController.getAllApplications();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllApplicationsEmpty() {
        when(applicationsService.findAll()).thenReturn(List.of());

        List<ApplicationResponse> result = applicationsController.getAllApplications();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
