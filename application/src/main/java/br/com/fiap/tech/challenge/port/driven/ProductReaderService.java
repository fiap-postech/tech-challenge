package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;

import java.util.UUID;

public interface ProductReaderService {

    ResponseList<Product> readAll(Page page);

    Product readById(UUID id);

}