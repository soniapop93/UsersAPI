package Utils;

import Users.User;
import org.json.JSONObject;

public class JsonConverter {
    public static JSONObject userToJson(User user) {
        JSONObject jsonObject = new JSONObject();

        // address
        JSONObject addressJobject = new JSONObject();
        addressJobject.put("streetName", user.getAddress().getStreetName());
        addressJobject.put("number", user.getAddress().getNumber());
        addressJobject.put("city", user.getAddress().getCity());
        addressJobject.put("country", user.getAddress().getCountry());
        addressJobject.put("zipcode", user.getAddress().getZipcode());

        // card
        JSONObject cardJobject = new JSONObject();
        cardJobject.put("id", user.getCard().getId());
        cardJobject.put("cardNumber", user.getCard().getCardNumber());
        cardJobject.put("expirationDate", user.getCard().getExpirationDate());

        // user
        JSONObject userJobject = new JSONObject();
        userJobject.put("id", String.valueOf(user.getId()));
        jsonObject.put("username", user.getUsername());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("phoneNumber", user.getPhoneNumber());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("address", addressJobject);
        jsonObject.put("card", cardJobject);

        // final jobject
        jsonObject.put("user", userJobject);

        return jsonObject;
    }
}
