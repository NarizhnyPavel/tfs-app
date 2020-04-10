package com.TimeForStudy.error;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Бизнес-исключения приложения.
 *
 * @author Velikanov Artyom.
 */
public class ApplicationException extends RuntimeException {

    private final List<ErrorDescription> errorDescriptionList = new ArrayList<>(Collections.emptyList());
    private final List<ApplicationError> applicationErrorList = new ArrayList<>(Collections.emptyList());

    public ApplicationException(ErrorDescription errorDescription) {
        super(errorDescription.getMessage());
        errorDescriptionList.add(errorDescription);
    }

    public ApplicationException(ErrorDescription errorDescription, Throwable throwable) {
        super(errorDescription.getMessage(), throwable);
        errorDescriptionList.add(errorDescription);
    }

    public ApplicationException(ApplicationError errorResponse) {
        super(errorResponse.getMessage(), new Throwable(errorResponse.getCause()));
        applicationErrorList.add(errorResponse);
    }

    public ErrorResponse getErrorResponse() {
        ErrorResponse response = new ErrorResponse();
        if (CollectionUtils.isEmpty(applicationErrorList)) {
            errorDescriptionList.stream()
                    .map(it -> ApplicationError.of(it.getCode(), it.getMessage(), null))
                    .forEach(response::addError);
        } else {
            applicationErrorList.forEach(response::addError);
        }
        return response;
    }
}