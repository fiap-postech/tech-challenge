package br.com.fiap.tech.challenge.rest.resource.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateProductRequest extends ProductRequest {

    @Serial
    private static final long serialVersionUID = 1464909268054662495L;
}