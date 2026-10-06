package com.dbcargo.notesboard.controller;

import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;
import com.dbcargo.notesboard.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationsController {

    private final ApplicationService applicationsService;

    @GetMapping("/applications")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResponse> getAllApplications() {
        return applicationsService.findAll();
    }
}
