package lab2;

/**
 * Ticket class representing an event ticket
 */
public class Ticket implements SaleableItem {
    private String description;
    private double price;

    public Ticket(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return "Ticket";
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getCopies() {
        return 1;
    }

    @Override
    public void setCopies(int copies) {
        // Tickets don't have copies
    }

    @Override
    public String getIsbn() {
        return null;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket Description: " + description;
    }
}