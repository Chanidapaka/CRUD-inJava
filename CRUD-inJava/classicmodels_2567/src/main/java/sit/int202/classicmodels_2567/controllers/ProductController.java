package sit.int202.classicmodels_2567.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sit.int202.classicmodels_2567.entities.Product;
import sit.int202.classicmodels_2567.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("page", "products");
        return "product_list";
    }
    @PostMapping("")
    public String createProduct(@ModelAttribute Product product, Model model){
        String productCode = productService.createProduct(product);
        return this.getAllProducts(model);
    }

  @GetMapping("/form")  //get ส่งไปที่product_from
    public String createProduct(){
        return "product_form";
  }

    @GetMapping("/form/{id}")  //get ส่งไปที่product_from
    public String updateProduct(@PathVariable String id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product_update_form";
    }
    @PutMapping("")
    public String updateProduct(@PathVariable String id, @RequestBody Product product, Model model){
        productService.updateProduct(product);
        return this.getAllProducts(model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String deleteProduct(@PathVariable String id, Model model){
        productService.deleteProduct(id);
            return this.getAllProducts(model);
        }
    }

