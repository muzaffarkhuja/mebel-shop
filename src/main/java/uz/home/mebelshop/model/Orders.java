package uz.home.mebelshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(generator = "orderIdSeq")
    @SequenceGenerator(name = "orderIdSeq", sequenceName = "order_id_seq", allocationSize = 1)
    private Integer id;
    private String token;
}
