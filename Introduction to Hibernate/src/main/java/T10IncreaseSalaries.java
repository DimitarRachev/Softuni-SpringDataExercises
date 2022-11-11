import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class T10IncreaseSalaries {

    private static final String PRINT_FORMAT = "%s %s ($%.2f)%n";

    private static final String SELECT_EMPLOYEES =
            "SELECT e FROM Employee e WHERE e.department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')";

    public static void main(String[] args) {
        final EntityManager entityManager = Persistence
                .createEntityManagerFactory(Constants.DATABASE_NAME)
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createQuery(SELECT_EMPLOYEES, Employee.class)
                .getResultList()
                .stream()
                .peek(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))))
                .peek(entityManager::persist)
                .forEach(e -> System.out.printf(PRINT_FORMAT, e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
