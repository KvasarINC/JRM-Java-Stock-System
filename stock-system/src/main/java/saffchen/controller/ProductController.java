package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.ProductDto;
import saffchen.entities.ProductEntity;
import saffchen.mapper.ProductMapper;
import saffchen.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    static final String REST_URL = "/api/products";
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productMapper.toProductsDtoList(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Optional<ProductEntity> product = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.productToProductDto(product.get()));
    }
}
