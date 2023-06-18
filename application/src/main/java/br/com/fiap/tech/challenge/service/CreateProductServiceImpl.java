package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.port.driven.ProductWriterService;
import br.com.fiap.tech.challenge.port.driver.CreateProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateProductServiceImpl implements CreateProductService {

    private ProductWriterService writerService;

    @Override
    public Product create(Product product) {
        return writerService.write(product);
    }
}