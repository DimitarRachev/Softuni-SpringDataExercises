import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class T13RemoveTowns {

    private static final String SELECT_ADDRESSES =
            "SELECT a FROM Address a WHERE a.town.name = :townName";
    private static final String SELECT_TOWN =
            "SELECT t FROM Town t WHERE t.name = :townName";

    public static void main(String[] args) {

        final String townName = new Scanner(System.in).nextLine();
        EntityManager entityManager = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME).createEntityManager();
        entityManager.getTransaction().begin();

        try {
            List<Address> addresses = entityManager.createQuery(SELECT_ADDRESSES, Address.class).setParameter("townName", townName).getResultList();
            addresses.forEach(a -> {
                a.getEmployees().forEach(e -> e.setAddress(null));
                entityManager.remove(a);
            });

            Town town = entityManager.createQuery(SELECT_TOWN, Town.class).setParameter("townName", townName).getSingleResult();
            entityManager.remove(town);

            entityManager.getTransaction().commit();

            System.out.println(new StringBuilder()
                    .append(addresses.size())
                    .append(addresses.size() == 1 ? " address in " : " addresses in ")
                    .append(townName)
                    .append(" deleted"));
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
