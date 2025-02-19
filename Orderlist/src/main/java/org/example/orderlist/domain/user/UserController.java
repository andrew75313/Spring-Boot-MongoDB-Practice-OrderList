package org.example.orderlist.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.orderlist.domain.file.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CsvService csvService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        try {
            csvService.uploadUserCsv(file); // CSV 업로드 처리
            return ResponseEntity.ok("CSV 파일 업로드 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponseDto> getUserById(@PathVariable String id) {

        UserDataResponseDto response = userService.getUserById(id);

         return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/max/{maxQuantity}")
    public ResponseEntity<List<UserDataResponseDto>> getUserByMaxQuantity(@PathVariable int maxQuantity) {

        List<UserDataResponseDto> response = userService.getUserByMaxQuantity(maxQuantity);

        return ResponseEntity.ok(response);
    }
}
