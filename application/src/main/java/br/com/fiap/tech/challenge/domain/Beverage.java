package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Beverage extends Product {

    @Serial
    private static final long serialVersionUID = 8271359996195513007L;

    @Builder
    protected Beverage(UUID uuid, String name, String description, Price price, Image image) {
        super(uuid, name, description, price, image);
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.BEVERAGE;
    }

    @Override
    public Price fullPrice() {
        return price();
    }

    @Override
    public Discount discount() {
        return Discount.withoutDiscount();
    }
}