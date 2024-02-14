package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("All Too Well");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindUnavailableProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Afterglow");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Backburner");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Product obtainedProduct = productRepository.getProductById("z0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNull(obtainedProduct);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Afterglow");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Backburner");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product3.setProductName("Forever Winter");
        product3.setProductQuantity(200);
        productRepository.create(product3);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductPutPositiveQuantity() {
        Product product = new Product();
        product.setProductId("b0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Ori");
        product.setProductQuantity(1);

        productRepository.create(product);

        Product editingData = new Product();
        editingData.setProductId("b0f9de46-90b1-437d-a0bf-d0821dde9096");
        editingData.setProductName("Editing");
        editingData.setProductQuantity(5);

        productRepository.edit("b0f9de46-90b1-437d-a0bf-d0821dde9096", editingData);
        Product editedProduct = productRepository.getProductById(editingData.getProductId());

        assertEquals(editingData.getProductId(), editedProduct.getProductId());
        assertEquals("Editing", editedProduct.getProductName());
        assertEquals(5, editedProduct.getProductQuantity());
    }

    @Test
    void testEditProductPutNegativeQuantity() {
        Product product = new Product();
        product.setProductId("b0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Ori");
        product.setProductQuantity(1);

        productRepository.create(product);

        Product editingData = new Product();
        editingData.setProductId("b0f9de46-90b1-437d-a0bf-d0821dde9096");
        editingData.setProductName("Editing");
        editingData.setProductQuantity(-5);

        productRepository.edit("b0f9de46-90b1-437d-a0bf-d0821dde9096", editingData);
        Product editedProduct = productRepository.getProductById(editingData.getProductId());

        assertEquals(editingData.getProductId(), editedProduct.getProductId());
        assertEquals("Editing", editedProduct.getProductName());
        assertEquals(0, editedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("b0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Ori");
        product.setProductQuantity(1);

        productRepository.create(product);
        productRepository.delete(product.getProductId());

        Product searchDeletedProduct = productRepository.getProductById(product.getProductId());
        assertNull(searchDeletedProduct);
    }
}