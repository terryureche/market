package mini.market.demo.entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import mini.market.demo.entities.Product;
import mini.market.demo.entities.User;

import javax.persistence.*;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ManyToMany
    private List<Product> product;
    @OneToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date order_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shipping_date;

}
