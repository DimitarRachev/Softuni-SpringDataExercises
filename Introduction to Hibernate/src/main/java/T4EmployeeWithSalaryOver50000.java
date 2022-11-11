import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class T4EmployeeWithSalaryOver50000 {
    private static final String EMPLOYEE_WITH_SALARY_OVER_50000 =
            "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.createQuery(EMPLOYEE_WITH_SALARY_OVER_50000, String.class).getResultList().forEach(System.out::println);
        entityManager.close();
    }
}
