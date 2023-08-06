import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String firstName;
    private String lastName;
    private String birthNumber;

    public Person(String firstName, String lastName, String birthNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthNumber = birthNumber;
    }

    public String getBirthNumber() {
        return this.birthNumber;
    }

    public String getFullNameWithBN() {
        return this.firstName + " " + this.lastName + " " + this.birthNumber;
    }

    public Integer getAgeFromBN() {
        String birthNumber = getBirthNumber();
        Integer year = Integer.parseInt(birthNumber.substring(0, 2));
        Integer month = Integer.parseInt(birthNumber.substring(2, 4));
        Integer day = Integer.parseInt(birthNumber.substring(4, 6));

        if (month > 50) {
            month = month - 50;
        }

        int currentYear = LocalDate.now().getYear();
        int century = (year > currentYear - 2000) ? 1900 : 2000;
        year = year + century;
        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.of(currentYear, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

        return Period.between(birthDate, currentDate).getYears();
    }
}
