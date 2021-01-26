package mini.market.demo.services;

import mini.market.demo.entities.Product;
import mini.market.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> allProducts() {
        return repo.findAll();
    }

    public Product save(Product product) { return repo.save(product); }

    public Product get(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
