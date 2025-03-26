public class Product  {
    public String code;
    public String name;
    public double price;
    public double discountPrice;
    public double discount;

    public Product(String code, String name, double price, double discount){
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = setDiscountPrice(discount);
        this.discount = discount;


    }
    public double setDiscountPrice(double newDiscount){
        if (newDiscount == 0 || newDiscount  < 0 || newDiscount  > 1){
            this.discountPrice = this.price;
        }
        else{
            this.discountPrice = this.price * (1 - newDiscount);
        }
        return this.discountPrice;
    }
    public double getPrice(){
        return discountPrice;
    }
}

