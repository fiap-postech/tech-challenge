package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.fromString;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartResource {

    private ModelMapper mapper;
    private FindCartByUUIDService findCartByUUIDService;
    private CreateCartService createCartService;
    private AddCartItemService addCartItemService;
    private RemoveCartItemService removeCartItemService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse get(@PathVariable("uuid") String uuid) {
        return mapper.map(
                findCartByUUIDService.get(fromString(uuid)),
                CartResponse.class
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse create(@RequestBody @Valid CreateCartRequest request) {
        return mapper.map(
                createCartService.create(request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @PostMapping("{cartId}/item/add")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addItem(@PathVariable("cartId") String cartId, @RequestBody @Valid AddCartItemRequest request) {
        return mapper.map(
                addCartItemService.add(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @DeleteMapping("{cartId}/item/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse removeItem(@PathVariable("cartId") String cartId, @PathVariable("cartItemId") String cartItemId) {
        return mapper.map(
                removeCartItemService.remove(fromString(cartId), fromString(cartItemId)),
                CartResponse.class
        );
    }
}