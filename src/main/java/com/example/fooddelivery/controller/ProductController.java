package com.example.fooddelivery.controller;



import com.example.fooddelivery.dto.ProductDto;
import com.example.fooddelivery.mapper.ProductMapper;
import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.fooddelivery.cons.ResourcePath.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAll(Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") final String productId){
        final Product product = productService.findOneProduct(productId);

        return ResponseEntity.ok(productMapper.toProductDto(product));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable("productId")final String productId){
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
