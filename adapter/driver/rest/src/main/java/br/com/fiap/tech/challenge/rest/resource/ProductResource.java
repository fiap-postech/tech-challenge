package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.ProductReaderService;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductResource {

    private ModelMapper mapper;
    private ProductReaderService productReaderService;

    @GetMapping
    public List<ProductResponse> getAllAvailable(){
        return productReaderService.allAvailable()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .toList();
    }
}
