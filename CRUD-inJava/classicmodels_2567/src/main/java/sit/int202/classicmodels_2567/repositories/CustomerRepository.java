package sit.int202.classicmodels_2567.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int202.classicmodels_2567.entities.Customer;
import sit.int202.classicmodels_2567.entities.Product;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("FROM Customer c where concat(c.contactFirstName,  ' ' , c.contactLastName) like ?1")
    Customer findByName(String name);

    List<Customer> findByContactFirstNameOrderByCustomerName(String contactFirstName);
}