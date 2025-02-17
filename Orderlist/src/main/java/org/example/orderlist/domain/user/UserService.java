package org.example.orderlist.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .purchases(userRequestDto.getPurchases())
                .build();

        User savedUser = userRepository.save(user);

        return UserMapper.toDto(savedUser);
    }

    public UserResponseDto getUserById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
