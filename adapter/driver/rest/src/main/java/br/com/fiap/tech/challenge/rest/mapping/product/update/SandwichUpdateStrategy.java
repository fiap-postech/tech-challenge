package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

class SandwichUpdateStrategy extends AbstractProductUpdateStrategy {
    public SandwichUpdateStrategy(UpdateProductRequest request) {
        super(request);
    }

    @Override
    public Product update(Product product) {
        if (product instanceof Sandwich sandwich) {
            var builder = sandwich.toBuilder();

            updateName(builder::name);
            updateDescription(builder::description);
            updateEnabled(builder::enabled);
            updatePrice(price -> builder.price(Price.of(makeMoney(price))));
            updateImage(image -> builder.image(Image.of(image)));

            return builder.build();
        }

        return product;
    }
}
