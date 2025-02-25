package org.example.orderlist.global.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class DataResponseDto<T> {
    private T data;
}
