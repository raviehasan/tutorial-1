package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl service;

    @Mock
    Model model;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndGetProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("back to december");
        product.setProductQuantity(100);
        Product newProduct = service.create(product);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", newProduct.getProductId());
        assertEquals("back to december", newProduct.getProductName());
        assertEquals(100, newProduct.getProductQuantity());

        when(productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);
        Product obtainedProduct = service.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product, obtainedProduct);
    }

    @Test
    void testFindAll() {
        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product());
        expectedList.add(new Product());

        when(productRepository.findAll()).thenReturn(expectedList.iterator());
        List<Product> actualList = service.findAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testFindAllIfEmpty() {
        when(productRepository.findAll()).thenReturn((new ArrayList<Product>()).iterator());
        List<Product> productList = service.findAll();
        assertTrue(productList.isEmpty());
    }

    @Test
    void testDeleteProduct() {
        String productId = "b0f9de46-90b1-437d-a0bf-d0821dde9096";
        service.delete(productId);
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testEditProduct() {
        String productId = "b0f9de46-90b1-437d-a0bf-d0821dde9096";
        Product editedProduct = new Product();
        editedProduct.setProductName("Edited Product");
        editedProduct.setProductQuantity(10);
        service.edit(productId, editedProduct);
        verify(productRepository, times(1)).edit(productId, editedProduct);
    }
}