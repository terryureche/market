package mini.market.demo.repository;

import mini.market.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}
