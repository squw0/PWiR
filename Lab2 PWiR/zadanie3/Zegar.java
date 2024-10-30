public class Zegar {
    private int godzina;
    private int minuta;
    private int sekunda;
    private boolean format12Godzinny;

    public Zegar() {
        this.godzina = 0;
        this.minuta = 0;
        this.sekunda = 0;
        this.format12Godzinny = false;
    }
    public void nastaw(int godzina, int minuta, int sekunda) {
        if (godzina >= 0 && godzina < 24 && minuta >= 0 && minuta < 60 && sekunda >= 0 && sekunda < 60) {
            this.godzina = godzina;
            this.minuta = minuta;
            this.sekunda = sekunda;
            System.out.println("Ustawiono czas na: " + wypisz());
        } else {
            System.out.println("Nieprawidłowe wartości czasu.");
        }
    }
    public String wypisz() {
        int godzinaDoWyswietlenia = godzina;
        String suffix = "";

        if (format12Godzinny) {
            suffix = (godzina >= 12) ? " PM" : " AM";
            godzinaDoWyswietlenia = (godzina % 12 == 0) ? 12 : godzina % 12;
        }

        return String.format("%02d:%02d:%02d%s", godzinaDoWyswietlenia, minuta, sekunda, suffix);
    }
    public void format(boolean format12Godzinny) {
        this.format12Godzinny = format12Godzinny;
        System.out.println("Zmieniono format na " + (format12Godzinny ? "12-godzinny" : "24-godzinny"));
    }
    public void tykniecie() {
        sekunda++;
        if (sekunda == 60) {
            sekunda = 0;
            minuta++;
            if (minuta == 60) {
                minuta = 0;
                godzina++;
                if (godzina == 24) {
                    godzina = 0;
                }
            }
        }
        System.out.println("Aktualny czas po tyknięciu: " + wypisz());
    }
}
