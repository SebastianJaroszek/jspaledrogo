package pl.dominisz;

import java.math.BigDecimal;

/**
 * Kasa umożliwia obliczenie łącznej wartości towarów znajdujących się w koszyku
 */
public class Cashbox {

    private ProductRepository productRepository = ProductRepository.getInstance();

    public BigDecimal getTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getId());
            if (product != null) {
                totalPrice =
                        totalPrice.add(product.getPrice()
                                .multiply(new BigDecimal(cartItem.getQuantity())));
            }
        }
        return totalPrice;
    }
}
