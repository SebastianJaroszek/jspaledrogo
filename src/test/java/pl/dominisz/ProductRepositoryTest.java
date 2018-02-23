package pl.dominisz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * http://dominisz.pl
 * 20.02.2018
 */
class ProductRepositoryTest {

    private ProductRepository productRepository = ProductRepository.getInstance();

    @Test
    void findAll() {
        //TODO zaszyta na stałe lista produktów w konstruktorze nie jest dobra pod kątem testów
        //lepiej dostarczać listę produktów z zewnątrz, np. setter, add
        List<Product> products = productRepository.findAll();
        assertEquals(9, products.size(), "There should be 9 products");
    }

    @Test
    void findById() {
        int existingProductId = 3;
        Product product = productRepository.findById(existingProductId);
        assertNotNull(product);
        assertEquals(existingProductId, product.getId());

        int notExistingProductId = 100;
        product = productRepository.findById(notExistingProductId);
        assertNull(product);
    }

    @Test
    void setCount() {
        int existingProductId = 3;
        int countAfterUpdate = 10;
        productRepository.setCount(existingProductId, countAfterUpdate);
        Product product = productRepository.findById(existingProductId);
        assertEquals(countAfterUpdate,
                product.getCount(), "Numbers should be equal");
    }

    @Test
    void getInstance() {
        ProductRepository productRepository = ProductRepository.getInstance();
        productRepository = ProductRepository.getInstance();
        productRepository = ProductRepository.getInstance();
        productRepository = ProductRepository.getInstance();
    }
}