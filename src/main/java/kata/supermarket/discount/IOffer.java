package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface IOffer {
    public BigDecimal applyDiscount(List<Item> cart);
}
