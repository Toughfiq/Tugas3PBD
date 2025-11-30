package model;

/**
 * Kelas Minuman - Inheritance dari MenuItem
 * Menerapkan konsep inheritance dan polymorphism
 */
public class Minuman extends MenuItem {
    private String jenisMinuman; // contoh: Dingin, Panas, Soda

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() {
        return jenisMinuman;
    }

    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }

    // Polymorphism: Override metode abstrak
    @Override
    public void tampilMenu() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMASI MINUMAN                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Nama           : %-37s ║%n", getNama());
        System.out.printf("║ Harga          : Rp %-34.0f ║%n", getHarga());
        System.out.printf("║ Jenis Minuman  : %-37s ║%n", jenisMinuman);
        System.out.printf("║ Kategori       : %-37s ║%n", getKategori());
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    @Override
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }

    @Override
    public String toString() {
        return String.format("%-20s | Rp %,10.0f | %-15s | %s",
                getNama(), getHarga(), jenisMinuman, getKategori());
    }
}