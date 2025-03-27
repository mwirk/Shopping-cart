
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartCommandTest {
    Cart cart;
    @Before
    public void setUp() throws Exception{
        cart = new Cart();
    }
    @Test
    public void testCart_AddProduct(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        assertEquals(newProduct,cart.makeAction().getFirst());
    }
    @Test
    public void testCart_RemoveProduct(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Command remove = new RemoveProductCommand(cart, newProduct);
        cart.setCommand(remove);
        cart.makeAction();
        assertEquals(0, cart.products.size());
    }
    @Test
    public void testCart_SumUp(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Product newProduct2 = new Product("m122", "Maslo", 76.35, 0.0);
        Command add2 = new AddProductCommand(cart,newProduct2);
        cart.setCommand(add2);
        cart.makeAction();
        Command sumUp = new SumUpCommand(cart);
        cart.setCommand(sumUp);
        cart.makeAction();
        assertEquals(112.7, cart.sum, 0.01);
    }
    @Test
    public void testCart_FindCheapest(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Product newProduct2 = new Product("m122", "Parasol", 6.35, 0.0);
        Command add2 = new AddProductCommand(cart,newProduct2);
        cart.setCommand(add2);
        cart.makeAction();
        Command chepeast = new FindCheapestCommand(cart);
        cart.setCommand(chepeast);
        assertEquals(newProduct2, cart.makeAction().getFirst());
    }
    @Test
    public void testCart_Find_N_Cheapest(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Product newProduct2 = new Product("m122", "Parasol", 6.35, 0.0);
        Command add2 = new AddProductCommand(cart,newProduct2);
        cart.setCommand(add2);
        cart.makeAction();
        Product newProduct3 = new Product("m1", "Baton", 5.35, 0.0);
        Command add3 = new AddProductCommand(cart,newProduct3);
        cart.setCommand(add3);
        cart.makeAction();
        Command n_chepeast = new Find_N_CheapestCommand(cart,2);
        cart.setCommand(n_chepeast);
        List<Product> expected = new ArrayList<>();
        expected.add(newProduct3);
        expected.add(newProduct2);
        assertEquals(expected, cart.makeAction());
    }
    @Test
    public void testCart_FindMostExpensive(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Product newProduct2 = new Product("m122", "Parasol", 6.35, 0.0);
        Command add2 = new AddProductCommand(cart,newProduct2);
        cart.setCommand(add2);
        cart.makeAction();
        Command mostExpensive = new FindMostExpensiveCommand(cart);
        cart.setCommand(mostExpensive);
        assertEquals(newProduct, cart.makeAction().getFirst());
    }
    @Test
    public void testCart_Find_N_Most_Expensive(){
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        Product newProduct2 = new Product("m122", "Parasol", 6.35, 0.0);
        Command add2 = new AddProductCommand(cart,newProduct2);
        cart.setCommand(add2);
        cart.makeAction();
        Product newProduct3 = new Product("m1", "Baton", 5.35, 0.0);
        Command add3 = new AddProductCommand(cart,newProduct3);
        cart.setCommand(add3);
        cart.makeAction();
        Command n_chepeast = new Find_N_Most_ExpensiveCommand(cart,2);
        cart.setCommand(n_chepeast);
        List<Product> expected = new ArrayList<>();
        expected.add(newProduct);
        expected.add(newProduct2);
        assertEquals(expected, cart.makeAction());
    }


    }