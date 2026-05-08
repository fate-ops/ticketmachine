package ch.bbw.pr;

import java.util.Scanner;

public class Ticketmaschine {
    private double total;
    private int PLZ1 = 8000;
    private int PLZ2 = 3000;
    private int PLZ3 = 1000;

    public double getTotal() {
        return total;
    }

    public int getPLZ1() {
        return PLZ1;
    }

    public int getPLZ2() {
        return PLZ2;
    }

    public int getPLZ3() {
        return PLZ3;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

class Main {
    public static void main(String[] args) {
        Ticketmaschine terminal = new Ticketmaschine();
        Scanner sc = new Scanner(System.in);
        int enteredPLZ = 0;
        char enteredOption = ' ';
        String zielLabel = "";
        String klasseLabel = "";
        String retourLabel = "";
        double bezahlt = 0;

        System.out.print("Geben Sie bitte eine gültige PLZ ein: ");
        enteredPLZ = sc.nextInt();
        if (enteredPLZ != terminal.getPLZ1() && enteredPLZ != terminal.getPLZ2() && enteredPLZ != terminal.getPLZ3()) {
            System.out.println("Invalide PLZ.");
            return;
        }

        if (enteredPLZ == terminal.getPLZ1()) {
            terminal.setTotal(20);
            zielLabel = "Zuerich (8000)";
        } else if (enteredPLZ == terminal.getPLZ2()) {
            terminal.setTotal(10);
            zielLabel = "Bern (3000)";
        } else if (enteredPLZ == terminal.getPLZ3()) {
            terminal.setTotal(15);
            zielLabel = "Lausanne (1000)";
        }

        System.out.print("Wie möchten Sie reisen? (a = Halbtax, b = 2. Kl, c = 1. Kl): ");
        enteredOption = sc.next().charAt(0);

        if (enteredOption == 'a') {
            terminal.setTotal(terminal.getTotal() * 0.5);
            klasseLabel = "Halbtax";
        } else if (enteredOption == 'b') {
            klasseLabel = "2. Klasse";
        } else if (enteredOption == 'c') {
            terminal.setTotal(terminal.getTotal() * 1.5);
            klasseLabel = "1. Klasse";
        } else {
            return;
        }

        System.out.print("Möchten Sie zusaetzlich ein Retourbillet? (y, n): ");
        enteredOption = sc.next().charAt(0);

        if (enteredOption == 'y') {
            terminal.setTotal(terminal.getTotal() * 2);
            retourLabel = "Ja";
        } else if (enteredOption == 'n') {
            retourLabel = "Nein";
        } else {
            return;
        }

        double ticketPreis = terminal.getTotal();

        while (terminal.getTotal() > 0) {
            System.out.printf("Offener Betrag: %.2f CHF%n", terminal.getTotal());
            System.out.print("Bitte geben Sie Ihre Zahlung ein: ");
            double eingabe = sc.nextDouble();
            bezahlt += eingabe;
            terminal.setTotal(terminal.getTotal() - eingabe);
        }

        int fLiber = 0;
        int zLiber = 0;
        int eLiber = 0;
        int ff = 0;
        int zwr = 0;
        int zer = 0;
        int fur = 0;

        int rueckgabeInRappen = (int) Math.round(terminal.getTotal() * -100);

        while (rueckgabeInRappen >= 500) {
            rueckgabeInRappen -= 500;
            fLiber++;
        }
        while (rueckgabeInRappen >= 200) {
            rueckgabeInRappen -= 200;
            zLiber++;
        }
        while (rueckgabeInRappen >= 100) {
            rueckgabeInRappen -= 100;
            eLiber++;
        }
        while (rueckgabeInRappen >= 50) {
            rueckgabeInRappen -= 50;
            ff++;
        }
        while (rueckgabeInRappen >= 20) {
            rueckgabeInRappen -= 20;
            zwr++;
        }
        while (rueckgabeInRappen >= 10) {
            rueckgabeInRappen -= 10;
            zer++;
        }
        while (rueckgabeInRappen >= 5) {
            rueckgabeInRappen -= 5;
            fur++;
        }

        double rueckgabe = (fLiber * 5 + zLiber * 2 + eLiber + ff * 0.5 + zwr * 0.2 + zer * 0.1 + fur * 0.05);

        System.out.println();
        System.out.println("+--------------------------------------+");
        System.out.println("|         FATELAND TICKET RECEIPT      |");
        System.out.println("+--------------------------------------+");

        System.out.printf("| %-36s |\n", "Ziel: " + zielLabel);
        System.out.printf("| %-36s |\n", "Tarif: " + klasseLabel);
        System.out.printf("| %-36s |\n", "Retour: " + retourLabel);

        System.out.println("|--------------------------------------|");

        System.out.printf("| %-36s |\n", String.format("Ticketpreis: %.2f CHF", ticketPreis));
        System.out.printf("| %-36s |\n", String.format("Bezahlt: %.2f CHF", bezahlt));
        System.out.printf("| %-36s |\n", String.format("Rückgeld: %.2f CHF", rueckgabe));

        System.out.println("|--------------------------------------|");

        System.out.printf("| %-36s |\n", String.format("5.00 CHF  x %d", fLiber));
        System.out.printf("| %-36s |\n", String.format("2.00 CHF  x %d", zLiber));
        System.out.printf("| %-36s |\n", String.format("1.00 CHF  x %d", eLiber));
        System.out.printf("| %-36s |\n", String.format("0.50 CHF  x %d", ff));
        System.out.printf("| %-36s |\n", String.format("0.20 CHF  x %d", zwr));
        System.out.printf("| %-36s |\n", String.format("0.10 CHF  x %d", zer));
        System.out.printf("| %-36s |\n", String.format("0.05 CHF  x %d", fur));

        System.out.println("+--------------------------------------+");
        System.out.printf("| %-36s |\n", "Danke für Ihren Einkauf.");
        System.out.printf("| %-36s |\n", "Gute Reise.");
        System.out.println("+--------------------------------------+");
    }
}