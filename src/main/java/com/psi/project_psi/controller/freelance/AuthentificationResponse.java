package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthentificationResponse {
    private String token;
    private Users users;
}
