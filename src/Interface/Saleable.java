package lab2;

/**
 * Interface for items that can be sold in the bookstore
 */
public interface SaleableItem {
    String getTitle();
    double getPrice();
    void setPrice(double price);
    int getCopies();
    void setCopies(int copies);
    String getIsbn();
    String getDescription();
}