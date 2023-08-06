import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        Map<String, Person> people = new HashMap<String, Person>();

        System.out.println("Welcome to simple app of managing people.");
        System.out.println("You can add new people, delete or search.");
        while (true) {
            System.out.println("Which action you want to do? (write either create, delete or search or quit to close the app. )");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action = reader.readLine();
            switch (action) {
                case "create":
                    System.out.println("Write first name:");
                    String firstName = reader.readLine();
                    while(!Util.validateName(firstName)) {
                        System.out.println("First name can't be empty and can only contain letters.");
                        System.out.println("Write first name:");
                        firstName = reader.readLine();
                    }
                    System.out.println("Write last name:");
                    String lastName = reader.readLine();
                    while(!Util.validateName(lastName)) {
                        System.out.println("Last name can't be empty and can only contain letters.");
                        System.out.println("Write last name:");
                        lastName = reader.readLine();
                    }
                    System.out.println("Write birth number:");
                    String birthNumber = reader.readLine();
                    while(!Util.validateBirthNumber(birthNumber)) {
                        System.out.println("Birth number has to be valid! (YYMMDD/XXXX or YYMMDDXXXX)");
                        System.out.println("Write birth number:");
                        birthNumber = reader.readLine();
                    }
                    Person person = new Person(firstName, lastName, birthNumber);
                    if (people.get(birthNumber.replace("/", "")) != null) {
                        System.out.println("Person already exists in DB.");
                    } else people.put(birthNumber.replace("/", ""), person);
                    break;
                case "search":
                    System.out.println("Write birth number of person you are looking for:");
                    String searchBN = reader.readLine();
                    Person searchPerson = people.get(searchBN.replace("/", ""));
                    if (searchPerson != null) System.out.println(searchPerson.getFullNameWithAge());
                    else System.out.println("Not found.");
                    break;
                case "delete":
                case "remove":
                    System.out.println("Write birth number of person you want to delete:");
                    String deleteBN = reader.readLine();
                    Person deletePerson = people.get(deleteBN.replace("/", ""));
                    if (deletePerson != null) {
                        System.out.println(deletePerson.getFullNameWithAge());
                        System.out.println("Are you sure? (Y/N) This operation is irreversible!");
                        String agree = reader.readLine();
                        if (agree.toUpperCase() == "Y") {
                            people.remove(deleteBN.replace("/", ""));
                            System.out.println(deletePerson.getFullNameWithAge());
                            System.out.println("Has been deleted");
                        }
                    }
                    else System.out.println("Not found.");
                    break;
                case "quit":
                    System.out.println("Bye bye.");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
