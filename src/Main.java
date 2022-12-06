import java.util.List;

public class Main {
    public static  final String FILENAME = "vat-eu.csv";

    public static void main(String[] args) {

        // NAČTENÍ SOUBORU
    RegisterOfStates register = new RegisterOfStates();
    try {
        register.readStatesFromFile(FILENAME);
    } catch (StateException e) {
        System.err.println("Chyba při načtení souboru: "+e.getLocalizedMessage());
    }
        // ZÁKLADNÍ VÝPISY
    System.out.println("Základní výpis států: ");
    List<State> states = register.getStates();
    states.forEach(System.out::println);
    }
}