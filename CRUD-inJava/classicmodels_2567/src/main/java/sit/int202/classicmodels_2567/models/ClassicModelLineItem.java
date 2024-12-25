package sit.int202.classicmodels_2567.models;

import lombok.Getter;
import sit.int202.classicmodels_2567.entities.Product;

@Getter
public class ClassicModelLineItem implements CartItem {
    private Product product;
    private int quantity;
    private double percentDiscount;

    public ClassicModelLineItem(Product product) {
        this(product, 1, 0.0);
    }

    public ClassicModelLineItem(Product product, int quantity) {
        this(product, quantity, 0.0);
    }

    public ClassicModelLineItem(Product product, int quantity, double percentDiscount) {
        this.product = product;
        this.quantity = quantity;
        this.percentDiscount = percentDiscount;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getUnitPrice() {
        return product.getMsrp().doubleValue();
    }

    @Override
    public double getTotal() {
        return getUnitPrice() * getQuantity() - getUnitPrice() * getQuantity() * percentDiscount;
    }
}
