package mini.market.demo.repository;

import mini.market.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
