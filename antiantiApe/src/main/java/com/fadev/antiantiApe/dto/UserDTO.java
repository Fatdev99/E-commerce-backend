package com.fadev.antiantiApe.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private List<OrderDTO> orders;
    private CartDTO cart;
}
