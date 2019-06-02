import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Bild extends Medium {
    private String ort;

    public Bild(String titel, int jahr, String ort) {
        super(titel, jahr);
        this.ort = ort;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        PrintStream ps = new PrintStream(stream);
        ps.printf("ID: %d '%s' aufgenommen im jahr %d in %s\n",
                super.getId(), super.getTitel(), super.getJahr(), getOrt());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Bild b = (Bild) obj;

        if (!super.equals(b))
            return false;

        if (!this.ort.equals(b.ort))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ort);
    }
}
