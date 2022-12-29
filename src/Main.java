import com.engeto.ifloop.Support;

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
        try (PrintWriter writer = new PrintWriter(new PrintWriter(OUT_FILENAME))) {

            // ZÁKLADNÍ VÝPISY
            System.out.println("Základní výpis států: ");
            // writer.println("Základní výpis států: ");
            List<State> states = register.getStates();
            states.forEach(System.out::println);
            // states.forEach(writer::println);


            System.out.println("Státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % " +
                    "a přitom nepoužívají speciální sazbu daně. " +
                    "Seřazení těchto států sestupně podle výše základní sazby DPH: ");
            writer.println("Státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % " +
                    "a přitom nepoužívají speciální sazbu daně. " +
                    "Seřazení těchto států sestupně podle výše základní sazby DPH: ");
            Collections.sort(states, new VatNormalComparator().reversed());

            for (State state : states) {
                if (state.getVatNormal() > 20 && !state.isSpecialVat()) {
                    String outputLine = state + " (" + state.getVatReduced() + " %)";
                    System.out.println(outputLine);
                    writer.println(outputLine);

                }
            }

            System.out.println("========================================");
            System.out.print("Sazba DPH 20% nebo nižší nebo používají speciální sazbu: ");
            writer.println("========================================");
            writer.print("Sazba DPH 20% nebo nižší nebo používají speciální sazbu: ");

            for (State state : states) {
                if (state.getVatNormal() <= 20 || state.isSpecialVat()) {
                    String outputLine = state.getShortName() + ", ";
                    System.out.print(outputLine);
                    writer.print(outputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ČTENÍ ZADANÉHO LIMITU A ZÁPIS VÝPISU DO SOUBORU
        System.out.println("\nZadejte vlastní výši DPH:");
        List<State> statesList = register.getStates();
            int limit = Support.safeReadInt();
            System.out.println("Limit výše DPH pro výpis států byl nastaven na: "+limit+" %");
            try (PrintWriter scannerWriter = new PrintWriter(new PrintWriter("vat-over-" + limit + ".txt"))) {
                Collections.sort(statesList, new VatNormalComparator().reversed());
                for (State state : statesList) {
                    if (state.getVatNormal() > limit && !state.isSpecialVat()) {
                        String outputLine = state + " (" + state.getVatReduced() + " %)";
                        // System.out.println(outputLine);
                        scannerWriter.println(outputLine);
                    }
                }
                // System.out.println("========================================");
                // System.out.print("Sazba DPH "+limit+" % nebo nižší nebo používají speciální sazbu: ");
                scannerWriter.println("========================================");
                scannerWriter.print("Sazba DPH "+limit+" % nebo nižší nebo používají speciální sazbu: ");

                for (State state : statesList) {
                    if (state.getVatNormal() <= limit || state.isSpecialVat()) {
                        String outputLine = state.getShortName() + ", ";
                        // System.out.print(outputLine);
                        scannerWriter.print(outputLine);
                    }
                }

            } catch (IOException e) {
                throw new StateException("Nastala chyba při zápisu do souboru: " + e.getLocalizedMessage());
            }

        }

    }





