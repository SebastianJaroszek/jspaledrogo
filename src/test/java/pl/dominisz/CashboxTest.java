package pl.dominisz;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * http://dominisz.pl
 * 21.02.2018
 */
class CashboxTest {

    @Test
    void getTotalPrice() {
        Cashbox cashbox = new Cashbox();
        Cart cart = createCart();
        BigDecimal actualTotalPrice = cashbox.getTotalPrice(cart);
        BigDecimal expectedTotalPrice = getExpectedTotalPrice(cart);
        assertTrue(actualTotalPrice.compareTo(expectedTotalPrice) == 0);
    }

    //TODO najfajniej by było wykorzystać mocka do repozytorium w Cashbox ;-)
    private BigDecimal getExpectedTotalPrice(Cart cart) {
        return new BigDecimal(1690).multiply(new BigDecimal(2))
                .add(new BigDecimal(1399).multiply(new BigDecimal(3)));
    }

    private Cart createCart() {
        Cart cart = new Cart();
        CartItem cartItem1 = new CartItem(1, 2);
        CartItem cartItem2 = new CartItem(2, 3);
        cart.add(cartItem1);
        cart.add(cartItem2);
        return cart;
    }
}