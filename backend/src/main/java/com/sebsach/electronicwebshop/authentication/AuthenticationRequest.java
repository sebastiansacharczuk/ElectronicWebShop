package com.sebsach.electronicwebshop.authentication;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @NotEmpty(message = "Username is required")
    @NotNull(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password should be between 6 and 30 characters")
    private String password;
}
