package com.dbcargo.notesboard.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {

    private String release;

    private String description;

    private String status;

    private String application;
}
