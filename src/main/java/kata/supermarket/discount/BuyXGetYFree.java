package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class BuyXGetYFree implements IOffer {

    private String discountProductName;
    private int buyQuantity;
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
        System.out.println("returning reduction = "+reduction);
        return reduction;
    }
}
