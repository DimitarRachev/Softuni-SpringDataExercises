import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class T2ChangeCasing {
    private static final String UPDATE_TOWNS_WITH_NAME_LESS_THAN_5_CHARS =
            "update Town t set t.name = upper(t.name) where length(t.name) <= 5";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(UPDATE_TOWNS_WITH_NAME_LESS_THAN_5_CHARS);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
