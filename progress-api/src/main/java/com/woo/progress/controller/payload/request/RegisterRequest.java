package com.woo.progress.controller.payload.request;

import com.woo.progress.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "firstname is required")
    private String enName;
    @NotBlank(message = "lastname is required")
    private String userName;
    @NotBlank(message = "email is required")
//    @Email(message = "email format is not valid")
    private String userId;
    @NotBlank(message = "password is required")
//    @StrongPassword
    private String password;
    @NotNull
    private Role role;
}
