package com.example.fooddelivery.service.product;

import com.example.fooddelivery.command.ProductCommand;
import com.example.fooddelivery.dto.ProductDto;
import com.example.fooddelivery.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product createProduct(ProductCommand productCommand);

    Product findOneProduct(String productId);

    Page<ProductDto> getAllProducts(Pageable pageable);
    Product deleteById(String productId);
    Product updateProduct(String productId,ProductCommand productCommand);
}
