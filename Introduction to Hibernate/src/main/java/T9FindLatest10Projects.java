import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.PrintStream;
import java.util.Comparator;

public class T9FindLatest10Projects {

    private static final String SELECT_PROJECTS =
            "SELECT p FROM Project p ORDER BY p.startDate desc ";
    private static final String PRINT_FORMAT =
            "Project name: %s%n" +
                    "        Project Description: %s%n" +
                    "        Project Start Date:%s%n" +
                    "        Project End Date: %s%n";

    public static void main(String[] args) {
        Persistence
                .createEntityManagerFactory(Constants.DATABASE_NAME)
                .createEntityManager()
                .createQuery(SELECT_PROJECTS, Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf(PRINT_FORMAT, p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));
    }
}
