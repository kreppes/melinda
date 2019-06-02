import javax.swing.*;
import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Medienverwaltung {
    private LinkedList<Medium> liste;

    public Medienverwaltung() {
        liste = new LinkedList<Medium>();
    }

    public void zeigeMedien() {

        Collections.sort(liste);
        Iterator<Medium> it = liste.iterator();

        while (it.hasNext())
            it.next().druckeDaten(System.out);
    }

    public void aufnehmen(Medium m) {
        liste.add(m);
    }

    public LinkedList<Medium> getListe() {
        return liste;
    }

    public void sucheNeuesMedium() {

        Medium neu = liste.get(0);

        for (Medium m : liste) {
            if (neu.getJahr() < m.getJahr())
                neu = m;
        }
        neu.druckeDaten(System.out);
    }

    public double berechneErscheinungsJahr() {
        int anzahl = 0;
        double sum = 0.0;

        for (Medium m : liste) {
            sum += m.getJahr();
            anzahl++;
        }
        return sum / anzahl;
    }

    public void schreiben(File file) {
        try (BufferedWriter bw =  new BufferedWriter(new FileWriter(file));
             PrintWriter pw = new PrintWriter(bw)) {

            for (Medium m : liste) {
                if (m.getClass() == Bild.class) {
                    pw.printf("ID: %d %s aufgenommen im jahr %d in %s\n",
                            m.getId(), m.getTitel(), m.getJahr(), ((Bild) m).getOrt());
                } else {
                    pw.printf("ID: %d %s von %s im Jahr %d Spieldauer %d\n",
                            m.getId(), m.getTitel(), ((Audio) m).getInterpret(),
                            m.getJahr(),((Audio) m).getDauer());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speichern(List<Medium> liste, File file) {
        try (OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os)) {

            oos.writeObject(liste);
            JOptionPane.showMessageDialog(null,"Daten erfolgreich gespeichert");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Medium> laden(File file) {
        liste = new LinkedList<>();

        try (InputStream is = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(is)) {


            liste = (LinkedList<Medium>) oi.readObject();
            Medium.count = liste.size();
            JOptionPane.showMessageDialog(null,"Daten erfolgreich geladen");

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
