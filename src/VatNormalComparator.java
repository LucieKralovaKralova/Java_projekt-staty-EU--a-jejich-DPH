import java.util.Comparator;

public class VatNormalComparator implements Comparator <State> {
    @Override
    public int compare (State state1, State state2) {
        return state1.getVatNormal().compareTo(state2.getVatNormal());
    }
}
