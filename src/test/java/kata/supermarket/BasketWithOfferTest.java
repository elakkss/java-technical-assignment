package kata.supermarket;

import kata.supermarket.discount.BuyXGetYFree;
import kata.supermarket.discount.OfferManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to check the basket behaves properly with the offer applied.
 * The offer being used in the class is Buy 1 Milk and Get 1 Milk free.
 *
 */
public class BasketWithOfferTest {

    @BeforeAll
    static void setOffer() {
        BuyXGetYFree buyOffer = new BuyXGetYFree("Milk",1,1);
        OfferManager.addOffer(buyOffer);
    }

    @DisplayName("basket provides its total value when containing products with offer")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketWithOfferTotal(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());

    }

    static Stream<Arguments> basketWithOfferTotal() {
        return Stream.of(
                emptyBasket(),
                singlePintMilk(),
                doublePintMilk(),
                tripleItemsByUnit(),
                multipleItemsPricedByWeightWithOffer(),
                multipleItemsPricedByWeightWOOffer()
        );
    }

    private static Arguments emptyBasket() {
        return Arguments.of("an empty basket", "0.00", Collections.emptyList());
    }

    private static Arguments singlePintMilk() {
        return Arguments.of("a single pint of milk", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments doublePintMilk() {
        return Arguments.of("two pints of milk, with Buy1 get 1 free offer", "0.49", Arrays.asList(aPintOfMilk(),aPintOfMilk()));
    }

    private static Arguments tripleItemsByUnit() {
        return Arguments.of("two pints of milk, with Buy1 get 1 free offer and with Bread", "2.04",
                Arrays.asList(aPintOfMilk(),aPintOfMilk(),aPackOfBread()));
    }

    private static Arguments multipleItemsPricedByWeightWithOffer() {
        return Arguments.of("weighed items with milk offer", "3.29",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), aPintOfMilk(),aPackOfBread(),aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedByWeightWOOffer() {
        return Arguments.of("weighed items without milk offer", "3.29",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(),aPackOfBread(),aPintOfMilk()));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), "Milk").oneOf();
    }

    private static Item aPackOfBread() {
        return new Product(new BigDecimal("1.55"),"Bread").oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"),"Sweet");
    }
}
