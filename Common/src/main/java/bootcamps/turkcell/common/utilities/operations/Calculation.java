package bootcamps.turkcell.common.utilities.operations;

import java.time.LocalDate;
import java.time.Period;

public class Calculation {

    public static int daysBetween(LocalDate startDate, LocalDate endDate) {
        Period differenceBetween = Period.between(startDate, endDate);
        return differenceBetween.getDays();
    }
}
