package uz.home.mebelshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Sales {
    @Id
    @GeneratedValue(generator = "salesIdSeq")
    @SequenceGenerator(name = "salesIdSeq", sequenceName = "sales_id_seq", allocationSize = 1)
    private Integer id;
    @OneToOne
    private Product product;
    private LocalDateTime expireDate;
    private Double price;
}
