package Users;

import Database.DatabaseLogic;
import org.json.JSONObject;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLogic {
    private DatabaseLogic db;

    public UserLogic(DatabaseLogic db) {
        this.db = db;
    }

    public User returnUserById(BigInteger userId) {
        ResultSet result = db.getUserById(userId);

        try {
            while(result.next()) {
                String username = result.getString("username");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String phoneNumber = result.getString("phoneNumber");
                String email = result.getString("email");
                String addressStr = result.getString("address");
                String cardStr = result.getString("card");

                Address address = returnAddress(addressStr);
                Card card = returnCard(cardStr);

                if (address != null && card != null) {
                    return new User(
                            userId,
                            username,
                            firstName,
                            lastName,
                            phoneNumber,
                            email,
                            address,
                           card);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Address returnAddress(String addressStr) {
        String[] splitAddress = addressStr.split(";");

        if (splitAddress.length == 5) {
            return new Address(
                    splitAddress[0], // street name
                    splitAddress[1], // number
                    splitAddress[2], // city
                    splitAddress[3], // country
                    splitAddress[4]); //zipcode
        }

        return null;
    }

    private Card returnCard(String cardStr) {
        String[] splitCard = cardStr.split(";");

        if (splitCard.length == 3) {
            Date expirationDate = null;
            try {
                expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(splitCard[2]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            return new Card(
                    Long.parseLong(splitCard[0]), // card id
                    splitCard[1], // card number
                    expirationDate); // expiration date -> format yyyy-mm-dd
        }

        return null;
    }
}
