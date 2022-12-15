public class State {
    private String shortName;       // Zkratka státu
    private String name;            // Název státu
    private Double vatNormal;          // Plná sazba DPH v procentech
    private double vatReduced;      // Snížená sazba DPH v procentech
    private boolean isSpecialVat;   // Je peciální sazba DPH?

    public State(String shortName, String name, Double vatNormal, double vatReduced, boolean isSpecialVat) {
        this.shortName = shortName;
        this.name = name;
        this.vatNormal = vatNormal;
        this.vatReduced = vatReduced;
        this.isSpecialVat = isSpecialVat;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVatNormal() {
        return vatNormal;
    }

    public void setVatNormal(Double vatNormal) {
        this.vatNormal = vatNormal;
    }

    public double getVatReduced() {
        return vatReduced;
    }

    public void setVatReduced(double vatReduced) {
        this.vatReduced = vatReduced;
    }

    public boolean isSpecialVat() {
        return isSpecialVat;
    }

    public void setSpecialVat(boolean specialVat) {
        isSpecialVat = specialVat;
    }

    @Override
    public String toString() {
        return name+" ("+shortName+") : \t"+vatNormal+" %";
    }

    public String toStringOverLimit() {
        return name+" ("+shortName+") : \t"+vatNormal+" % ("+vatReduced+")";
    }

    public String toStringUnderLimit() {
        return shortName+", ";
    }

}
