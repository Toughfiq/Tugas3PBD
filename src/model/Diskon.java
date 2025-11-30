package model;

/**
 * Kelas Diskon - Inheritance dari MenuItem
 * Menerapkan konsep inheritance dan polymorphism
 */
public class Diskon extends MenuItem {
    private double persenDiskon; // dalam persen (0-100)

    public Diskon(String nama, double harga, double persenDiskon) {
        super(nama, harga, "Diskon");
        this.persenDiskon = persenDiskon;
    }

    public double getPersenDiskon() {
        return persenDiskon;
    }

    public void setPersenDiskon(double persenDiskon) {
        this.persenDiskon = persenDiskon;
    }

    // Menghitung harga setelah diskon
    public double hitungHargaDiskon() {
        return getHarga() - (getHarga() * persenDiskon / 100);
    }

    // Polymorphism: Override metode abstrak
    @Override
    public void tampilMenu() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMASI DISKON                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Nama Item      : %-37s ║%n", getNama());
        System.out.printf("║ Harga Normal   : Rp %-34.0f ║%n", getHarga());
        System.out.printf("║ Diskon         : %-36.0f%% ║%n", persenDiskon);
        System.out.printf("║ Harga Diskon   : Rp %-34.0f ║%n", hitungHargaDiskon());
        System.out.printf("║ Hemat          : Rp %-34.0f ║%n", getHarga() - hitungHargaDiskon());
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    @Override
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + getHarga() + "|" + persenDiskon;
    }

    @Override
    public String toString() {
        return String.format("%-20s | Rp %,10.0f | Diskon %.0f%% | Rp %,10.0f",
                getNama(), getHarga(), persenDiskon, hitungHargaDiskon());
    }
}