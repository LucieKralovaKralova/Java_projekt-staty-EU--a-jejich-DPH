import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static  final String FILENAME = "vat-eu.csv";

    public static void main(String[] args) {

        // NAČTENÍ SOUBORU
        RegisterOfStates register = new RegisterOfStates();
        try {
            register.readStatesFromFile(FILENAME);
        } catch (StateException e) {
            System.err.println("Chyba při načtení souboru: " + e.getLocalizedMessage());
        }
        // ZÁKLADNÍ VÝPISY
        System.out.println("Základní výpis států: ");
        List<State> states = register.getStates();
        states.forEach(System.out::println);

        System.out.println("Státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % " +
                "a přitom nepoužívají speciální sazbu daně. " +
                "Seřazení těchto států sestupně podle výše základní sazby DPH: ");
        Collections.sort(states, new VatNormalComparator().reversed());
        for (State state : states) {
            if (state.getVatNormal() > 20 && !state.isSpecialVat()) {

              System.out.println(state+" ("+state.getVatReduced()+" %)");
            }
         }

        System.out.println("========================================");
        System.out.print("Sazba VAT 20% nebo nižší nebo používají speciální sazbu: ");
        for (State state : states) {
            if (state.getVatNormal() < 20 || state.isSpecialVat()) {
                System.out.print(state.getShortName()+", ");
            }
        }


    }
}