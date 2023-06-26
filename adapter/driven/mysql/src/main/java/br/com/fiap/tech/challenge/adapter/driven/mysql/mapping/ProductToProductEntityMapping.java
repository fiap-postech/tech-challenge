package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.ProductEntityRepository;
import br.com.fiap.tech.challenge.domain.*;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_NOT_FOUND_BY_UUID;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToMoneyConverter;
import static java.util.Objects.isNull;

@Mapper
@AllArgsConstructor
public class ProductToProductEntityMapping implements MySQLTypeMapConfiguration {

    private final ProductEntityRepository productRepository;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Product.class, ProductEntity.class)
                .addMapping(Product::uuid, ProductEntity::setUuid)
                .addMapping(Product::name, ProductEntity::setName)
                .addMapping(Product::category, ProductEntity::setCategory)
                .addMapping(Product::description, ProductEntity::setDescription)
                .addMapping(Product::enabled, ProductEntity::setEnabled)
                .addMapping(Product::image, ProductEntity::setImage)
                .addMappings(mapping -> mapping.using(priceToMoneyConverter()).map(Product::price, ProductEntity::setPrice));

        mapper.typeMap(Sandwich.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Beverage.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Dessert.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(SideDish.class, ProductEntity.class)
                .includeBase(Product.class, ProductEntity.class);

        mapper.typeMap(Combo.class, ComboEntity.class)
                .includeBase(Product.class, ProductEntity.class)
                .addMappings(mapping -> {
                    var converter = comboItemsConverter();

                    mapping.using(converter).map(Combo::beverage, ComboEntity::setBeverage);
                    mapping.using(converter).map(Combo::sandwich, ComboEntity::setSandwich);
                    mapping.using(converter).map(Combo::sideDish, ComboEntity::setSideDish);
                });
    }

    private Converter<? extends Product, ProductEntity> comboItemsConverter(){
        return ctx -> {
            var product = ctx.getSource();

            if (isNull(product)){
                return null;
            }

            var uuid = product.uuid().toString();

            return productRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ApplicationException(PRODUCT_NOT_FOUND_BY_UUID, uuid));
        };

    }
}
