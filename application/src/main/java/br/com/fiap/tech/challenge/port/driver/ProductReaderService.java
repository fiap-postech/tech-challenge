package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Product;

import java.util.List;

public interface ProductReaderService {

    List<Product> allAvailable();

}
