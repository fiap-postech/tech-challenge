package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.rest.resource.request.CreateSingleProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface SideDishMapper {

    SideDishMapper INSTANCE = Mappers.getMapper(SideDishMapper.class);

    @Mapping(target = "uuid", source = "request", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    SideDish toSideDish(CreateSingleProductRequest request);

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "source", qualifiedByName = "imageToStringConverter")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    @Mapping(target = "fullPrice", source = "source", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    @Mapping(target = "category", source = "source", qualifiedByName = "getCategory")
    ProductResponse toProductEntity(SideDish source);

    @Named("generateUuid")
    static UUID generateUuid(CreateSingleProductRequest uuid) {
        return UUID.randomUUID();
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(SideDish source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(SideDish source) {
        return priceToBigDecimalConverter(source.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(SideDish source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(SideDish source) {
        return source.category();
    }

    @Named("getImage")
    static Image getImage(String source) {
        return Image.of(source);
    }

    @Named("imageToStringConverter")
    static String imageConverter(SideDish source) {
        return imageToStringConverter(source.image());
    }
}