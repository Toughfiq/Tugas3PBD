# Sistem Manajemen Restoran - Tugas Praktik 3 PBO

## 📋 Deskripsi Project

Program Java untuk manajemen restoran yang mengimplementasikan konsep-konsep Pemrograman Berbasis Objek (OOP) seperti abstraksi, inheritance, encapsulation, polymorphism, exception handling, dan operasi file.

## 🗂️ Struktur Folder

```
ManajemenRestoran/
│
├── src/
│   ├── model/              # Package untuk model data
│   │   ├── MenuItem.java   # Abstract class untuk item menu
│   │   ├── Makanan.java    # Class untuk menu makanan
│   │   ├── Minuman.java    # Class untuk menu minuman
│   │   └── Diskon.java     # Class untuk item diskon
│   │
│   ├── service/            # Package untuk business logic
│   │   ├── Menu.java       # Class untuk mengelola menu
│   │   └── Pesanan.java    # Class untuk mengelola pesanan
│   │
│   ├── util/               # Package untuk utility functions
│   │   ├── FileManager.java # Class untuk operasi file I/O
│   │   └── InputValidator.java # Class untuk validasi input
│   │
│   ├── exception/          # Package untuk custom exceptions
│   │   └── MenuException.java # Custom exception class
│   │
│   └── Main.java           # Main program entry point
│
├── data/
│   ├── menu.txt            # File penyimpanan menu
│   └── struk/              # Folder untuk menyimpan struk
│
└── README.md               # Dokumentasi project
```

## 🎯 Fitur Program

1. **Manajemen Menu**

   - Tambah item menu (Makanan, Minuman, Diskon)
   - Tampilkan semua menu
   - Simpan menu ke file
   - Muat menu dari file

2. **Manajemen Pesanan**

   - Buat pesanan baru
   - Tambah item ke pesanan
   - Hitung total dengan diskon
   - Tampilkan struk pembayaran
   - Simpan struk ke file

3. **Operasi File**
   - Simpan/muat data menu
   - Simpan struk pesanan
   - Lihat struk yang tersimpan

## 🔧 Konsep OOP yang Diimplementasikan

### 1. **Abstraksi**

- **MenuItem** sebagai abstract class
- Method abstrak: `tampilMenu()` dan `toFileString()`

### 2. **Inheritance**

```
MenuItem (Abstract)
├── Makanan
├── Minuman
└── Diskon
```

### 3. **Encapsulation**

- Semua atribut class bersifat `private`
- Akses melalui getter dan setter methods
- Information hiding

### 4. **Polymorphism**

- Override method `tampilMenu()` di setiap subclass
- Override method `toString()` dan `toFileString()`
- Runtime polymorphism dengan instanceof

### 5. **Exception Handling**

- Custom exception: `MenuException`
- Try-catch untuk input validation
- IOException handling untuk file operations

### 6. **I/O & File Operations**

- `BufferedReader` dan `BufferedWriter` untuk menu
- `PrintWriter` untuk struk
- File parsing dan formatting

### 7. **Input Validation**

- `InputValidator` utility class
- Validasi integer, double, string, percentage
- Validasi Yes/No confirmation
- Error handling untuk input tidak valid

### 8. **Collections**

- `ArrayList<MenuItem>` untuk daftar menu
- `ArrayList<Integer>` untuk jumlah item pesanan

### 8. **Struktur Kontrol**

- **Keputusan**: if-else, switch-case
- **Pengulangan**: for, while, for-each
- **Array & String**: String manipulation, array operations

### 1. Menu Utama

```
╔════════════════════════════════════════════════════════╗
║                    MENU UTAMA                          ║
╠════════════════════════════════════════════════════════╣
║  1. Tambah Item Menu                                   ║
║  2. Tampilkan Menu Restoran                            ║
║  3. Buat Pesanan Baru                                  ║
║  4. Simpan Menu ke File                                ║
║  5. Lihat Struk Tersimpan                              ║
║  6. Tentang Program                                    ║
║  0. Keluar                                             ║
╚════════════════════════════════════════════════════════╝
```

### 2. Tambah Item Menu

- Pilih jenis: Makanan, Minuman, atau Diskon
- Input nama, harga, dan detail
- Item otomatis tersimpan dalam sistem

### 3. Buat Pesanan

- Input nama pelanggan
- Pilih item dari menu yang tersedia
- Input jumlah untuk setiap item
- Sistem akan menghitung total otomatis
- Struk dapat disimpan ke file

### 4. Lihat Struk

- Pilih dari daftar struk tersimpan
- Tampilkan detail pesanan
- Format: `ORD-YYYYMMDDHHmmss.txt`

## Format File

### menu.txt

```
MAKANAN|Nasi Goreng|25000|Utama
MINUMAN|Es Teh|8000|Dingin
DISKON|Paket Hemat|50000|20
```

### Struk (ORD-xxxxx.txt)

```
════════════════════════════════════════════════════════
                  STRUK PEMBAYARAN
                 RESTORAN NUSANTARA
════════════════════════════════════════════════════════
No. Pesanan  : ORD-20250101120000
Pelanggan    : John Doe
Tanggal      : 01/01/2025 12:00:00
...
```

## Catatan Penting

1. **File Initialization**

   - Program otomatis membuat folder `data/` dan `data/struk/`
   - Menu default akan ditambahkan jika file kosong

2. **Error Handling**

   - Input validation untuk angka
   - Exception handling untuk item tidak ditemukan
   - IOException handling untuk operasi file

3. **Data Persistence**
   - Menu disimpan otomatis ke file
   - Struk dapat disimpan sesuai kebutuhan
   - Format file plain text untuk mudah di-debug
