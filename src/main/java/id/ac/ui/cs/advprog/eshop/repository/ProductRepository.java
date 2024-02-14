package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void delete(String productId) {
        productData.remove(getProductById(productId));
    }

    public Product getProductById(String productId) {
        Product wantedProduct = null;
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                wantedProduct = product;
            }
        }
        return wantedProduct;
    }

    public void edit(String productId, Product editedProduct) {
        String newName = editedProduct.getProductName();
        int newQuantity = editedProduct.getProductQuantity();
        if (newQuantity < 0)
            newQuantity = 0;

        Product productToEdit = getProductById(productId);
        productToEdit.setProductName(newName);
        productToEdit.setProductQuantity(newQuantity);
    }
}
