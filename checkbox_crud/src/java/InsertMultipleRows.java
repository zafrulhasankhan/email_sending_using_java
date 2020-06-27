import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InsertMultipleRows {

    public static void main(String[] args) {
        List < User > list = new ArrayList < > ();
        String name="nasim";
        
        for(int j=0;j<4;j++){
        list.add(new User(100, name, "denial@gmail.com", "US", "123"));
        }
        String INSERT_USERS_SQL = "INSERT INTO users" + "  (id, name, email, country, password) VALUES " +
            " (?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/appdb?useSSL=false", "root", "");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            connection.setAutoCommit(false);
            for (Iterator < User > iterator = list.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getCountry());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.addBatch();
            }
            int[] updateCounts = preparedStatement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (BatchUpdateException batchUpdateException) {
            printBatchUpdateException(batchUpdateException);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static void printBatchUpdateException(BatchUpdateException b) {

        System.err.println("----BatchUpdateException----");
        System.err.println("SQLState:  " + b.getSQLState());
        System.err.println("Message:  " + b.getMessage());
        System.err.println("Vendor:  " + b.getErrorCode());
        System.err.print("Update counts:  ");
        int[] updateCounts = b.getUpdateCounts();

        for (int i = 0; i < updateCounts.length; i++) {
            System.err.print(updateCounts[i] + "   ");
        }
    }

}