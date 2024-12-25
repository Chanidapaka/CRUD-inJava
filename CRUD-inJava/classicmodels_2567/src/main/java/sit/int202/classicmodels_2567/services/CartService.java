package sit.int202.classicmodels_2567.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int202.classicmodels_2567.entities.Product;
import sit.int202.classicmodels_2567.models.Cart;
import sit.int202.classicmodels_2567.models.CartItem;
import sit.int202.classicmodels_2567.models.ClassicModelLineItem;

@Service
public class CartService {
    @Autowired
    ProductService productService;
    public void addItem(String productCode, Cart<String, CartItem> cart) {
        // SELECT * FROM products WHERE id = productCode;
        Product p = productService.getProductById(productCode);
        if (p != null) {
            CartItem ci = new ClassicModelLineItem(p);
            cart.addItem(productCode, ci);
        }
    }
}
