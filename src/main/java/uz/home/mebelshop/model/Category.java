package uz.home.mebelshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(generator = "categoryIdSeq")
    @SequenceGenerator(name = "categoryIdSeq", sequenceName = "category_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private Integer parentId;
}
