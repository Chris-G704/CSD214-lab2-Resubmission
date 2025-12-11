package lab2;

/**
 * DiscMag class representing a magazine with a disc
 */
public class DiscMag extends Magazine {
    private boolean hasDisc;

    public DiscMag(Magazine magazine, boolean hasDisc) {
        super(magazine.getTitle(), magazine.getPrice(), magazine.getCopies(),
                magazine.getIsbn(), magazine.getDescription(),
                magazine.getOrderQty(), magazine.getCurrentIssue());
        this.hasDisc = hasDisc;
    }

    public boolean hasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public String toString() {
        return super.toString() + " Has Disc: " + hasDisc;
    }
}