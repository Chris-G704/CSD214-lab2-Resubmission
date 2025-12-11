package lab2;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";

    private final ArrayList<SaleableItem> saleableItems = new ArrayList<>();
    private final int currentItem = 0;

    private Scanner input;
    private final PrintStream out;

    // Default constructor for normal execution
    public App() {
        this(System.in, System.out);
    }

    // Constructor for testing
    public App(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.out = out;
    }

    // Run the application
    public void run() {
        populate(); // Add some initial data
        int choice = 0;
        while (choice != 99) {
            out.print(menu);
            choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    editItem();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    sellItem();
                    break;
                case 5:
                    listAny();
                    break;
                case 99:
                    out.println("Goodbye!");
                    break;
                default:
                    out.println("Invalid option.");
            }
        }
    }

    // Add item with menu
    public void addItem() {
        out.println("Add an item");
        int choice = 0;
        while (choice != 99) {
            out.println("1. Add Book");
            out.println("2. Add Magazine");
            out.println("3. Add DiscMag");
            out.println("4. Add Ticket");
            out.println("99. Exit");
            choice = input.nextInt();
            input.nextLine();

            if (choice == 99) break;

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addMagazine();
                    break;
                case 3:
                    addDiscMag();
                    break;
                case 4:
                    addTicket();
                    break;
                default:
                    out.println("Invalid option.");
            }
        }
    }

    // Add a book
    private void addBook() {
        out.print("Enter Title:\n");
        String title = input.nextLine();
        out.print("Enter Author:\n");
        String author = input.nextLine();
        out.print("Enter copies:\n");
        int copies = input.nextInt();
        out.print("Enter price:\n");
        double price = input.nextDouble();
        input.nextLine();

        Book book = new Book(System.currentTimeMillis(), title, price, copies, null, null, author);
        saleableItems.add(book);
    }

    // Add a magazine
    private void addMagazine() {
        out.print("Enter Title:\n");
        String title = input.nextLine();
        out.print("Enter copies:\n");
        int copies = input.nextInt();
        out.print("Enter price:\n");
        double price = input.nextDouble();
        input.nextLine();

        Magazine magazine = new Magazine(title, price, copies, null, null, copies, LocalDate.now());
        saleableItems.add(magazine);
    }

    // Add a disc magazine
    private void addDiscMag() {
        out.print("Enter Title:\n");
        String title = input.nextLine();
        out.print("Enter copies:\n");
        int copies = input.nextInt();
        out.print("Enter price:\n");
        double price = input.nextDouble();
        out.print("Has disc? (true/false):\n");
        boolean hasDisc = input.nextBoolean();
        input.nextLine();

        Magazine baseMag = new Magazine(title, price, copies, null, null, copies, LocalDate.now());
        DiscMag discMag = new DiscMag(baseMag, hasDisc);
        saleableItems.add(discMag);
    }

    // Add a ticket
    private void addTicket() {
        out.print("Enter description:\n");
        String description = input.nextLine();
        out.print("Enter price:\n");
        double price = input.nextDouble();
        input.nextLine();

        Ticket ticket = new Ticket(description, price);
        saleableItems.add(ticket);
    }

    // Add item directly (for testing)
    public void addItem(SaleableItem s) {
        saleableItems.add(s);
    }

    // Edit an item
    public void editItem() {
        out.println("Edit an item");
        listI(saleableItems);

        out.print("Choose an item to edit or 99 to exit:\n");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 99) return;
        if (choice < 1 || choice > saleableItems.size()) {
            out.println("Invalid choice.");
            return;
        }

        SaleableItem item = saleableItems.get(choice - 1);
        if (item instanceof Editable) {
            editItem((Editable) item);
        } else {
            out.println("This item cannot be edited.");
        }
    }

    // Edit an editable item
    public void editItem(Editable e) {
        if (e instanceof Book) {
            Book book = (Book) e;
            out.println("Edit Title (" + book.getTitle() + " [enter for no changes])");
            String title = input.nextLine();
            if (!title.isEmpty()) book.setTitle(title);

            out.println("Edit Author (" + book.getAuthor() + " [enter for no changes])");
            String author = input.nextLine();
            if (!author.isEmpty()) book.setAuthor(author);

            out.println("Edit copies (" + book.getCopies() + " [enter for no changes])");
            String copiesStr = input.nextLine();
            if (!copiesStr.isEmpty()) book.setCopies(Integer.parseInt(copiesStr));

            out.println("Edit price (" + book.getPrice() + " [enter for no changes])");
            String priceStr = input.nextLine();
            if (!priceStr.isEmpty()) book.setPrice(Double.parseDouble(priceStr));
        } else if (e instanceof Magazine) {
            Magazine mag = (Magazine) e;
            out.println("Edit Title (" + mag.getTitle() + " [enter for no changes])");
            String title = input.nextLine();
            if (!title.isEmpty()) mag.setTitle(title);

            out.println("Edit copies (" + mag.getCopies() + " [enter for no changes])");
            String copiesStr = input.nextLine();
            if (!copiesStr.isEmpty()) mag.setCopies(Integer.parseInt(copiesStr));

            out.println("Edit price (" + mag.getPrice() + " [enter for no changes])");
            String priceStr = input.nextLine();
            if (!priceStr.isEmpty()) mag.setPrice(Double.parseDouble(priceStr));
        }
    }

    // Delete an item
    public void deleteItem() {
        out.println("Delete an item");
        listI(saleableItems);

        out.print("Choose an item to delete or 99 to exit:\n");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 99) return;
        if (choice < 1 || choice > saleableItems.size()) {
            out.println("Invalid choice.");
            return;
        }

        saleableItems.remove(choice - 1);
        out.println("Item deleted.");
    }

    // Sell an item
    public void sellItem() {
        out.println("Sell an item");
        listI(saleableItems);

        out.print("Choose an item to sell or 99 to exit:\n");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 99) return;
        if (choice < 1 || choice > saleableItems.size()) {
            out.println("Invalid choice.");
            return;
        }

        SaleableItem item = saleableItems.get(choice - 1);
        out.print("How many copies to sell?\n");
        int qty = input.nextInt();
        input.nextLine();

        if (qty > item.getCopies()) {
            out.println("Not enough copies available.");
            return;
        }

        item.setCopies(item.getCopies() - qty);
        double total = item.getPrice() * qty;
        out.println("Sold " + qty + " copies. Total: $" + String.format("%.2f", total));

        if (item.getCopies() == 0) {
            saleableItems.remove(choice - 1);
            out.println("Item removed (no copies left).");
        }
    }

    // List items with filtering
    public void listAny() {
        out.println("All Items");
        out.println("----------");
        int choice = 0;
        while (choice != 99) {
            out.println("List");
            out.println("1. All");
            out.println("2. Books");
            out.println("3. Magazines");
            out.println("4. DiscMags");
            out.println("5. Tickets");
            out.println("99. Exit");
            choice = input.nextInt();
            input.nextLine();

            if (choice == 99) break;

            switch (choice) {
                case 1:
                    listI(saleableItems);
                    break;
                case 2:
                    ArrayList<SaleableItem> books = new ArrayList<>();
                    for (SaleableItem item : saleableItems) {
                        if (item instanceof Book && !(item instanceof Magazine)) {
                            books.add(item);
                        }
                    }
                    listI(books);
                    break;
                case 3:
                    ArrayList<SaleableItem> magazines = new ArrayList<>();
                    for (SaleableItem item : saleableItems) {
                        if (item instanceof Magazine && !(item instanceof DiscMag)) {
                            magazines.add(item);
                        }
                    }
                    listI(magazines);
                    break;
                case 4:
                    ArrayList<SaleableItem> discMags = new ArrayList<>();
                    for (SaleableItem item : saleableItems) {
                        if (item instanceof DiscMag) {
                            discMags.add(item);
                        }
                    }
                    listI(discMags);
                    break;
                case 5:
                    ArrayList<SaleableItem> tickets = new ArrayList<>();
                    for (SaleableItem item : saleableItems) {
                        if (item instanceof Ticket) {
                            tickets.add(item);
                        }
                    }
                    listI(tickets);
                    break;
                default:
                    out.println("Invalid option.");
            }
        }
    }

    // List items helper
    public void listI(Object o) {
        if (o instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            ArrayList<SaleableItem> items = (ArrayList<SaleableItem>) o;
            if (items.isEmpty()) {
                out.println("No items.");
            } else {
                for (int i = 0; i < items.size(); i++) {
                    out.println((i + 1) + ". " + items.get(i));
                }
            }
        }
    }

    // Populate with sample data
    public void populate() {
        for (int i = 0; i < 2; i++) {
            saleableItems.add(Util.getFakeBook());
            saleableItems.add(Util.getFakeMagazine());
            saleableItems.add(Util.getFakeDiscMag());
            saleableItems.add(Util.getFakeTicket());
        }
    }

    // Check if item exists
    public boolean findItemExists(SaleableItem s) {
        return saleableItems.contains(s);
    }

    // Find an item
    public SaleableItem findItem(SaleableItem s) {
        int index = saleableItems.indexOf(s);
        return index >= 0 ? saleableItems.get(index) : null;
    }

    // Get an item
    public SaleableItem getItem(SaleableItem s) {
        return findItem(s);
    }
}