package model;

/**
 * Kelas abstrak MenuItem sebagai base class untuk semua item menu
 * Menerapkan konsep abstraksi dan encapsulation
 */
public abstract class MenuItem {
    // Encapsulation: atribut private
    private String nama;
    private double harga;
    private String kategori;

    // Constructor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    // Metode abstrak yang harus diimplementasikan oleh subclass
    public abstract void tampilMenu();

    // Metode untuk format data ke file
    public abstract String toFileString();

    @Override
    public String toString() {
        return String.format("%-20s | Rp %,10.0f | %s", nama, harga, kategori);
    }
}