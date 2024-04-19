import java.util.*;

class Product {
    String name;
    String category;
    String description;

    public Product(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }
}

class ProductConfigurator {
    Map<String, List<Product>> productsByCategory;
    List<Product> allProducts;

    public ProductConfigurator() {
        productsByCategory = new HashMap<>();
        allProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        allProducts.add(product);
        productsByCategory.computeIfAbsent(product.category, k -> new ArrayList<>()).add(product);
    }

    public List<Product> searchByName(String query) {
        List<Product> results = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.name.toLowerCase().contains(query.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }

    public List<Product> searchByCategory(String query) {
        return productsByCategory.getOrDefault(query, new ArrayList<>());
    }

    
}

public class Product_Configuration_System_Program3 {
    public static void main(String[] args) {
        ProductConfigurator configurator = new ProductConfigurator();

        configurator.addProduct(new Product("Laptop", "Electronics", "High-performance laptop with SSD"));
        configurator.addProduct(new Product("T-shirt", "Clothing", "100% cotton T-shirt"));
        configurator.addProduct(new Product("Book", "Books", "Bestselling novel by author X"));

        
        List<Product> searchResults = configurator.searchByName("laptop");
        for (Product result : searchResults) {
            System.out.println(result.name + " - " + result.category);
        }
    }
}
