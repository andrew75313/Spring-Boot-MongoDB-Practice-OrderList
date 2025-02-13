package org.example.orderlist.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User(
                userRequestDto.getId(),
                userRequestDto.getName(),
                userRequestDto.getEmail(),
                userRequestDto.getPurchases()
        );

        // DB에 저장
        User savedUser = userRepository.save(user);

        // 저장된 User 객체를 DTO로 변환하여 반환
        return UserMapper.toDto(savedUser);
    }

    public UserResponseDto getUserById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
