import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BookDAO {

    private EntityManagerFactory emf;

    public BookDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertBook(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteBook(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Book getBookById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> getBooksByGenre(String genre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.genre = :genre", Book.class);
            query.setParameter("genre", genre);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> getBooksWithLowStock() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b WHERE b.availableCopies < 3", Book.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> getBooksSince2000() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b WHERE b.publicationYear >= 2000", Book.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> getBooksByAuthorContaining(String text) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:text)", Book.class);
            query.setParameter("text", "%" + text + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Book> getTop5MostExpensiveBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b ORDER BY b.price DESC", Book.class)
                .setMaxResults(5)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public long getTotalBookCount() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT COUNT(b) FROM Book b", Long.class)
                .getSingleResult();
        } finally {
            em.close();
        }
    }

    public double getAveragePrice() {
        EntityManager em = emf.createEntityManager();
        try {
            Double result = em.createQuery(
                "SELECT AVG(b.price) FROM Book b", Double.class)
                .getSingleResult();
            return result != null ? result : 0.0;
        } finally {
            em.close();
        }
    }

    public Book getOldestBook() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b ORDER BY b.publicationYear ASC", Book.class)
                .setMaxResults(1)
                .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getBookCountByGenre() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b.genre, COUNT(b) FROM Book b GROUP BY b.genre", Object[].class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getAveragePriceByGenre() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b.genre, AVG(b.price) FROM Book b GROUP BY b.genre", Object[].class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getGenresWithMoreThan100Copies() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b.genre, SUM(b.availableCopies) FROM Book b " +
                "GROUP BY b.genre HAVING SUM(b.availableCopies) > 100", Object[].class)
                .getResultList();
        } finally {
            em.close();
        }
    }
}