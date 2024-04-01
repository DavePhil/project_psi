package com.psi.project_psi.utils;

import lombok.Data;

@Data
public class AlreadyExist extends ErrorResponse {
    public AlreadyExist(){
        this.setEn("");
        this.setFr("");
    }
}
