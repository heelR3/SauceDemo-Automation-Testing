package model;
 
import java.util.Objects;
 
public class Product {
 
    private String name;
    private String price;
    private String description;
    private String imageUrl;
 
    // Default Constructor
    //page object model
    public Product() {
    }
 
    // Parameterized Constructor
    public Product(String name, String price, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }
 
    // Getters
    public String getName() {
        return name;
    }
 
    public String getPrice() {
        return price;
    }
 
    public String getDescription() {
        return description;
    }
 
    public String getImageUrl() {
        return imageUrl;
    }
 
    // Setters
    public void setName(String name) {
        this.name = name;
    }
 
    public void setPrice(String price) {
        this.price = price;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
 
    @Override
    public String toString() {
        return "Product {" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
 
    @Override
    public boolean equals(Object obj) {
 
        if (this == obj)
            return true;
 
        if (obj == null || getClass() != obj.getClass())
            return false;
 
        Product product = (Product) obj;
 
        return Objects.equals(name, product.name)
                && Objects.equals(price, product.price)
                && Objects.equals(description, product.description)
                && Objects.equals(imageUrl, product.imageUrl);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, imageUrl);
    }
 
}