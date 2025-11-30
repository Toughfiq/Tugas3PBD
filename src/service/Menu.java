package service;

import model.*;
import exception.MenuException;
import java.util.ArrayList;

/**
 * Kelas Menu untuk mengelola semua item menu
 * Menerapkan konsep Encapsulation dan Collections
 */
public class Menu {
    // Encapsulation: ArrayList private
    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        this.daftarMenu = new ArrayList<>();
    }

    // Menambahkan item ke menu
    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("✓ Item berhasil ditambahkan ke menu!");
    }

    // Mendapatkan semua item menu
    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    // Mencari item berdasarkan nama (Exception Handling)
    public MenuItem cariItem(String nama) throws MenuException {
        // Struktur pengulangan untuk mencari item
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        // Throw exception jika item tidak ditemukan
        throw new MenuException("Item '" + nama + "' tidak ditemukan dalam menu!");
    }

    // Menampilkan semua menu
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("\n Menu masih kosong!");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          DAFTAR MENU RESTORAN                           ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ No │ Nama Item          │ Harga        │ Detail          │ Kategori     ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════╣");

        int nomor = 1;
        for (MenuItem item : daftarMenu) {
            String detail = "";

            // Struktur keputusan (if-else) untuk menentukan detail
            if (item instanceof Makanan) {
                detail = ((Makanan) item).getJenisMakanan();
            } else if (item instanceof Minuman) {
                detail = ((Minuman) item).getJenisMinuman();
            } else if (item instanceof Diskon) {
                detail = String.format("%.0f%% OFF", ((Diskon) item).getPersenDiskon());
            }

            System.out.printf("║ %-2d │ %-18s │ Rp %,9.0f │ %-15s │ %-9s ║%n",
                    nomor++, item.getNama(), item.getHarga(), detail, item.getKategori());
        }

        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }

    // Menampilkan menu berdasarkan kategori
    public void tampilkanMenuByKategori(String kategori) {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.printf("║              MENU %s%-28s║%n", kategori.toUpperCase(), "");
        System.out.println("╠═══════════════════════════════════════════════════════╣");

        boolean found = false;
        int nomor = 1;

        for (MenuItem item : daftarMenu) {
            if (item.getKategori().equalsIgnoreCase(kategori)) {
                System.out.printf("║ %d. %-49s ║%n", nomor++, item.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.printf("║   Tidak ada item dalam kategori %s%-14s║%n", kategori, "");
        }

        System.out.println("╚═══════════════════════════════════════════════════════╝");
    }

    // Mendapatkan jumlah item dalam menu
    public int getJumlahItem() {
        return daftarMenu.size();
    }

    // Mengosongkan menu
    public void kosongkanMenu() {
        daftarMenu.clear();
    }
}