package com.TimeForStudy.error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {

    private final List<ApplicationError> errors = new ArrayList<>(Collections.emptyList());

    public void addError(ApplicationError error) {
        errors.add(error);
    }

}