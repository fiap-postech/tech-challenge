package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.valueobject.Quantity;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class AddCartItemRequestMapper {

    @Autowired
    protected FindProductByUUIDService findProductByUUIDService;

    @Mapping(target = "product", source = "source", qualifiedByName = "getProduct")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    public abstract CartItem toCartItem (AddCartItemRequest source);

    @Named("getProduct")
    Product getProduct (AddCartItemRequest source){
        var id = source.getProductId();
        return findProductByUUIDService.get(UUID.fromString(id));
    }

    @Named("getQuantity")
    Quantity getQuantity (AddCartItemRequest source){
        return Quantity.of(source.getQuantity());
    }

}
