import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class T8GetEmployeeWithProject {

    private static final String GET_EMPLOYEE =
            "select e FROM Employee e WHERE e.id = :id";

    public static void main(String[] args) {
        int id = new Scanner(System.in).nextInt();
        EntityManager entityManager = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME).createEntityManager();
        Employee employee = entityManager
                .createQuery(GET_EMPLOYEE, Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle());
        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.println("      " + p.getName()));

        entityManager.close();
    }
}

