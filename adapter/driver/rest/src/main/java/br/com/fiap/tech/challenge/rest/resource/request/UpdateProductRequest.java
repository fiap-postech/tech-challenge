package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateProductRequest extends Request<Product> {
    @Serial
    private static final long serialVersionUID = 3075479575539176960L;

    @NotBlank
    private String id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private Boolean enabled;

    @Override
    public Product toDomain(ModelMapper mapper) {
        return mapper.map(this, Product.class);
    }
}