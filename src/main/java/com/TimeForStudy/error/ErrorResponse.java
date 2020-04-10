package com.TimeForStudy.error;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ErrorResponse {

    private final List<ApplicationError> errors = new ArrayList<>(Collections.emptyList());

    public void addError(ApplicationError error) {
        errors.add(error);
    }

}
