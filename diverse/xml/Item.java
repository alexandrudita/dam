package eu.ase.ro.damapp.network.xml;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    private String category;
    private List<Product> products;

    public Item() {
    }

    public Item(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Item{" +
                "category='" + category + '\'' +
                ", products=" + products +
                '}';
    }
}
