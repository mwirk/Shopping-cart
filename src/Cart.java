import java.util.*;


public class Cart {
    public double sum = 0;
    public List<Product> products = new ArrayList<>();
    CartHistoryManager commandHistory = new CartHistoryManager(this);

    public Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public List<Product> makeAction() {
        //Deep copy
        commandHistory.commandHistory.put(command, new ArrayList<>(products));
        return command.execute();
    }


}

class AddProductCommand implements Command {
    private final Cart cart;
    private final Product newProduct;
    public AddProductCommand(Cart cart, Product newProduct){
        this.cart = cart;
        this.newProduct = newProduct;
    }

    @Override
    public List<Product> execute() {
        cart.products.add(newProduct);
        ProductSorter.sort(cart.products);
        cart.sum += newProduct.discountPrice;
        return cart.products;
    }
}


class RemoveProductCommand implements Command {
    private final Cart cart;
    private final Product productToRemove;

    public RemoveProductCommand(Cart cart, Product productToRemove){
        this.cart = cart;
        this.productToRemove = productToRemove;
    }

    @Override
    public List<Product> execute() {
        cart.products.removeIf(prod -> prod == productToRemove);
        return cart.products;
    }
}

class SumUpCommand implements Command {
    private final Cart cart;

    public SumUpCommand(Cart cart){
        this.cart = cart;
    }

    @Override
    public List<Product> execute() {
        double sum = 0;
        for(int i=0;i<cart.products.size(); i++){
            sum += cart.products.get(i).discountPrice;
        }
        cart.sum = sum;
        return cart.products;
    }
}

class ApplyDiscountCommand implements Command{
    private final Cart cart;
    private final Discount discount;

    public ApplyDiscountCommand(Cart cart, Discount discount){
        this.cart = cart;
        this.discount = discount;
    }
    @Override
    public List<Product> execute()  {
        discount.apply();
        return cart.products;
    }
}

class FindCheapestCommand implements Command{
    private final Cart cart;
    private final List<Product> cheapest = new ArrayList<Product>();

    public FindCheapestCommand(Cart cart){
        this.cart = cart;
    }
    @Override
    public List<Product> execute() {
        Product minProduct = cart.products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
        cheapest.add(minProduct);
        return cheapest;
    }
}

class FindMostExpensiveCommand implements Command{
    private final Cart cart;
    private final List<Product> mostExpensive = new ArrayList<Product>();

    public FindMostExpensiveCommand(Cart cart){
        this.cart = cart;
    }
    @Override
    public List<Product> execute() {
        Product maxProduct = cart.products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
        mostExpensive.add(maxProduct);
        return mostExpensive;
    }
}


class Find_N_CheapestCommand implements Command{
    private final Cart cart;
    private final List<Product> cheapest = new ArrayList<Product>();
    private final List<Product> productsCopy;
    private final int howMany;
    public Find_N_CheapestCommand(Cart cart, int howMany){
        this.cart = cart;
        this.productsCopy = new ArrayList<Product>(cart.products);
        this.howMany = howMany;
    }
    @Override
    public List<Product> execute() {

        for (int j=0; j<howMany; j++) {

            Product minProduct = productsCopy.stream()
                    .min(Comparator.comparing(Product::getPrice))
                    .orElse(null);


            cheapest.add(minProduct);
            productsCopy.remove(minProduct);
        }
        return cheapest;
    }
}

class Find_N_Most_ExpensiveCommand implements Command{
    private final Cart cart;
    private final List<Product> mostExpensive = new ArrayList<Product>();
    private final List<Product> productsCopy;
    private final int howMany;
    public Find_N_Most_ExpensiveCommand (Cart cart, int howMany){
        this.cart = cart;
        this.productsCopy = new ArrayList<Product>(cart.products);
        this.howMany = howMany;
    }
    @Override
    public List<Product> execute() {

        for (int j=0; j<howMany; j++) {

            Product maxProduct = productsCopy.stream()
                    .max(Comparator.comparing(Product::getPrice))
                    .orElse(null);


            mostExpensive.add(maxProduct);
            productsCopy.remove(maxProduct);
        }

        return mostExpensive;
    }
}

