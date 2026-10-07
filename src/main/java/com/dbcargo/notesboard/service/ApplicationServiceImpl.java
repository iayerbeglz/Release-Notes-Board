package com.dbcargo.notesboard.service;

import com.dbcargo.notesboard.domain.dto.response.ApplicationResponse;
import com.dbcargo.notesboard.domain.exception.ItemNotFoundException;
import com.dbcargo.notesboard.repository.ApplicationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationsRepository applicationsRepository;

    @Override
    public List<ApplicationResponse> findAll() {
        return applicationsRepository.findAll().stream()
                .map(entity -> ApplicationResponse.builder().name(entity.getName()).build())
                .toList();
    }

    @Override
    public ApplicationResponse findById(Long applicationId) {
        return applicationsRepository.findById(applicationId)
                .map(entity -> ApplicationResponse.builder().name(entity.getName()).build())
                .orElseThrow(() -> new ItemNotFoundException());
    }
}
