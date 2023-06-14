package uz.home.mebelshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Integer id;
    private Integer productId;
    private String path;
    private String ext;
    private LocalDateTime createdAt;
}
