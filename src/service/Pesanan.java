package service;

import model.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Kelas Pesanan untuk mencatat pesanan pelanggan
 * Menerapkan konsep Encapsulation dan Collections
 */
public class Pesanan {
    // Encapsulation: atribut private
    private ArrayList<MenuItem> itemPesanan;
    private ArrayList<Integer> jumlahItem;
    private String namaPelanggan;
    private Date tanggalPesanan;
    private String nomorPesanan;

    public Pesanan(String namaPelanggan) {
        this.itemPesanan = new ArrayList<>();
        this.jumlahItem = new ArrayList<>();
        this.namaPelanggan = namaPelanggan;
        this.tanggalPesanan = new Date();
        this.nomorPesanan = generateNomorPesanan();
    }

    // Generate nomor pesanan unik
    private String generateNomorPesanan() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return "ORD-" + sdf.format(tanggalPesanan);
    }

    // Menambahkan item ke pesanan
    public void tambahItem(MenuItem item, int jumlah) {
        // Cek apakah item sudah ada dalam pesanan
        int index = itemPesanan.indexOf(item);

        if (index >= 0) {
            // Jika sudah ada, tambahkan jumlahnya
            jumlahItem.set(index, jumlahItem.get(index) + jumlah);
            System.out.println("✓ Jumlah item '" + item.getNama() + "' diperbarui!");
        } else {
            // Jika belum ada, tambahkan item baru
            itemPesanan.add(item);
            jumlahItem.add(jumlah);
            System.out.println("✓ Item '" + item.getNama() + "' ditambahkan ke pesanan!");
        }
    }

    // Menghitung total harga pesanan
    public double hitungTotal() {
        double total = 0;

        // Struktur pengulangan untuk menghitung total
        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = jumlahItem.get(i);

            // Struktur keputusan untuk menangani diskon
            if (item instanceof Diskon) {
                total += ((Diskon) item).hitungHargaDiskon() * qty;
            } else {
                total += item.getHarga() * qty;
            }
        }

        return total;
    }

    // Menghitung total diskon
    public double hitungTotalDiskon() {
        double totalDiskon = 0;

        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = jumlahItem.get(i);

            if (item instanceof Diskon) {
                Diskon diskon = (Diskon) item;
                totalDiskon += (diskon.getHarga() - diskon.hitungHargaDiskon()) * qty;
            }
        }

        return totalDiskon;
    }

    // Menampilkan struk pesanan
    public void tampilkanStruk() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    STRUK PEMBAYARAN                            ║");
        System.out.println("║                   RESTORAN NUSANTARA                           ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ No. Pesanan  : %-47s ║%n", nomorPesanan);
        System.out.printf("║ Pelanggan    : %-47s ║%n", namaPelanggan);
        System.out.printf("║ Tanggal      : %-47s ║%n", sdf.format(tanggalPesanan));
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Item                         Qty   Harga      Sub Total        ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = jumlahItem.get(i);
            double hargaSatuan;
            double subtotal;

            if (item instanceof Diskon) {
                Diskon diskon = (Diskon) item;
                hargaSatuan = diskon.hitungHargaDiskon();
                subtotal = hargaSatuan * qty;
                System.out.printf("║ %-25s    %2d   %,9.0f   %,12.0f ║%n",
                        item.getNama(), qty, hargaSatuan, subtotal);
                System.out.printf("║   (Diskon %.0f%% dari Rp %,.0f)%-31s║%n",
                        diskon.getPersenDiskon(), diskon.getHarga(), "");
            } else {
                hargaSatuan = item.getHarga();
                subtotal = hargaSatuan * qty;
                System.out.printf("║ %-25s    %2d   %,9.0f   %,12.0f ║%n",
                        item.getNama(), qty, hargaSatuan, subtotal);
            }
        }

        System.out.println("╠════════════════════════════════════════════════════════════════╣");

        double totalDiskon = hitungTotalDiskon();
        if (totalDiskon > 0) {
            System.out.printf("║ Total Diskon                                   Rp %,12.0f ║%n", totalDiskon);
        }

        System.out.printf("║ TOTAL PEMBAYARAN                               Rp %,12.0f ║%n", hitungTotal());
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║              Terima kasih atas kunjungan Anda!                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }

    // Getter methods
    public ArrayList<MenuItem> getItemPesanan() {
        return itemPesanan;
    }

    public ArrayList<Integer> getJumlahItem() {
        return jumlahItem;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public String getNomorPesanan() {
        return nomorPesanan;
    }

    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }

    // Mengonversi pesanan ke format string untuk file
    public String toFileString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();

        sb.append("PESANAN|").append(nomorPesanan).append("|");
        sb.append(namaPelanggan).append("|").append(sdf.format(tanggalPesanan)).append("\n");

        for (int i = 0; i < itemPesanan.size(); i++) {
            MenuItem item = itemPesanan.get(i);
            int qty = jumlahItem.get(i);
            double harga;

            if (item instanceof Diskon) {
                harga = ((Diskon) item).hitungHargaDiskon();
            } else {
                harga = item.getHarga();
            }

            sb.append("ITEM|").append(item.getNama()).append("|");
            sb.append(qty).append("|").append(harga).append("\n");
        }

        sb.append("TOTAL|").append(hitungTotal()).append("\n");

        return sb.toString();
    }
}