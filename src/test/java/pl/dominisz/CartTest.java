package pl.dominisz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * http://dominisz.pl
 * 20.02.2018
 */
class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void add() {
        CartItem cartItem1 = new CartItem(1, 2);
        CartItem cartItem2 = new CartItem(1, 3);
        CartItem cartItem3 = new CartItem(2, 5);
        cart.add(cartItem1);
        cart.add(cartItem2);
        cart.add(cartItem3);
        List<CartItem> cartItems = cart.getCartItems();
        assertEquals(2, cartItems.size());
        assertEquals(1, cartItems.get(0).getId());
        assertEquals(5, cartItems.get(0).getQuantity());
        assertEquals(2, cartItems.get(1).getId());
        assertEquals(5, cartItems.get(1).getQuantity());
    }

    @Test
    void getTotalPrice() {
        //TODO dodać dwa produkty do koszyka
        //sprawdzić ich łączną cenę
    }
}