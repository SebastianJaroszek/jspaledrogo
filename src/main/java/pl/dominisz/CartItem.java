package pl.dominisz;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Pozycja w koszyku, na którą składa się
 * id - id produktu
 * quantity - liczba sztuk
 */
@Data
@AllArgsConstructor
public class CartItem {

    //identyfikator produktu
    private int id;

    //sztuki w koszyku
    private int quantity;

}
