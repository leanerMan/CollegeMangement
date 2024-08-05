package com.app.college.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
    private boolean error;
    private String message;
    private String exception;
    private List<FieldError> fieldErrors;

}
