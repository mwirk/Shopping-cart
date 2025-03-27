public class Main {


    public static void main(String[] args) {
        Cart cart1 = new Cart();
        Product maslo = new Product("m12", "Maslo", 36.35, 0.0);
        Command addNewProduct = new AddProductCommand(cart1, maslo);
        cart1.setCommand(addNewProduct);
        cart1.makeAction();

        Product maslo2 = new Product("w12", "Amol", 35.35, 0.0);
        Command addNewProduct2 = new AddProductCommand(cart1, maslo2);
        cart1.setCommand(addNewProduct2);
        cart1.makeAction();

        Product maslo3 = new Product("mak12", "Makaron", 17.53, 0.0);
        Command addNewProduct3 = new AddProductCommand(cart1, maslo3);
        cart1.setCommand(addNewProduct3);
        cart1.makeAction();


        DiscountFunction oneTimeSale = new OneTimeSale30PercentOnChosenProduct(cart1.products.get(0));
        Discount discount = new Discount(cart1, oneTimeSale);
        Command apply = new ApplyDiscountCommand(cart1, discount);
        cart1.setCommand(apply);
        System.out.println(cart1.makeAction().getFirst().discountPrice);


        



    }
}