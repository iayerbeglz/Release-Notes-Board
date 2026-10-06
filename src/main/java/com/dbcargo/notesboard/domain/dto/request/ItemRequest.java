package com.dbcargo.notesboard.domain.dto.request;

import com.dbcargo.notesboard.validations.ItemStatusConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemRequest {

    private String release;
    private String description;

    @ItemStatusConstraint(message = "Allowed values are DRAFT, PREVIEW, PUBLISHED")
    private String status;

}
