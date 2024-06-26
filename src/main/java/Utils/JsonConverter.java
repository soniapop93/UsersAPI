package Utils;

import Users.Address;
import Users.Card;
import Users.User;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static User jsonToUser(String json) {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonUser = jsonObject.getJSONObject("user");

        String username = jsonUser.getString("username");
        String firstName = jsonUser.getString("firstName");
        String lastName = jsonUser.getString("lastName");
        String phoneNumber = jsonUser.getString("phoneNumber");
        String email = jsonUser.getString("email");

        JSONObject jsonAddress = jsonUser.getJSONObject("address");
        String streetName = jsonAddress.getString("streetName");
        String number = jsonAddress.getString("number");
        String city = jsonAddress.getString("city");
        String country = jsonAddress.getString("country");
        String zipcode = jsonAddress.getString("zipcode");

        JSONObject jsonCard = jsonUser.getJSONObject("card");
        String id = jsonCard.getString("id");
        String cardNumber = jsonCard.getString("cardNumber");
        String expirationDateStr = jsonCard.getString("expirationDate");

        Address address = new Address(streetName, number, city, country, zipcode);

        Date expirationDate;
        try {
            expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Card card = new Card(Long.parseLong(id), cardNumber, expirationDate);

        return new User(null, username, firstName, lastName, phoneNumber, email, address, card);
    }

    public static boolean checkJsonIsValidAddNewUser (String body) {
        JSONObject jsonBody = new JSONObject(body);
        if (!jsonBody.has("user")) {
            return false;
        }

        JSONObject jsonObjectUser = jsonBody.getJSONObject("user");

        if (!jsonObjectUser.has("username")) {
            return false;
        }

        if (!jsonObjectUser.has("firstName")) {
            return false;
        }

        if (!jsonObjectUser.has("lastName")) {
            return false;
        }

        if (!jsonObjectUser.has("phoneNumber")) {
            return false;
        }

        if (!jsonObjectUser.has("email")) {
            return false;
        }

        if (!jsonObjectUser.has("address")) {
            return false;
        }

        JSONObject jsonObjectAddress = jsonObjectUser.getJSONObject("address");

        if (!jsonObjectAddress.has("streetName")) {
            return false;
        }

        if (!jsonObjectAddress.has("number")) {
            return false;
        }

        if (!jsonObjectAddress.has("city")) {
            return false;
        }

        if (!jsonObjectAddress.has("country")) {
            return false;
        }

        if (!jsonObjectAddress.has("zipcode")) {
            return false;
        }

        if (!jsonObjectUser.has("card")) {
            return false;
        }

        JSONObject jsonObjectCard = jsonObjectUser.getJSONObject("card");

        if (!jsonObjectCard.has("id")) {
            return false;
        }

        if (!jsonObjectCard.has("cardNumber")) {
            return false;
        }

        if (!jsonObjectCard.has("expirationDate")) {
            return false;
        }

        return true;
    }


    // TODO: delete later after testing
//    public String test () {
//        JSONObject jsonObject = new JSONObject();
//
//        JSONObject jAddress = new JSONObject();
//        jAddress.put("streetName", "test");
//        jAddress.put("number", "test");
//        jAddress.put("city", "test");
//        jAddress.put("country", "test");
//        jAddress.put("zipcode", "test");
//
//        JSONObject jCard = new JSONObject();
//        jCard.put("id", "test");
//        jCard.put("cardNumber", "test");
//        jCard.put("expirationDate", "test");
//
//
//        JSONObject jUser = new JSONObject();
//        jUser.put("username", "test username");
//        jUser.put("firstName", "test");
//        jUser.put("lastName", "test");
//        jUser.put("phoneNumber", "");
//        jUser.put("email", "test");
//        jUser.put("address", jAddress);
//        jUser.put("card", jCard);
//
//        jsonObject.put("user", jUser);
//
//        return jsonObject.toString();
//    }
}
