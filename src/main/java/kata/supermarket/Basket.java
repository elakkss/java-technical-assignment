package kata.supermarket;

import kata.supermarket.discount.OfferManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         *
         * Applying discounts on all the items in the basket.
         * All offers should extend the IOffer interface and applyDiscount method
         * should return a BigDecimal representing the reduction.
         *
         * @return - BigDecimal representation of total reduction of the basket.
         */
        private BigDecimal discounts() {
            return  OfferManager.OFFERS.stream().map(o-> o.applyDiscount(this.items))
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
