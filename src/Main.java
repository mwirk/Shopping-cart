public class Main {


    public static void main(String[] args) {
        Cart cart1 = new Cart();
        Product maslo = new Product("m12", "Maslo", 335.53, 0.0);
        Command addNewProduct = new AddProductCommand(cart1, maslo);
        cart1.setCommand(addNewProduct);
        cart1.makeAction();

        Product maslo2 = new Product("m12", "Maslo", 335.53, 0.0);
        Command addNewProduct2 = new AddProductCommand(cart1, maslo2);
        cart1.setCommand(addNewProduct2);
        cart1.makeAction();

        Product maslo3 = new Product("m12", "Maslo", 335.53, 0.0);
        Command addNewProduct3 = new AddProductCommand(cart1, maslo3);
        cart1.setCommand(addNewProduct3);
        cart1.makeAction();


        Command find_2_cheapest = new Find_N_Cheapest(cart1, 2);
        cart1.setCommand(find_2_cheapest);
        cart1.makeAction();
        System.out.println(find_2_cheapest.execute().getFirst().name);
        System.out.println(find_2_cheapest.execute().get(1).name);
        System.out.println(find_2_cheapest.execute().get(2).name);
        




//        DiscountFunction sum = new SumMoreThan200FreeMugDiscountFunction();
//        Discount discount = new Discount(cart1, sum);
//        Command applyFirstDiscount = new ApplyDiscountCommand(cart1, discount);
//        cart1.setCommand(applyFirstDiscount);
//        cart1.makeAction();
//
//        System.out.println(cart1.products.get(1).discountPrice);

    }
}