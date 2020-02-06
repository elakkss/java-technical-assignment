package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class to handle offers like Buy 1 Milk and get 1 Milk free.
 * We can use the same class with multiple offers by changing the numbers.
 * Some example offers are
 * Buy 2 Bread and get 1 Bread free
 * Buy 3 Crisps and get 2 Crisps free.
 * You need to have the same product to be eligible for this offer.
 * You cannot combine different products for this offer.
 */
public class BuyXGetYFree implements IOffer {

    // Name of the product on which the offer applies
    private String discountProductName;

    // Total quantity to buy to be eligible for the offer
    private int buyQuantity;

    //Total quantity you will be getting free
    private int freeQuantity;

    public String getDiscountProductName() {
        return discountProductName;
    }

    public void setDiscountProductName(String discountProductName) {
        this.discountProductName = discountProductName;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public BuyXGetYFree(String discountProductName, int buyQuantity, int freeQuantity) {
        this.discountProductName = discountProductName;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }


    /**
     *
     * ApplyDiscount on the basket full of items.
     * This takes into consideration total quantity of the item in the basket .
     *
     * If we have Buy 1 Get 1 free offer
     * And basket has 2 Cans of Milk
     * then offer is calculated as
     * 2/(1+1) = 2/2*(Price of single can of Milk)
     * = 1*(0.49)
     * = 0.49
     *
     * This is the reduction amount to be applied in the basket.
     *
     * @param cart - List of items in the basket
     * @return - BigDecimal representation of the total amount to be discounted from the basket
     */
    @Override
    public BigDecimal applyDiscount(List<Item> cart) {
        BigDecimal reduction = BigDecimal.ZERO;
        int totalQ = 0;
        for(Item i : cart) {
            if(i.name().equals(discountProductName)) {
                totalQ++;
                reduction = i.price();
            }
        }
        int freeNo = totalQ/(buyQuantity+freeQuantity);
        reduction = reduction.multiply(new BigDecimal(freeNo));
        return reduction;
    }
}
