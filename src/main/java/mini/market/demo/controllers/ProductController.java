package mini.market.demo.controllers;

import mini.market.demo.entities.Order;
import mini.market.demo.entities.Product;
import mini.market.demo.entities.User;
import mini.market.demo.repository.ProductRepository;
import mini.market.demo.services.ProductService;
import mini.market.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping(value="/admin/products")
    public ModelAndView listView() {
        List<Product> productList = productRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/products");
        modelAndView.addObject("productsList", productList);

        return modelAndView;
    }

    @GetMapping(value="/admin/products/edit/{id}")
    public ModelAndView editProductView(@PathVariable String id, Product product, BindingResult bindingResult) {
        product = productService.get(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("admin/products_edit");

        return modelAndView;
    }

    @PostMapping(value = "/admin/products/edit")
    public ModelAndView editProduct(Product product, BindingResult bindingResult) {
        productService.save(product);

        List<Product> productList = productRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/products");
        modelAndView.addObject("productsList", productList);

        return modelAndView;
    }

    @GetMapping(value="/admin/products/create")
    public ModelAndView createProductView() {
        Product product = new Product();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("admin/products_create");

        return modelAndView;
    }

    @PostMapping(value="/admin/products/create")
    public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        product.setUser(user);
        productService.save(product);


        List<Product> productList = productRepository.findAll();

        modelAndView.setViewName("admin/products");
        modelAndView.addObject("productsList", productList);


        return modelAndView;
    }

    @GetMapping(value="/admin/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();

        productService.delete(id);

        List<Product> productList = productRepository.findAll();

        modelAndView.setViewName("admin/products");
        modelAndView.addObject("productsList", productList);

        return modelAndView;
    }
}
