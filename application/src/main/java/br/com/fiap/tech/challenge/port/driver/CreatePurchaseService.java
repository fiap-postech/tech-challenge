package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

public interface CreatePurchaseService {
    Purchase create(Purchase purchase);
}