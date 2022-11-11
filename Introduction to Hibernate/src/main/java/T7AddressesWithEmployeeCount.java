import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class T7AddressesWithEmployeeCount {

    private static final String ALL_ADDRESSES =
            "SELECT a FROM Address a ORDER BY a.employees.size desc ";
    private static final String PRINT_FORMAT =
            "%s, %s - %d employees%n";

    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory(Constants.DATABASE_NAME)
                .createEntityManager();
        entityManager
                .createQuery(ALL_ADDRESSES, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(a -> System.out.printf(PRINT_FORMAT, a.getText(), a.getTown().getName(), a.getEmployees().size()));

        entityManager.close();
    }
}

