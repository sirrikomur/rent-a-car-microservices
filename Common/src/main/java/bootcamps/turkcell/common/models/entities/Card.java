package bootcamps.turkcell.common.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String number;
    private String holder;
    private int expireYear;
    private int expireMonth;
    private String cvc;
}
