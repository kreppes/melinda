import javax.swing.*;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Medienverwaltung mv = new Medienverwaltung();

    public void start() {
        boolean schleife = true, check = false;
        File file = new File(".\\Medienliste.ser");
        Scanner in = new Scanner(System.in);
        int wahl = 0;
        do {
            System.out.println("-1-\tAudio aufnehmen");
            System.out.println("-2-\tBild aufnehmen");
            System.out.println("-3-\tZeige alle Medien");
            System.out.println("-4-\tZeige neues Medium");
            System.out.println("-5-\tSchreibe Medien in Datei");
            System.out.println("-6-\tBerechne durchschnittliches Erscheinungsjahr");
            System.out.println("-7-\tSpeicher Medien");
            System.out.println("-8-\tLade Medien");
            System.out.println("-9-\tBeenden");

            do {
                try {
                    System.out.print("---\tWaehle aus: ");
                    wahl = in.nextInt();
                    check = true;
                } catch (InputMismatchException iE) {
                    System.err.println("Falsche eingabe");
                    in.next();
                }
            } while (!check);

            switch (wahl) {
                case 1:
                    audioAufnehmen();
                    break;

                case 2:
                    bildAufnehmen();
                    break;

                case 3:
                    mv.zeigeMedien();
                    System.out.println();
                    break;

                case 4:
                    mv.sucheNeuesMedium();
                    System.out.println();
                    break;

                case 5:
                   datenSchreiben();
                    break;

                case 6:
                    System.out.printf("Durchschnittliches Erscheinungsjahr: %.2f\n"
                            , mv.berechneErscheinungsJahr());
                    break;

                case 7:
                    mv.speichern(mv.getListe(), file);
                    break;

                case 8:
                    mv.laden(file);

                    break;

                case 9:
                    schleife = false;
                    System.out.println("Aufwiedersehen\n");
                    break;

                default:
                    System.err.println("Falsche Eingabe");
            }
        }while (schleife);
    }

    public void bildAufnehmen() {
        boolean test = true;

        String sJahr = "", titel, ort;
        int jahr = 0;

        titel = (JOptionPane.showInputDialog("Titel"));
        ort = (JOptionPane.showInputDialog("Ort"));

        if (titel == null || ort == null) {
            JOptionPane.showMessageDialog(null,"Bild nicht aufgenommen");
            return;
        }

        do {
            try {
                sJahr = (JOptionPane.showInputDialog("Jahr"));
                jahr = Integer.parseInt(sJahr);
                //jahr = Integer.parseInt(JOptionPane.showInputDialog(null, "Jahr Eingeben"));
                test = false;
            } catch (NumberFormatException e) {
                if (sJahr == null) {
                    JOptionPane.showMessageDialog(null,"Bild nicht aufgenommen");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Bitte Gültiges Jahr Eingeben");
            }
        } while (test);

        Bild bild = new Bild(titel,jahr,ort);
        mv.aufnehmen(bild);
        JOptionPane.showMessageDialog(null, "Bild erfolgreich aufgenommen");
    }

    public void audioAufnehmen() {
        boolean test = true;
        String inter, titel, sDauer = "", sJahr = "";
        int jahr = 0, dauer = 0;

        titel = (JOptionPane.showInputDialog("Titel"));
        inter = (JOptionPane.showInputDialog("Interpreter"));

        if (titel == null || inter == null) {
            JOptionPane.showMessageDialog(null,"Audio nicht aufgenommen");
            return;
        }

        do {
            try {
                sJahr = (JOptionPane.showInputDialog("Jahr"));
                jahr = Integer.parseInt(sJahr);
                test = false;
            } catch (NumberFormatException nE) {
                if (sJahr == null) {
                    JOptionPane.showMessageDialog(null,"Audio nicht aufgenommen");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Bitte gültiges Jahr eingeben");
            }
        } while (test);

        do {
            test = true;
            try {
                sDauer = JOptionPane.showInputDialog("Dauer");
                dauer = Integer.parseInt(sDauer);
                test = false;
            }  catch (NumberFormatException e) {
                if (sDauer == null) {
                    JOptionPane.showMessageDialog(null,"Audio nicht aufgenommen");
                    return;
                }
                JOptionPane.showMessageDialog(null,"Bitte gültigen Wert eingeben");
            }
        } while (test);
        Audio audio = new Audio(titel,jahr,inter,dauer);
        mv.aufnehmen(audio);
        JOptionPane.showMessageDialog(null, "Audio erfolgreich aufgenommen");
    }

    public void datenSchreiben() {
        File file = null;
        String filename = null;
        int wahl;
        boolean check;

        do {
            try {
            filename = JOptionPane.showInputDialog("Geben sie einen Datennamen ein");
            if (filename == null)
                return;

            if ( filename.equals("")) {
                throw new EmptyFilenameException("Sie müssen eine datei angeben");
            }
            file = new File(filename + ".txt");
            check = false;
            } catch (EmptyFilenameException e) {
                wahl = JOptionPane.showConfirmDialog(null,
                        "Dateiname ist leer! Neuen Dateinamen wählen?");

                if (wahl == JOptionPane.YES_OPTION) {
                    check = true;
                } else {
                    return;
                }
            }

        } while (check);
        if (file != null)
            mv.schreiben(file);
    }
}
