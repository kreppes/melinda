import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Audio extends Medium {
    private String interpret;
    private int dauer;

    public Audio(String titel, int jahr, String interpret, int dauer) {
        super(titel, jahr);
        this.interpret = interpret;
        this.dauer = dauer;

    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        PrintStream ps = new PrintStream(stream);
        ps.printf("ID: %d %s von %s im jahr %d Spieldauer: %d\n",
                super.getId(), super.getTitel(), getInterpret(), super.getJahr(), getDauer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),interpret,dauer);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Audio a = (Audio) obj;

        if (!super.equals(a))
            return false;

        if (!(this.interpret.equals(a.interpret)) && (this.dauer != a.dauer))
            return false;

        return true;
    }
}

