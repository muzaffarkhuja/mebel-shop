package uz.home.mebelshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;
}
