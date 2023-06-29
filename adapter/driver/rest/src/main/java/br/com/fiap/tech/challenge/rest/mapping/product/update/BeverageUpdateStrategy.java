package br.com.fiap.tech.challenge.rest.mapping.product.update;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Image;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

class BeverageUpdateStrategy extends AbstractProductUpdateStrategy {
    public BeverageUpdateStrategy(UpdateProductRequest request) {
        super(request);
    }

    @Override
    public Product update(Product product) {
        if (product instanceof Beverage beverage) {
            var builder = beverage.toBuilder();

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