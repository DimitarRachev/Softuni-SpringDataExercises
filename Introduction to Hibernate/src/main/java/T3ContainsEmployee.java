import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class T3ContainsEmployee {
    private static final String SELECT_BY_FIRST_AND_LAST_NAME =
            "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String[] name = new Scanner(System.in).nextLine().split("\\s+");
        Query query = entityManager.createQuery(SELECT_BY_FIRST_AND_LAST_NAME, Long.class)
                .setParameter("fn", name[0])
                .setParameter("ln", name[1]);
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        System.out.println(result > 0 ? "Yes" : "No");
        entityManager.close();
    }
}
