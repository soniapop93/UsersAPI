package Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String streetName;
    private String number;
    private String city;
    private String country;
    private String zipcode;

    public Address(String streetName, String number, String city, String country, String zipcode) {
        this.streetName = streetName;
        this.number = number;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }
}
