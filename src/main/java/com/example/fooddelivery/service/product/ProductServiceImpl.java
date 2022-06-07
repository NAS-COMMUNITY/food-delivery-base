package com.example.fooddelivery.service.product;


import com.example.fooddelivery.command.ProductCommand;
import com.example.fooddelivery.dto.ProductDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.ProductMapper;
import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toProductDto);
    }

    @Override
    @Transactional
    public Product createProduct(ProductCommand productCommand) {
        final Product product = productRepository.save(Product.createOne(productCommand));
        return product;
    }
    @Override
    public Product findOneProduct(String productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.PRODUCT_NOT_FOUND.get()));
        return product;
    }
    @Override
    public Product deleteById(String productId) {
        final Product product = findOneProduct(productId);
        product.delete();
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(String productId, ProductCommand productCommand) {
        final Product product = findOneProduct(productId);

        product.update(productCommand);

        return productRepository.save(product);
    }
}
