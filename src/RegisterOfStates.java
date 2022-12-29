import com.engeto.ifloop.Support;

import java.io.*;
import java.util.*;

public class RegisterOfStates {
    public static final String DELIMITER = "\t";
    public List<State> states = new ArrayList<>();

    public void addState(State newState) {
        states.add(newState);
    }

    public void readStatesFromFile(String filename) throws StateException {
        String nextLine = null;
        String[] items = new String[0];
        String shortName = null;
        String name = null;
        double vatNormal = 0;
        double vatReduced = 0;
        int lineNumber = 0;
        boolean isSpecialVat = false;
        State newState = null;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                items = nextLine.split(DELIMITER);
                shortName = items[0];
                name = items[1];
                //vatNormal = Integer.parseInt(items[2]);
                //vatReduced = Integer.parseInt(items[3]);
                vatNormal = Double.parseDouble(items[2]);
                vatReduced = Double.parseDouble(items[3]);
                isSpecialVat = Boolean.parseBoolean(items[4]);
                newState = new State(shortName, name, vatNormal, vatReduced, isSpecialVat);
                addState(newState);
            }
        } catch (FileNotFoundException e) {
            throw new StateException("Nepodařilo se najít soubor " + filename + ":" + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new StateException("Nesprávný formát zadané hodnoty DPH " +
                    "(V případě desetinného čísla je nutno použít desetinnou tečku!) Číslo řádku: " + lineNumber +
                    "\n" + e.getLocalizedMessage());
        }
    }

    public List<State> getStates() {
        return new ArrayList<>(states);
    }


}



