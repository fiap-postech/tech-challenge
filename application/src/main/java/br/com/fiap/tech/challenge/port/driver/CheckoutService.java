package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.Purchase;

import java.util.UUID;

public interface CheckoutService {
    Purchase checkout(UUID cartId);
}