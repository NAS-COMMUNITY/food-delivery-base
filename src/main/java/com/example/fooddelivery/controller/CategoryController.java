package com.example.fooddelivery.controller;


import com.example.fooddelivery.command.CategoryCommand;
import com.example.fooddelivery.dto.CategoryDTO;
import com.example.fooddelivery.dto.mapper.CategoryMapper;
import com.example.fooddelivery.model.Category;
import com.example.fooddelivery.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.example.fooddelivery.cons.ResourcePath.CATEGORIES;
import static com.example.fooddelivery.cons.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAll(pageable).map(categoryMapper::toDto));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCommand categoryCommand){
        final Category category = categoryService.create(categoryCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryMapper.toDto(category));
    }
}
