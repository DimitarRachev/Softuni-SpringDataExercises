import entities.Department;
import entities.Employee;

import javax.persistence.Persistence;

public class T12EmployeesMaximumSalaries {

    private static final String SELECT_DEPARTMENTS =
            "SELECT e.department.name,  MAX(e.salary)  FROM Employee e GROUP BY e.department.id HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {
        Persistence
                .createEntityManagerFactory(Constants.DATABASE_NAME)
                .createEntityManager()
                .createQuery(SELECT_DEPARTMENTS, Object[].class)
                .getResultList()
                .forEach(d -> System.out.println(d[0] + " " + d[1]));
    }
}
