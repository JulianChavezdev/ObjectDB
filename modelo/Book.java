@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String genre;
    private double price;
    private int availableCopies;
    private boolean isBestSeller;

    public Book(String title, String author, String isbn, int publicationYear,
                String genre, double price, int availableCopies, boolean isBestSeller) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.price = price;
        this.availableCopies = availableCopies;
        this.isBestSeller = isBestSeller;
    }

    public Book() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

    public boolean isBestSeller() { return isBestSeller; }
    public void setBestSeller(boolean bestSeller) { isBestSeller = bestSeller; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author +
               "', isbn='" + isbn + "', publicationYear=" + publicationYear +
               ", genre='" + genre + "', price=" + price +
               ", availableCopies=" + availableCopies +
               ", isBestSeller=" + isBestSeller + "}";
    }
}