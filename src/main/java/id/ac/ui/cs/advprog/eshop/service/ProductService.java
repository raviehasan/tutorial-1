package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);

    public void delete(String productId);

    public List<Product> findAll();

    public Product getProductById(String productId);

    public void edit(String productId, Product editedProduct);
}