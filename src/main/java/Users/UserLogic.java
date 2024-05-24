package Users;

import Database.DatabaseLogic;

import java.math.BigInteger;
import java.sql.ResultSet;

public class UserLogic {
    private DatabaseLogic db;

    public UserLogic(DatabaseLogic db) {
        this.db = db;
    }

    public User returnUserById(BigInteger userId) {
        ResultSet result = db.getUserById(userId);

        try {
            while(result.next()) {
                User user = new User(
                        userId,
                        result.getString("username"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                        result.getString("email"),
                        ; //todo: finish it
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
