package Users;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class User {
    private BigInteger id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Address address;
    private Card card;

    public User(
            BigInteger id,
            String username,
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            Address address,
            Card card) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.card = card;
    }
}
