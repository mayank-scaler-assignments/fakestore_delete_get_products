package org.example.evaluations.evaluation.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public void deleteProduct(Long productId) {
       //Add your implementation here
        RestTemplate restTemplate = restTemplateBuilder.build();
        String DELETE_PRODUCT_URL = "https://fakestoreapi.com/products/{productId}";
        restTemplate.delete(DELETE_PRODUCT_URL, productId);
    }
}
