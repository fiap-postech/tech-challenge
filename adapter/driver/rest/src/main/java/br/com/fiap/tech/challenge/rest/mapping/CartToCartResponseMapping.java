package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import org.modelmapper.ModelMapper;

@Mapper
public class CartToCartResponseMapping implements TypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartResponse.class)
                .addMapping(Cart::uuid, CartResponse::setId)
                .addMapping(Cart::items, CartResponse::setItems)
//                .addMapping(Product::name, ProductResponse::setName)
//                .addMapping(Product::category, ProductResponse::setCategory)
//                .addMapping(Product::description, ProductResponse::setDescription)
//                .addMapping(Product::image, ProductResponse::setImage)
//                .addMappings(mapping -> mapping.using(priceToMoneyConverter()).map(Product::price, ProductResponse::setPrice))
        ;
    }
}
