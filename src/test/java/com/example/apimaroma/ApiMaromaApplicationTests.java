package com.example.apimaroma;

import com.example.apimaroma.products.ProductBean;
import com.example.apimaroma.products.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
//@RunWith(SpringRunner.class)
@SpringBootTest
class ApiMaromaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ProductService productService;

    private ProductBean productBean;
/*
    @SneakyThrows
    @Test
    public void getProductsTest(){
        when(productService.getAllProducts(null, null, null, null, null,null, null, null, null)).thenReturn( Stream.of(new ProductBean("chaise", "", null, 4, 4.0, 4F, null)).collect(Collectors.toList()));
        assertEquals(1, productService.getAllProducts(null, null, null, null, null,null, null, null, null).size());
    }
    */
}
