package lab2;

import java.time.LocalDate;

/**
 * Magazine class representing a magazine item
 */
public class Magazine extends Publication implements Editable {
    private Long id;
    private int orderQty;
    private LocalDate currentIssue;

    public Magazine(String title, double price, int copies, String isbn, String description, int orderQty, LocalDate currentIssue) {
        super(title, price, copies, isbn, description);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public LocalDate getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(LocalDate currentIssue) {
        this.currentIssue = currentIssue;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + " Editable{id=" + id + "} Order Qty: " + orderQty + " Current Issue: " + currentIssue;
    }
}