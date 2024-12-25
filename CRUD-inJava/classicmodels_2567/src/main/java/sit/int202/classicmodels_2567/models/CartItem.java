package sit.int202.classicmodels_2567.models;

import sit.int202.classicmodels_2567.entities.Product;

public interface CartItem {
    int getQuantity();
    void setQuantity(int quantity);
    double getUnitPrice();
    double getTotal();
    double getPercentDiscount();
    Product getProduct();
}