package com.users.usersAPI;

import Database.DatabaseLogic;
import Users.User;
import Users.UserLogic;
import Utils.JsonConverter;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
