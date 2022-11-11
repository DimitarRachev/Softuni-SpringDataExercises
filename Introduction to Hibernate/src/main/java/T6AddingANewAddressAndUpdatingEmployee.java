import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class T6AddingANewAddressAndUpdatingEmployee {

    private static final String ADD_ADDRESS =
            "UPDATE Employee e SET e.address = :newAddress WHERE e.lastName = :lastName";

    public static void main(String[] args) {
        final String lastName = new Scanner(System.in).nextLine();
        final EntityManager entityManager = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME).createEntityManager();
        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.getTransaction().begin();
        entityManager.persist(address);

        entityManager
                .createQuery(ADD_ADDRESS)
                .setParameter("newAddress", address)
                .setParameter("lastName", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

