import entities.Employee;

import javax.persistence.Persistence;
import java.util.Scanner;

public class T11FindEmployeesByFirstName {

    private static final String SELECT_EMPLOYEES =
            "SELECT e FROM Employee e WHERE e.firstName LIKE :regex";
    private static final String PRINT_FORMAT ="%s %s - %s - ($%.2f)%n";

    public static void main(String[] args) {
        final String regex = new Scanner(System.in).nextLine();
        Persistence
                .createEntityManagerFactory(Constants.DATABASE_NAME)
                .createEntityManager()
                .createQuery(SELECT_EMPLOYEES, Employee.class)
                .setParameter("regex", regex+"%")
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT, e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }
}
