package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    void delete(String productId);
    List<Product> findAll();
    Product getProductById(String productId);
    void edit(String productId, Product editedProduct);
}