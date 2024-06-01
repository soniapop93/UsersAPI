package Users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Card {
    private long id;
    private String cardNumber;
    private Date expirationDate;

    public Card(long id, String cardNumber, Date expirationDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }
}
