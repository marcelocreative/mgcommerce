package com.mgsystems.mgcommerce.services;

import com.mgsystems.mgcommerce.dto.ProductDTO;
import com.mgsystems.mgcommerce.entities.Product;
import com.mgsystems.mgcommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> result = repository.findAll(pageable);

        return result.map(x -> new ProductDTO(x));
    }
}
