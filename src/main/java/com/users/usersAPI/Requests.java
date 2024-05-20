package com.users.usersAPI;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Requests {

    @GetMapping("/health")
    public ResponseEntity<?> checkHealth() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "OK");

        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.OK);
    }

    @GetMapping("/getUserByID")
    public ResponseEntity<?> getUserById(@RequestParam String id) {
        //todo: finish it
    }
}
