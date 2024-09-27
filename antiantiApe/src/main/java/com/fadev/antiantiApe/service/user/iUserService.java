package com.fadev.antiantiApe.service.user;

import com.fadev.antiantiApe.dto.UserDTO;
import com.fadev.antiantiApe.model.User;
import com.fadev.antiantiApe.request.CreateUserRequest;
import com.fadev.antiantiApe.request.UpdateUserRequest;

public interface iUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

    UserDTO convertUserToDTO(User user);
}
