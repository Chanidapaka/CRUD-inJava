package sit.int202.classicmodels_2567.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int202.classicmodels_2567.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
   List<Product> findByBuyPriceGreaterThan(Double buyPrice);
}