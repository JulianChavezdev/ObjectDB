import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book");
        BookDAO dao = new BookDAO(emf);

        /*Busqué esta opción para poder correr el programa y que no se duplique cada vez.
        usando un if, obteniendo el total de libros, si es 0 agregarlos, la siguiente vez que el programa corra, no se agregaran porque ya no es 0.*/

        if (dao.getTotalBookCount() == 0) {
            dao.insertBook(new Book("cien años de soledad", "Gabriel García Márquez", "978-8437604947", 1967, "Novela", 45.50, 5, true));
            dao.insertBook(new Book("Sapiens", "Yuval Noah Harari", "978-0062316097", 2011, "Ensayo", 30.00, 12, true));
            dao.insertBook(new Book("El resplandor", "Stephen King", "978-0307743657", 1977, "Horror", 22.00, 2, false));
            dao.insertBook(new Book("Elprincipito", "Antoine de Saint-Exupéry", "978-0156012195", 1943, "Infantil", 15.00, 20, true));
            dao.insertBook(new Book("La ciudad de los prodigios", "Eduardo Mendoza", "978-8432205552", 1986, "Novela", 18.90, 8, false));
            dao.insertBook(new Book("It", "Stephen King", "978-1501142970", 1986, "Horror", 35.00, 4, true));
            dao.insertBook(new Book("Breve historia del tiempo", "Stephen Hawking", "978-0553380163", 1988, "Ensayo", 28.00, 1, false));
            dao.insertBook(new Book("Harry Potter y la piedra filosofal", "J.K. Rowling", "978-8478884451", 1997, "Infantil", 40.00, 15, true));
            dao.insertBook(new Book("Patria", "Fernando Aramburu", "978-8490663196", 2016, "novela", 24.50, 10, true));
            dao.insertBook(new Book("El código Da Vinci", "Dan Brown", "978-0307474278", 2003, "novela", 12.00, 25, true));
        }


        Book Book1 = new Book("Capi y la fabrica de chocolates", "Julian Chavez", "000-03283893", 2024, "novela", 9.99, 3, false);
        dao.insertBook(Book1);
        System.out.println(dao.getBookById(Book1.getId()));
        dao.deleteBook(Book1.getId());
        System.out.println(dao.getBookById(Book1.getId()));


        System.out.println(dao.getAllBooks());
        System.out.println(dao.getBooksByGenre("Novel"));
        System.out.println(dao.getBooksWithLowStock());
        System.out.println(dao.getBooksSince2000());
        System.out.println(dao.getBooksByAuthorContaining("King"));
        System.out.println(dao.getTop5MostExpensiveBooks());


        System.out.println(dao.getTotalBookCount());
        System.out.println(dao.getAveragePrice());
        System.out.println(dao.getOldestBook());

        /* bucles foreach para recorrer las listas, teniendo row[0] que corresponde a la primera columna de la consulta 
        y row[1] que corresponde a la segunda columna de la consulta. */

for (Object[] row : dao.getBookCountByGenre()) {
            System.out.println(row[0] + ": " + row[1]);
        }

        for (Object[] row : dao.getAveragePriceByGenre()) {
            System.out.println(row[0] + " Promedio: " + row[1]);
        }

        for (Object[] row : dao.getGenresWithMoreThan100Copies()) {
            System.out.println(row[0] + " > 100: " + row[1]);
        }

        emf.close();
    }
}