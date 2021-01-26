package mini.market.demo.controllers;

import mini.market.demo.entities.Order;
import mini.market.demo.entities.Product;
import mini.market.demo.entities.User;
import mini.market.demo.repository.OrderRepository;
import mini.market.demo.repository.ProductRepository;
import mini.market.demo.services.OrderService;
import mini.market.demo.services.ProductService;
import mini.market.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value="/admin/orders")
    public ModelAndView listView() {
        List<Order> orderList = orderRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/orders");
        modelAndView.addObject("ordersList", orderList);

        return modelAndView;
    }

    @GetMapping(value="/admin/orders/edit/{id}")
    public ModelAndView editOrderView(@PathVariable String id, Order order, BindingResult bindingResult) {
        order = orderService.get(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.setViewName("admin/orders_edit");

        return modelAndView;
    }

    @PostMapping(value = "/admin/orders/edit")
    public ModelAndView editOrder(Order order, BindingResult bindingResult) {
        orderService.save(order);

        List<Order> orderList = orderRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/orders");
        modelAndView.addObject("ordersList", orderList);

        return modelAndView;
    }

    @GetMapping(value="/admin/orders/create")
    public ModelAndView createProductView() {
        Order order = new Order();

        List<Product> allProducts = productRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("products", allProducts);
        modelAndView.setViewName("admin/orders_create");

        return modelAndView;
    }

    @PostMapping(value="/admin/orders/create")
    public ModelAndView createNewOrder(@Valid Order order, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        order.setUser(user);
        orderService.save(order);

        List<Order> orderList = orderRepository.findAll();

        modelAndView.setViewName("admin/orders");
        modelAndView.addObject("ordersList", orderList);

        return modelAndView;
    }

    @GetMapping(value="/admin/orders/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();

        orderService.delete(id);

        List<Order> orderList = orderRepository.findAll();

        modelAndView.setViewName("admin/orders");
        modelAndView.addObject("ordersList", orderList);

        return modelAndView;
    }

}