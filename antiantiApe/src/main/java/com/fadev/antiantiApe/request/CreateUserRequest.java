package com.fadev.antiantiApe.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String fullName;
    private String phone;
    private String email;
    private String password;
}
