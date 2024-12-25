package sit.int202.classicmodels_2567.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int202.classicmodels_2567.entities.Product;
import sit.int202.classicmodels_2567.repositories.CustomerRepository;
import sit.int202.classicmodels_2567.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String pCode) {
        return productRepository.findById(pCode).orElse(null);
    }

    public String createProduct(Product product){
       Product newProduct = productRepository.save(product); //save in database
       return newProduct.getProductCode(); //return
    }

    public boolean updateProduct(Product product){
        if (product.getProductCode() != null){
           Product updateProduct =   productRepository.save(product);
           return true;
        }
        return false;
    }

    public boolean deleteProduct(String productCode){
        Product product = this.getProductById(productCode); //productCode find

        if (product != null){
            productRepository.delete(product); //delete
            return true;
        }
        return false;
    }
}
