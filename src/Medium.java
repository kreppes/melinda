import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Medium implements Comparable<Medium> , Serializable {
    private final int id;
    protected static int count;
    private String titel;
    private int jahr;
    private Medienverwaltung mv;
    private static final long serialVersionUID = -4843533061268188527L;

    public Medium(String titel, int jahr) {
        this.titel = titel;
        this.jahr = jahr;
        id = count++;

        //mv = new Medienverwaltung();
    }

    public Medienverwaltung getMv() {
        return mv;
    }

    public void setMv(Medienverwaltung mv) {
        this.mv = mv;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public abstract void druckeDaten(OutputStream stream);

    public int alter() {
        return LocalDate.now().getYear() - jahr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, jahr);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Medium m = (Medium) obj;

        if ((this.jahr != m.jahr) && (!this.titel.equals(m.titel)))
            return false;

        return true;
    }

    @Override
    public int compareTo(Medium o) {
        if (this.getJahr() < o.getJahr())
            return -1;
        if (this.getJahr() > o.getJahr())
            return 1;
        return 0;
    }
}
