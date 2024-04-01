package com.psi.project_psi.utils;

import lombok.Data;

@Data
public class ErrorResponse {
    private String en;
    private String fr;

    public ErrorResponse getErrorMessage(){
        return new ErrorResponse();
    }
}

