package Database;
import java.math.BigInteger;
import java.sql.*;
import java.util.Map;

public class DatabaseLogic {
    private Connection connection;
    final String dbName = "users_database";
    final Map<String, String> tableNames = Map.ofEntries(Map.entry("users", "Users"));

    public DatabaseLogic() {
        generateDatabase();
        createTableUsers();
    }

    private void generateDatabase() {
        createConnection(dbName);
    }

    private void createConnection(String name) {
        String strCon = String.format("jdbc:sqlite:%s.db", name);

        try {
            connection = DriverManager.getConnection(strCon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTableUsers() {
        String strSql = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "id BIGINT PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "phoneNumber TEXT, " +
                "email TEXT, " +
                "address TEXT, " +
                "card TEXT);", tableNames.get("users"));

        executeStatementUpdate(strSql);
    }

    private void executeStatementUpdate(String strSql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(strSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeStatementQuery(String strSql) {
        ResultSet result = null;

        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(strSql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet getUserById(BigInteger userId) {
        return getResultByColumnName(tableNames.get("users"), "id", userId.toString());
    }

    private ResultSet getResultByColumnName(String tableName, String columnName, String item) {
        String strSql = String.format("SELECT * FROM %s WHERE %s='%s'", tableName, columnName, item);
        ResultSet result = executeStatementQuery(strSql);

        return result;
    }
}
