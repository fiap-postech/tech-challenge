package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Beverage;
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

import static br.com.fiap.tech.challenge.mapper.common.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface BeverageMapper {

    BeverageMapper INSTANCE = Mappers.getMapper(BeverageMapper.class);

    @Mapping(target = "uuid", source = "request", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Beverage toBeverage(CreateSingleProductRequest request);


    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "source", qualifiedByName = "imageToStringConverter")
    ProductResponse toProductEntity(Beverage source);

    @Named("generateUuid")
    static UUID generateUuid(CreateSingleProductRequest uuid){
        return UUID.randomUUID();
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Beverage source){
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source){
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image getImage(String source){
        return Image.of(source);
    }

    @Named("imageToStringConverter")
    static String imageConverter(Beverage source){
        return imageToStringConverter(source.image());
    }
}
