package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> findAll();
}
