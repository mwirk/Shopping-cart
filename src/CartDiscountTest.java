import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CartDiscountTest {
    Cart cart;
    @Before
    public void setUp() throws Exception{
        cart = new Cart();
    }
    @Test
    public void testCart_ApplyDiscount(){
        Product newProduct = new Product("m12", "Maslo", 367.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        DiscountFunction discFunc = new SumMoreThan300();
        Discount discount = new Discount(cart,discFunc);
        Command apply = new ApplyDiscountCommand(cart, discount);
        cart.setCommand(apply);
        cart.makeAction();
        assertEquals(348.954, cart.products.getFirst().discountPrice, 0.1);

    }
    @Test
    public void testCart_DiscountThirdCheapestProductIsFree(){
        Product newProduct = new Product("m12", "Maslo", 367.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        cart.makeAction();
        cart.makeAction();
        DiscountFunction discFunc = new ThirdCheapestProductIsFree();
        Discount discount = new Discount(cart,discFunc);
        Command apply = new ApplyDiscountCommand(cart, discount);
        cart.setCommand(apply);
        cart.makeAction();
        assertEquals(0, cart.products.getFirst().discountPrice, 0.0001);

    }
    @Test
    public void testCart_DiscountSumMoreThan200FreeMug(){
        Product newProduct = new Product("m12", "Maslo", 367.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        DiscountFunction discFunc = new SumMoreThan200FreeMug();
        Discount discount = new Discount(cart,discFunc);
        Command apply = new ApplyDiscountCommand(cart, discount);
        cart.setCommand(apply);
        cart.makeAction();
        assertEquals("Kubek firmowy", cart.products.getFirst().name);

    }

    @Test
    public void testCart_DiscountOneTimeSale30PercentOnChosenProduct(){
        Product newProduct = new Product("m12", "Maslo", 367.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        DiscountFunction discFunc = new OneTimeSale30PercentOnChosenProduct(newProduct);
        Discount discount = new Discount(cart,discFunc);
        Command apply = new ApplyDiscountCommand(cart, discount);
        cart.setCommand(apply);
        cart.makeAction();
        assertEquals(0.3, cart.products.getFirst().discount, 0.001);

    }
}
