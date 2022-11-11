import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class T5EmployeesFromDepartment {

    private static final String EMPLOYEE_FROM_DEPARTMENT =
            "SELECT e FROM Employee e WHERE e.department.name = :dp ORDER BY e.salary asc, e.id";
    private static final String PRINT_EMP_FORMAT =
            "%s %s from %s - %.2f%n";

    public static void main(String[] args) {
        String department = new Scanner(System.in).nextLine();
        EntityManager entityManager = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME).createEntityManager();
        entityManager
                .createQuery(EMPLOYEE_FROM_DEPARTMENT, Employee.class)
                .setParameter("dp", department)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_EMP_FORMAT, e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));
        entityManager.close();
    }
}
