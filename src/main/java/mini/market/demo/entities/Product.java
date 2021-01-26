package mini.market.demo.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @OneToOne
    private User user;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


/*
for mysql to create the table
*
    CREATE TABLE `test`.`products` (
  `id` VARCHAR(45) NOT NULL,
  `company_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `price`  DECIMAL(5,2) NOT NULL DEFAULT '0',
  `quantity` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `company_id` (`company_id` ASC) VISIBLE);
*/