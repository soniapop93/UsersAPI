package Users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Card {
    public long id;
    public String cardNumber;
    Date expirationDate;
}
