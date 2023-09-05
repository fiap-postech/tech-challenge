package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.enterprise.entity.Purchase;

import java.util.UUID;

public interface FindPurchaseByUUIDService {

    Purchase get(UUID uuid);

}