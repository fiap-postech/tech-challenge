package br.com.fiap.tech.challenge.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Accessors(fluent = true)
@ToString
public class Quantity extends ValueObject {
    @Serial
    private static final long serialVersionUID = 5676097435219158623L;

    private final int value;

    private Quantity(int quantity) {
        assertArgumentRange(
                quantity,
                1,
                Integer.MAX_VALUE,
                "quantity should be greater than or equal to 1"
        );

        this.value = quantity;
    }

    public Quantity increment(){
        return increment(1);
    }

    public Quantity increment(int value){
        return of(value() + value);
    }

    public Quantity decrement(){
        return decrement(1);
    }

    public Quantity decrement(int value){
        return of(value() - value);
    }

    public static Quantity of(int value){
        return new Quantity(value);
    }
}
