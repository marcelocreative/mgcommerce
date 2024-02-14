package com.mgsystems.mgcommerce.services;

import com.mgsystems.mgcommerce.dto.ProductDTO;
import com.mgsystems.mgcommerce.entities.Product;
import com.mgsystems.mgcommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

        Product entity = repository.findById(id).get();
        return new ProductDTO(entity);

    }

}
