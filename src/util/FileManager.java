package util;

import model.*;
import service.*;
import java.io.*;
import java.util.Scanner;

/**
 * Kelas FileManager untuk operasi I/O dan File
 * Menerapkan konsep I/O dan Exception Handling
 */
public class FileManager {
    private static final String MENU_FILE = "data/menu.txt";
    private static final String STRUK_FOLDER = "data/struk/";

    // Membuat folder jika belum ada
    public static void createDirectories() {
        File menuDir = new File("data");
        File strukDir = new File(STRUK_FOLDER);

        if (!menuDir.exists()) {
            menuDir.mkdir();
        }

        if (!strukDir.exists()) {
            strukDir.mkdirs();
        }
    }

    // Menyimpan menu ke file
    public static void simpanMenu(Menu menu) {
        createDirectories();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            // Struktur pengulangan untuk menyimpan setiap item
            for (MenuItem item : menu.getDaftarMenu()) {
                writer.write(item.toFileString());
                writer.newLine();
            }
            System.out.println("\n✓ Menu berhasil disimpan ke file!");
        } catch (IOException e) {
            System.err.println("✗ Error saat menyimpan menu: " + e.getMessage());
        }
    }

    // Memuat menu dari file
    public static void muatMenu(Menu menu) {
        File file = new File(MENU_FILE);

        if (!file.exists()) {
            System.out.println("⚠ File menu tidak ditemukan. Membuat file baru...");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            int count = 0;

            // Struktur pengulangan untuk membaca setiap baris
            while ((line = reader.readLine()) != null) {
                try {
                    MenuItem item = parseMenuItem(line);
                    if (item != null) {
                        menu.tambahItem(item);
                        count++;
                    }
                } catch (Exception e) {
                    System.err.println("⚠ Error parsing line: " + line);
                }
            }

            System.out.println("✓ Berhasil memuat " + count + " item dari file menu!");
        } catch (IOException e) {
            System.err.println("✗ Error saat membaca menu: " + e.getMessage());
        }
    }

    // Parse string menjadi MenuItem object
    private static MenuItem parseMenuItem(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 4) {
            return null;
        }

        String tipe = parts[0];
        String nama = parts[1];
        double harga = Double.parseDouble(parts[2]);
        String detail = parts[3];

        // Struktur keputusan untuk membuat object sesuai tipe
        switch (tipe) {
            case "MAKANAN":
                return new Makanan(nama, harga, detail);
            case "MINUMAN":
                return new Minuman(nama, harga, detail);
            case "DISKON":
                double diskon = Double.parseDouble(detail);
                return new Diskon(nama, harga, diskon);
            default:
                return null;
        }
    }

    // Menyimpan struk pesanan ke file
    public static void simpanStruk(Pesanan pesanan) {
        createDirectories();

        String fileName = STRUK_FOLDER + pesanan.getNomorPesanan() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Menulis header
            writer.println("════════════════════════════════════════════════════════");
            writer.println("                  STRUK PEMBAYARAN");
            writer.println("                 RESTORAN NUSANTARA");
            writer.println("════════════════════════════════════════════════════════");
            writer.println("No. Pesanan  : " + pesanan.getNomorPesanan());
            writer.println("Pelanggan    : " + pesanan.getNamaPelanggan());
            writer.println("Tanggal      : " + pesanan.getTanggalPesanan());
            writer.println("════════════════════════════════════════════════════════");
            writer.println("Item                      Qty   Harga      Sub Total");
            writer.println("════════════════════════════════════════════════════════");

            // Menulis detail item
            for (int i = 0; i < pesanan.getItemPesanan().size(); i++) {
                MenuItem item = pesanan.getItemPesanan().get(i);
                int qty = pesanan.getJumlahItem().get(i);
                double harga;
                double subtotal;

                if (item instanceof Diskon) {
                    Diskon diskon = (Diskon) item;
                    harga = diskon.hitungHargaDiskon();
                    subtotal = harga * qty;
                    writer.printf("%-25s %3d   %,9.0f   %,12.0f%n",
                            item.getNama(), qty, harga, subtotal);
                    writer.printf("  (Diskon %.0f%% dari Rp %,.0f)%n",
                            diskon.getPersenDiskon(), diskon.getHarga());
                } else {
                    harga = item.getHarga();
                    subtotal = harga * qty;
                    writer.printf("%-25s %3d   %,9.0f   %,12.0f%n",
                            item.getNama(), qty, harga, subtotal);
                }
            }

            writer.println("════════════════════════════════════════════════════════");

            double totalDiskon = pesanan.hitungTotalDiskon();
            if (totalDiskon > 0) {
                writer.printf("Total Diskon                            Rp %,12.0f%n", totalDiskon);
            }

            writer.printf("TOTAL PEMBAYARAN                        Rp %,12.0f%n", pesanan.hitungTotal());
            writer.println("════════════════════════════════════════════════════════");
            writer.println("           Terima kasih atas kunjungan Anda!");
            writer.println("════════════════════════════════════════════════════════");

            System.out.println("\n✓ Struk berhasil disimpan ke: " + fileName);
        } catch (IOException e) {
            System.err.println("✗ Error saat menyimpan struk: " + e.getMessage());
        }
    }

    // Membaca dan menampilkan struk dari file
    public static void bacaStruk(String nomorPesanan) {
        String fileName = STRUK_FOLDER + nomorPesanan + ".txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("✗ Struk dengan nomor " + nomorPesanan + " tidak ditemukan!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println();

            // Membaca dan menampilkan setiap baris
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("✗ Error saat membaca struk: " + e.getMessage());
        }
    }

    // Menampilkan daftar semua struk yang tersimpan
    public static void tampilkanDaftarStruk() {
        File folder = new File(STRUK_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("\n⚠ Tidak ada struk yang tersimpan!");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              DAFTAR STRUK TERSIMPAN                   ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName().replace(".txt", "");
            System.out.printf("║ %2d. %-50s ║%n", (i + 1), fileName);
        }

        System.out.println("╚═══════════════════════════════════════════════════════╝");
    }
}