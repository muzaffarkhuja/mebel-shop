package uz.home.mebelshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(generator = "imageIdSeq")
    @SequenceGenerator(name = "imageIdSeq", sequenceName = "image_id_seq", allocationSize = 1)
    private Integer id;
    private Integer productId;
    private String path;
    private String ext;
    private LocalDateTime createdAt;
}
