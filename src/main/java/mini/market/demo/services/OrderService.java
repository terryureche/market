package mini.market.demo.services;

import mini.market.demo.entities.Order;
import mini.market.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository repo;

    public List<Order> allOrders() {
        return repo.findAll();
    }

    public Order save(Order order) {
        return repo.save(order);
    }

    public Order get(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
