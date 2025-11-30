package model;

/**
 * Kelas Makanan - Inheritance dari MenuItem
 * Menerapkan konsep inheritance dan polymorphism
 */
public class Makanan extends MenuItem {
    private String jenisMakanan; // contoh: Pembuka, Utama, Penutup

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    // Polymorphism: Override metode abstrak
    @Override
    public void tampilMenu() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMASI MAKANAN                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Nama           : %-37s ║%n", getNama());
        System.out.printf("║ Harga          : Rp %-34.0f ║%n", getHarga());
        System.out.printf("║ Jenis Makanan  : %-37s ║%n", jenisMakanan);
        System.out.printf("║ Kategori       : %-37s ║%n", getKategori());
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    @Override
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }

    @Override
    public String toString() {
        return String.format("%-20s | Rp %,10.0f | %-15s | %s",
                getNama(), getHarga(), jenisMakanan, getKategori());
    }
}