package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;
    
    @Mock
    private ProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create"))
                .andExpect(redirectedUrl("list"));
        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = new ArrayList<>();
        when(service.findAll()).thenReturn(productList);
        mockMvc.perform(get("/product/list"))
                .andExpect(view().name("ProductList"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", productList));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        mockMvc.perform(delete("/product/delete/{productId}", productId))
                .andExpect(redirectedUrl("../list"));
        verify(service, times(1)).delete(productId);
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(service.getProductById(productId)).thenReturn(product);
        mockMvc.perform(get("/product/edit/{productId}", productId))
                .andExpect(view().name("EditProduct"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testEditProductPut() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        mockMvc.perform(put("/product/edit/{productId}", productId))
                .andExpect(redirectedUrl("../list"));
        verify(service, times(1)).edit(eq(productId), any(Product.class));
    }
}
