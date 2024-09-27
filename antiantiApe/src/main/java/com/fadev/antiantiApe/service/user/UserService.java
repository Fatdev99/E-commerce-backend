package com.fadev.antiantiApe.service.user;

import com.fadev.antiantiApe.dto.UserDTO;
import com.fadev.antiantiApe.exception.AlreadyExistsException;
import com.fadev.antiantiApe.exception.ResourceNotFoundException;
import com.fadev.antiantiApe.model.User;
import com.fadev.antiantiApe.request.CreateUserRequest;
import com.fadev.antiantiApe.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fadev.antiantiApe.repository.userRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements iUserService{
    private final userRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFullName(request.getFullName());
                    user.setPhone(request.getPhone());
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());

                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("Oops!" + request.getEmail() + " already exists!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return  userRepository.findById(userId).map(existingUser ->{
            existingUser.setFullName(request.getFullName());
            existingUser.setPhone(request.getPhone());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User Not Found!");
        });
    }

    @Override
    public UserDTO convertUserToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
