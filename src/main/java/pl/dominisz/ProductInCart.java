package pl.dominisz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductInCart {

    private String name;
    private BigDecimal price;
    private int quantity;

}
