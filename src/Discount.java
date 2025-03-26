import java.util.List;


public class Discount {
    public Cart cart;
    public DiscountFunction discountFunction;

    public Discount(Cart cart, DiscountFunction discountFunction) {
        this.cart = cart;
        this.discountFunction = discountFunction;
    }

    public List<Product> apply() {
        return discountFunction.apply(this.cart);
    }
}

