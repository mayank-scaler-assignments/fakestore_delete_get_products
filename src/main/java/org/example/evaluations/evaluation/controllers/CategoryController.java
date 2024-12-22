package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CategoryController is a REST controller that handles HTTP requests related to product categories.
 * It provides an endpoint to retrieve all products belonging to a specific category.
 */
@RestController
@RequestMapping("/products/category")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves all products for a given category.
     *
     * @param categoryName the name of the category
     * @return a list of products belonging to the specified category
     */
    @GetMapping("/{categoryName}")
    public List<Product> getAllCategories(@PathVariable String categoryName) {
        return categoryService.getProducts(categoryName);
    }
}