import model.*;
import service.*;
import util.FileManager;
import util.InputValidator;
import exception.MenuException;
import java.util.Scanner;

/**
 * Main Program - Sistem Manajemen Restoran
 * Mengintegrasikan semua konsep OOP yang dipelajari
 */
public class Main {
    private static Menu menu = new Menu();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║          SISTEM MANAJEMEN RESTORAN NUSANTARA           ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        // Memuat menu dari file saat program dimulai
        FileManager.muatMenu(menu);

        // Jika menu kosong, tambahkan data default
        if (menu.getJumlahItem() == 0) {
            tambahDataDefault();
        }

        // Loop menu utama
        boolean running = true;
        while (running) {
            tampilkanMenuUtama();

            try {
                int pilihan = InputValidator.getValidInteger(scanner, 0, 6);

                // Struktur keputusan untuk memproses pilihan
                switch (pilihan) {
                    case 1:
                        tambahItemMenu();
                        break;
                    case 2:
                        menu.tampilkanSemuaMenu();
                        break;
                    case 3:
                        buatPesanan();
                        break;
                    case 4:
                        FileManager.simpanMenu(menu);
                        break;
                    case 5:
                        FileManager.tampilkanDaftarStruk();
                        System.out.print("\nMasukkan nomor pesanan untuk melihat struk: ");
                        String noPesanan = scanner.nextLine();
                        FileManager.bacaStruk(noPesanan);
                        break;
                    case 0:
                        System.out.println("\n╔════════════════════════════════════════════════════════╗");
                        System.out.println("║      Terima kasih telah menggunakan sistem kami!      ║");
                        System.out.println("╚════════════════════════════════════════════════════════╝");
                        running = false;
                        break;
                    default:
                        System.out.println("\n✗ Pilihan tidak valid! Silakan pilih 0-6.");
                }

                if (running && pilihan != 0) {
                    InputValidator.pauseScreen(scanner);
                }

            } catch (Exception e) {
                System.out.println("\n✗ Terjadi kesalahan: " + e.getMessage());
                InputValidator.pauseScreen(scanner);
            }
        }

        scanner.close();
    }

    // Menampilkan menu utama
    private static void tampilkanMenuUtama() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENU UTAMA                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Tambah Item Menu                                   ║");
        System.out.println("║  2. Tampilkan Menu Restoran                            ║");
        System.out.println("║  3. Buat Pesanan Baru                                  ║");
        System.out.println("║  4. Simpan Menu ke File                                ║");
        System.out.println("║  5. Lihat Struk Tersimpan                              ║");
        System.out.println("║  0. Keluar                                             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("Pilih menu [0-5]: ");
    }

    // Menambahkan item baru ke menu
    private static void tambahItemMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              TAMBAH ITEM MENU                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Makanan                                            ║");
        System.out.println("║  2. Minuman                                            ║");
        System.out.println("║  3. Item dengan Diskon                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("Pilih jenis item [1-3]: ");

        int jenis = InputValidator.getValidInteger(scanner, 1, 3);

        System.out.print("Nama item: ");
        String nama = InputValidator.getNonEmptyString(scanner);

        System.out.print("Harga (Rp): ");
        double harga = InputValidator.getPositiveDouble(scanner);

        // Struktur keputusan untuk membuat object sesuai jenis
        switch (jenis) {
            case 1:
                String[] jenisMakananOptions = {"Pembuka", "Utama", "Penutup"};
                System.out.print("Jenis makanan (Pembuka/Utama/Penutup): ");
                String jenisMakanan = InputValidator.getValidChoice(scanner, jenisMakananOptions);
                menu.tambahItem(new Makanan(nama, harga, jenisMakanan));
                break;

            case 2:
                String[] jenisMinumanOptions = {"Dingin", "Panas", "Soda"};
                System.out.print("Jenis minuman (Dingin/Panas/Soda): ");
                String jenisMinuman = InputValidator.getValidChoice(scanner, jenisMinumanOptions);
                menu.tambahItem(new Minuman(nama, harga, jenisMinuman));
                break;

            case 3:
                System.out.print("Persentase diskon (%): ");
                double diskon = InputValidator.getValidPercentage(scanner);
                menu.tambahItem(new Diskon(nama, harga, diskon));
                break;
        }
    }

    // Membuat pesanan baru
    private static void buatPesanan() {
        if (menu.getJumlahItem() == 0) {
            System.out.println("\n✗ Menu masih kosong! Tambahkan item terlebih dahulu.");
            return;
        }

        System.out.print("\nMasukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();

        Pesanan pesanan = new Pesanan(namaPelanggan);
        boolean selesai = false;

        // Loop untuk menambahkan item ke pesanan
        while (!selesai) {
            menu.tampilkanSemuaMenu();

            System.out.println("\n[Ketik 'selesai' untuk menyelesaikan pesanan]");
            System.out.print("Masukkan nama item yang dipesan: ");
            String namaItem = scanner.nextLine();

            if (namaItem.equalsIgnoreCase("selesai")) {
                selesai = true;
                continue;
            }

            try {
                // Exception handling untuk item yang tidak ditemukan
                MenuItem item = menu.cariItem(namaItem);

                System.out.print("Jumlah: ");
                int jumlah = InputValidator.getPositiveInteger(scanner);

                pesanan.tambahItem(item, jumlah);

            } catch (MenuException e) {
                // Menangkap custom exception
                System.out.println("✗ " + e.getMessage());
            }

            System.out.print("\nTambah item lagi? (y/n): ");
            if (!InputValidator.getYesNoConfirmation(scanner)) {
                selesai = true;
            }
        }

        // Menampilkan dan menyimpan struk jika ada pesanan
        if (!pesanan.isEmpty()) {
            pesanan.tampilkanStruk();

            System.out.print("\nSimpan struk ke file? (y/n): ");
            if (InputValidator.getYesNoConfirmation(scanner)) {
                FileManager.simpanStruk(pesanan);
            }
        } else {
            System.out.println("\n⚠ Tidak ada item dalam pesanan!");
        }
    }

    // Menambahkan data default untuk testing
    private static void tambahDataDefault() {
        System.out.println("\nℹ Menambahkan menu default...");

        // Makanan
        menu.tambahItem(new Makanan("Nasi Goreng Spesial", 25000, "Utama"));
        menu.tambahItem(new Makanan("Sate Ayam", 30000, "Utama"));
        menu.tambahItem(new Makanan("Gado-Gado", 20000, "Pembuka"));
        menu.tambahItem(new Makanan("Es Krim Vanilla", 15000, "Penutup"));

        // Minuman
        menu.tambahItem(new Minuman("Es Teh Manis", 8000, "Dingin"));
        menu.tambahItem(new Minuman("Jus Jeruk", 12000, "Dingin"));
        menu.tambahItem(new Minuman("Kopi Susu", 15000, "Panas"));
        menu.tambahItem(new Minuman("Cappuccino", 18000, "Panas"));

        // Item dengan diskon
        menu.tambahItem(new Diskon("Paket Hemat 1", 50000, 20));
        menu.tambahItem(new Diskon("Paket Hemat 2", 75000, 15));

        System.out.println("✓ Menu default berhasil ditambahkan!");

        // Simpan ke file
        FileManager.simpanMenu(menu);
    }

}