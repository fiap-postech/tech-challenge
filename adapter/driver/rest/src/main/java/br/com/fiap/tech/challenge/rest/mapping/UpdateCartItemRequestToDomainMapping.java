package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

@Mapper
@AllArgsConstructor
public class UpdateCartItemRequestToDomainMapping implements RestTypeMapConfiguration {

    private final FindProductByUUIDService findProductByUUIDService;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(UpdateCartItemRequest.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private Provider<CartItem> cartItemProvider() {
        return provision -> {
            var request = (UpdateCartItemRequest) provision.getSource();
            var product = getProduct(request.getProductId());
            return CartItem.builder()
                    .product(product)
                    .quantity(Quantity.of(request.getQuantity()))
                    .build();
        };
    }

    private Product getProduct(String uuid) {
        return findProductByUUIDService.get(UUID.fromString(uuid));
    }
}