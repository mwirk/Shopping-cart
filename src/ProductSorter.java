import java.util.List;
import java.util.Comparator;

public class ProductSorter {

    public static void sort(List<Product> products) {
        Comparator<Product> com = new Comparator<Product>() {
            @Override
            public int compare(Product i, Product j) {

                int priceComparison = Double.compare(i.discountPrice, j.discountPrice);
                if (priceComparison != 0) {
                    return priceComparison;
                }
                return i.name.compareTo(j.name);
            }
        };
        products.sort(com);
    }
}
