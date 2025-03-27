import java.util.List;
public interface DiscountFunction {
    List<Product> apply(Cart cart);
}


class SumMoreThan300 implements DiscountFunction {
    @Override
    public List<Product> apply(Cart cart) {
        if (cart.sum > 300) {
            for (int i = 0; i < cart.products.size(); i++) {
                double oldDiscount = cart.products.get(i).discount;
                cart.products.get(i).setDiscountPrice(oldDiscount + 0.05);
                cart.products.get(i).discount += 0.05;
            }
        }
        return cart.products;
    }
}

class ThirdCheapestProductIsFree implements DiscountFunction {
    @Override
    public List<Product> apply(Cart cart) {
        if (cart.products.size() > 2) {
            Product thirdCheapestProduct = cart.products.getLast();
            thirdCheapestProduct.setDiscountPrice(1);
            thirdCheapestProduct.discount = 1;
        }
        return cart.products;
    }
}

class SumMoreThan200FreeMug implements DiscountFunction {
    @Override
    public List<Product> apply(Cart cart) {
        if (cart.sum > 200) {
            Product mug = new Product("mug1", "Kubek firmowy", 50.45, 1.0);
            Command addNewProduct = new AddProductCommand(cart, mug);
            cart.setCommand(addNewProduct);
            cart.makeAction();
        }
        return cart.products;
    }
}
class OneTimeSale30PercentOnChosenProduct implements DiscountFunction {
    public Product chosenProduct;
    public OneTimeSale30PercentOnChosenProduct(Product chosenProduct){
        this.chosenProduct = chosenProduct;
    }
    @Override
    public List<Product> apply(Cart cart) {
        for (int i =0;i<cart.products.size();i++){
            if(cart.products.get(i) == chosenProduct){
                double oldDiscount = cart.products.get(i).discount;
                cart.products.get(i).setDiscountPrice(oldDiscount + 0.3);
                cart.products.get(i).discount += 0.3;

            }
        }
        return cart.products;
    }
}

