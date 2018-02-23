package pl.dominisz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Przechowuje koszyk
 * - lista CartItem
 * - dodanie CartItem do koszyka
 */
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void add(CartItem cartItem) {
        CartItem existingCartItem = findById(cartItem.getId());
        if (existingCartItem != null) {
            int existingQuantity = existingCartItem.getQuantity();
            existingCartItem.setQuantity(existingQuantity + cartItem.getQuantity());
        } else {
            cartItems.add(cartItem);
        }
    }

    private CartItem findById(int id) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
