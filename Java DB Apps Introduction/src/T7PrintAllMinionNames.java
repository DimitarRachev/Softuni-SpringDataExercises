package src;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static src.Constants.COLUMN_LABEL_NAME;

public class T7PrintAllMinionNames {

    private static final String GET_ALL_MINIONS_NAMES = "select m.name from minions m order by id";


    public static void main(String[] args) throws SQLException {


        final Connection connection = Utils.getSQLConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_MINIONS_NAMES);
        ResultSet resultSet = statement.executeQuery();

        ArrayDeque<String> nameQueue = new ArrayDeque<>();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            nameQueue.add(resultSet.getString(COLUMN_LABEL_NAME));
            names.add(resultSet.getString(COLUMN_LABEL_NAME));
        }

        //First variant
//        int i = 1;
//        while (!nameQueue.isEmpty()) {
//            if (i % 2 != 0) {
//                System.out.println(nameQueue.pollFirst());
//            } else {
//                System.out.println(nameQueue.pollLast());
//            }
//            i++;
//        }

        //Second variant
        for (int i = 0; i < names.size(); i++) {
            if ((i + 1) % 2 != 0) {
                System.out.println(names.get(i));
            } else {
                System.out.println(names.get(names.size() - i));
            }
        }

        connection.close();
    }
}
