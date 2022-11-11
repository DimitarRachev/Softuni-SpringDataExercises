package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static src.Constants.COLUMN_LABEL_NAME;

public class T6RemoveVillain {

    private static final String GET_VILLAIN_BY_ID = "select v.name from villains v where v.id = ?";
    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID = "delete from minions_villains mv where mv.villain_id = ?";
    private static final String DELETE_VILLAIN_BY_VILLAIN_ID = "delete from villains v where v.id = ?";
    private static final String NO_VILLAIN = "No such villain was found";
    private static final String VILLAIN_DELETED_FORMAT = "%s was deleted%n%d minions released";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();
        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement selectVillain = connection.prepareStatement(GET_VILLAIN_BY_ID);
        selectVillain.setInt(1, villainId);

        ResultSet villainSet = selectVillain.executeQuery();
        if (!villainSet.next()) {
            System.out.println(NO_VILLAIN);
            connection.close();
            return;
        }

        String villainName = villainSet.getString(COLUMN_LABEL_NAME);


        try (
                PreparedStatement deleteMinionsStatement = connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID);
                PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN_BY_VILLAIN_ID);
        ) {
            connection.setAutoCommit(false);
            deleteMinionsStatement.setInt(1, villainId);
            int minionsCount = deleteMinionsStatement.executeUpdate();
            deleteVillainStatement.setInt(1, villainId);
            deleteVillainStatement.executeUpdate();
            connection.commit();
            System.out.printf(VILLAIN_DELETED_FORMAT, villainName, minionsCount);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}
