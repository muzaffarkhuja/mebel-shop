package uz.home.mebelshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(generator = "orderItemsId")
    @SequenceGenerator(name = "orderItemsId", sequenceName = "order_items_id", allocationSize = 1)
    private Integer id;
    private Integer productId;
    @ManyToOne
    private Orders orders;
    private Double amount;
}
