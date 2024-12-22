package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.FakeStoreProductDto;
import org.example.evaluations.evaluation.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService implements ICategoryService {

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCategoryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getProducts(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String GET_CATEGORY_BY_NAME = "https://fakestoreapi.com/products/category/{categoryName}";

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(GET_CATEGORY_BY_NAME, FakeStoreProductDto[].class, categoryName);
        if (response == null || response.getBody() == null || response.getBody().length == 0) {
            return List.of();
        }

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        return mapToProducts(fakeStoreProductDtos);
    }

    private List<Product> mapToProducts(FakeStoreProductDto[] fakeStoreProductDtos) {
        return Arrays.stream(fakeStoreProductDtos)
                .map(this::mapToProduct)
                .toList();
    }

    private Product mapToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        return product;
    }

}
