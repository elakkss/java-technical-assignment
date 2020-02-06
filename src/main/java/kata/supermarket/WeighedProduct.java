package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final String name;


    public WeighedProduct(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
        this.name = "ToBeNamed";
    }

    public WeighedProduct(final BigDecimal pricePerKilo, final String name) {
        this.pricePerKilo = pricePerKilo;
        this.name = name;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public String name() {
        return name;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
