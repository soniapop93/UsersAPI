package com.users.usersAPI;

import Database.DatabaseLogic;
import Users.User;
import Users.UserLogic;
import Utils.JsonConverter;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;


@RestController
public class Requests {
    DatabaseLogic databaseLogic = new DatabaseLogic();
    UserLogic userLogic = new UserLogic(databaseLogic);

    @GetMapping("/health")
    public ResponseEntity<?> checkHealth() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "OK");

        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.OK);
    }

    @GetMapping("/getUserByID")
    public ResponseEntity<?> getUserById(@RequestParam String id) {
        User user = userLogic.returnUserById(new BigInteger(id));

        if (user != null) {
            return new ResponseEntity<>(JsonConverter.userToJson(user).toMap(), HttpStatus.OK);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", "not found");
        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<?> addUser(@RequestBody String body) {
        boolean validJson = JsonConverter.checkJsonIsValidAddNewUser(body);

        if (validJson) {
            // TODO: finish to add new user in database


            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ERROR - JSON Body not valid!!!");

        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.BAD_REQUEST);

    }
}
