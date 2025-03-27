
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartHistoryTest {
    Cart cart;
    @Before
    public void setUp() throws Exception{
        cart = new Cart();
    }
    @Test
    public void testCart_UndoAddProduct() {
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        cart.commandHistory.undo(add);
        assertEquals(0,cart.products.size());
    }
    @Test
    public void testCart_RedoAddProduct() {
        Product newProduct = new Product("m12", "Maslo", 36.35, 0.0);
        Command add = new AddProductCommand(cart,newProduct);
        cart.setCommand(add);
        cart.makeAction();
        cart.commandHistory.undo(add);
        cart.commandHistory.redo(add);
        assertEquals(1,cart.products.size());
    }

}