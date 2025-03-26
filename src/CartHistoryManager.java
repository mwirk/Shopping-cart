import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartHistoryManager {
    //LinkedHashMap zachowuje kolejnosc
    public Map<Command, List<Product>> commandHistory = new LinkedHashMap<>();
    private final Cart cart;

    public CartHistoryManager(Cart cart){
        this.cart = cart;
    }
    public void undo(Command command) {
        List<Product> previousState = commandHistory.get(command);
        if (previousState != null) {
            cart.products = new ArrayList<>(previousState);
        }
    }

    public void redo(Command command) {
        List<Product> previousState = commandHistory.get(command);
        if (previousState != null) {
            cart.products = new ArrayList<>(previousState);
            cart.setCommand(command);
            cart.makeAction();
        }
    }

}
