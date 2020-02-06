package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price).oneOf().price());
    }

    @Test
    void singleItemHasExpectedUnitPriceFromProductWithName() {
        final BigDecimal price = new BigDecimal("2.49");
        final String name = "Milk";
        Item item = new Product(price,name).oneOf();
        assertEquals(price, item.price());
        assertEquals(name, item.name());

    }
}