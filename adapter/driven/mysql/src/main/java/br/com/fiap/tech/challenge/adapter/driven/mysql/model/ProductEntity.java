package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class ProductEntity extends JPAEntity {
    @Serial
    private static final long serialVersionUID = -3103908935115324117L;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Column(columnDefinition = "text")
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public Product toDomain(){
        return switch (category){
            case BEVERAGE -> Beverage.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(makeMoney(getPrice()))
                    .image(Image.of(getImage()))
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case SANDWICH -> Sandwich.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(makeMoney(getPrice()))
                    .image(Image.of(getImage()))
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case DESSERT -> Dessert.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(makeMoney(getPrice()))
                    .image(Image.of(getImage()))
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case SIDE_DISH, COMBO -> SideDish.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(makeMoney(getPrice()))
                    .image(Image.of(getImage()))
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            //TODO map combo to domain
        };
    }
}
