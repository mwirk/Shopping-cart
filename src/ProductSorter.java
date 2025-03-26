import java.util.Comparator;
import java.util.List;

public class ProductSorter {

    public static void sort(List<Product> products){
        Comparator<Product> com = new Comparator<Product>() {
            @Override
            public int compare(Product i, Product j) {
                if(i.discountPrice < j.discountPrice)
                    return 1;
                else
                    return -1;
            }
        };
        products.sort(com);
    }
}
