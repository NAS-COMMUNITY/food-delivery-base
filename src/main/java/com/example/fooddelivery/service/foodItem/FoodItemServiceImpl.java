package com.example.fooddelivery.service.foodItem;


import com.example.fooddelivery.command.FoodItemCommand;
import com.example.fooddelivery.dto.FoodItemDto;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.FoodItemMapper;
import com.example.fooddelivery.model.FoodItem;
import com.example.fooddelivery.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;


    @Override
    public Page<FoodItemDto> getAllFoodItems(Pageable pageable) {
        Page<FoodItem> products = foodItemRepository.findAll(pageable);
        return products.map(foodItemMapper::toProductDto);
    }

    @Override
    @Transactional
    public FoodItem createFoodItem(FoodItemCommand foodItemCommand) {
        return foodItemRepository.save(FoodItem.createOne(foodItemCommand));
    }
    @Override
    public FoodItem findOneFoodItem(String productId) {
        return foodItemRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ExceptionFactory.PRODUCT_NOT_FOUND.get()));
    }
    @Override
    public FoodItem deleteById(String productId) {
        final FoodItem foodItem = findOneFoodItem(productId);
        foodItem.delete();
        return foodItemRepository.save(foodItem);
    }
    @Override
    public FoodItem updateFoodItem(String productId, FoodItemCommand foodItemCommand) {
        final FoodItem foodItem = findOneFoodItem(productId);

        foodItem.update(foodItemCommand);

        return foodItemRepository.save(foodItem);
    }
}
