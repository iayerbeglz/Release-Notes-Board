package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    List<ApplicationResponse> findAll();

    ApplicationResponse findById(Long applicationId);
}
