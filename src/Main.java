import com.engeto.ifloop.Support;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static final String FILENAME = "vat-eu.csv";


    public static void main(String[] args) throws StateException {

        // NAČTENÍ SOUBORU
        RegisterOfStates register = new RegisterOfStates();
        try {
            register.readStatesFromFile(FILENAME);
        } catch (StateException e) {
            System.err.println("Chyba při načtení souboru: " + e.getLocalizedMessage());
        }

        try {
            // ZÁKLADNÍ VÝPISY
            System.out.println("Základní výpis států: ");
            List<State> states = register.getStates();
            states.forEach(System.out::println);

            System.out.println("Státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % " +
                    "a přitom nepoužívají speciální sazbu daně. " +
                    "Seřazení těchto států sestupně podle výše základní sazby DPH: ");

            register.writeToFile(20);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // ČTENÍ ZADANÉHO LIMITU A ZÁPIS VÝPISU DO SOUBORU
        System.out.println("\nZadejte vlastní výši DPH:");
        int limit = Support.safeReadInt();
        System.out.println("Limit výše DPH pro výpis států byl nastaven na: "+limit+" %");
        register.writeToFile(limit);
        }

    }





