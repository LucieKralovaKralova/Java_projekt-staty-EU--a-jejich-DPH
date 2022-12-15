import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static final String FILENAME = "vat-eu.csv";
    public static final String OUT_FILENAME = "vat-over-20.txt";

    public static void main(String[] args) throws StateException {

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
        List<State> statesOver = new ArrayList<>();
        for (State state : states) {
            if (state.getVatNormal() > 20 && !state.isSpecialVat()) {
                String outputLine = state + " (" + state.getVatReduced() + " %)";
                System.out.println(outputLine);
                statesOver.add(state);
                try {
                    register.writeStatesToFile(OUT_FILENAME, statesOver, outputLine);
                } catch (StateException e) {
                   System.err.println(e.getLocalizedMessage());

                // register.writeStatesToFile(OUT_FILENAME);

                  }
            }
        }

        System.out.println("========================================");
        System.out.print("Sazba VAT 20% nebo nižší nebo používají speciální sazbu: ");
        List<State> statesUnder = new ArrayList<>();
        for (State state : states) {
            if (state.getVatNormal() <= 20 || state.isSpecialVat()) {
                String outputLine = state.getShortName() + ", ";
                System.out.print(outputLine);
                statesUnder.add(state);
                // try {
                //  register.writeStatesToFile(OUT_FILENAME, statesUnder);
                //  } catch (StateException e) {
                //    System.err.println(e.getLocalizedMessage());
                //  }
            }
        }
    }

            // try {
            // register.writeStatesToFile(OUT_FILENAME, statesOver);
            //} catch (StateException e) {
            //    System.err.println(e.getLocalizedMessage());
            // }

        }

        // public static void writeStatesToFile (String filename, List<State>stateList) throws StateException{
        //try (PrintWriter writer = new PrintWriter(new PrintWriter(filename))){
        //   for (State state: stateList){
        //      writer.println(state.toString());
        //  }} catch (IOException e){
        // throw new StateException("Nastala chyba při zápisu do souboru: "+e.getLocalizedMessage());
        //  }
        //  }


