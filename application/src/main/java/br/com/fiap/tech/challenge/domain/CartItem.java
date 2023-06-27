package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;

import static java.util.Objects.isNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class CartItem extends ValueObject {

    @Serial
    private static final long serialVersionUID = -8071866742749224693L;

    private final Product product;
    private final Quantity quantity;

    @Builder(toBuilder = true)
    public CartItem(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Price total() {
        if (isNull(product) || isNull(quantity)) {
            return Price.min();
        }
        return product.price().multiply(quantity);
    }

    public Price subtotal() {
        if (isNull(product) || isNull(quantity)) {
            return Price.min();
        }
        return product.fullPrice().multiply(quantity);
    }

    public Discount discount() {
        if (isNull(product) || isNull(quantity)) {
            return Discount.withoutDiscount();
        }
        return product.discount().multiply(quantity);
    }
}