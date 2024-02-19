package com.mgsystems.mgcommerce.services;

import com.mgsystems.mgcommerce.dto.ProductDTO;
import com.mgsystems.mgcommerce.entities.Product;
import com.mgsystems.mgcommerce.repositories.ProductRepository;
import com.mgsystems.mgcommerce.services.exceptions.DatabaseException;
import com.mgsystems.mgcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductDTO(entity);

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> result = repository.findAll(pageable);

        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity= new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);

        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try{
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity= repository.save(entity);

            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Id not found");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }

}
