package com.mgsystems.mgcommerce.controllers;

import com.mgsystems.mgcommerce.dto.ProductDTO;
import com.mgsystems.mgcommerce.entities.Product;
import com.mgsystems.mgcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/products")
public class ProductController {


    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){

        return ResponseEntity.ok(service.findById(id));

    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPage(Pageable pageable){

        Page<ProductDTO> page = service.findAll(pageable);

        return ResponseEntity.ok(page);
    }

}
