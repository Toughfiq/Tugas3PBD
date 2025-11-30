package util;

import java.util.Scanner;

/**
 * Kelas InputValidator untuk validasi input pengguna
 * Menerapkan konsep utility class dan exception handling
 */
public class InputValidator {

    /**
     * Validasi input integer dengan range tertentu
     * @param scanner Scanner object untuk input
     * @param min nilai minimum yang diperbolehkan
     * @param max nilai maximum yang diperbolehkan
     * @return integer yang valid
     */
    public static int getValidInteger(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("✗ Nilai harus antara %d dan %d. Coba lagi: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input integer positif
     * @param scanner Scanner object untuk input
     * @return integer positif yang valid
     */
    public static int getPositiveInteger(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                } else {
                    System.out.print("✗ Nilai harus lebih dari 0. Coba lagi: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input double positif
     * @param scanner Scanner object untuk input
     * @return double positif yang valid
     */
    public static double getPositiveDouble(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);

                if (value > 0) {
                    return value;
                } else {
                    System.out.print("✗ Nilai harus lebih dari 0. Coba lagi: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input double dengan range tertentu
     * @param scanner Scanner object untuk input
     * @param min nilai minimum
     * @param max nilai maximum
     * @return double yang valid
     */
    public static double getValidDouble(Scanner scanner, double min, double max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("✗ Nilai harus antara %.2f dan %.2f. Coba lagi: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input string tidak kosong
     * @param scanner Scanner object untuk input
     * @return string yang valid (tidak kosong)
     */
    public static String getNonEmptyString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.print("✗ Input tidak boleh kosong! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi konfirmasi Yes/No
     * @param scanner Scanner object untuk input
     * @return true jika yes, false jika no
     */
    public static boolean getYesNoConfirmation(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes") || input.equals("ya")) {
                return true;
            } else if (input.equals("n") || input.equals("no") || input.equals("tidak")) {
                return false;
            } else {
                System.out.print("✗ Input harus 'y' atau 'n'. Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input persentase (0-100)
     * @param scanner Scanner object untuk input
     * @return persentase yang valid
     */
    public static double getValidPercentage(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);

                if (value >= 0 && value <= 100) {
                    return value;
                } else {
                    System.out.print("✗ Persentase harus antara 0 dan 100. Coba lagi: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi input dengan pilihan tertentu
     * @param scanner Scanner object untuk input
     * @param choices array pilihan yang valid
     * @return pilihan yang valid
     */
    public static String getValidChoice(Scanner scanner, String[] choices) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            for (String choice : choices) {
                if (input.equalsIgnoreCase(choice)) {
                    return choice;
                }
            }

            System.out.print("✗ Pilihan tidak valid! Pilih dari [");
            for (int i = 0; i < choices.length; i++) {
                System.out.print(choices[i]);
                if (i < choices.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]. Coba lagi: ");
        }
    }

    /**
     * Validasi format email sederhana
     * @param scanner Scanner object untuk input
     * @return email yang valid
     */
    public static String getValidEmail(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return input;
            } else {
                System.out.print("✗ Format email tidak valid! Coba lagi: ");
            }
        }
    }

    /**
     * Validasi nomor telepon (angka saja, 10-13 digit)
     * @param scanner Scanner object untuk input
     * @return nomor telepon yang valid
     */
    public static String getValidPhoneNumber(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.matches("^[0-9]{10,13}$")) {
                return input;
            } else {
                System.out.print("✗ Nomor telepon harus 10-13 digit angka! Coba lagi: ");
            }
        }
    }

    /**
     * Clear console (multi-platform)
     */
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print beberapa baris kosong
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Pause dan tunggu user menekan Enter
     * @param scanner Scanner object untuk input
     */
    public static void pauseScreen(Scanner scanner) {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    /**
     * Tampilkan pesan error dengan format
     * @param message pesan error
     */
    public static void showError(String message) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                        ERROR                           ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-54s ║%n", message);
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    /**
     * Tampilkan pesan sukses dengan format
     * @param message pesan sukses
     */
    public static void showSuccess(String message) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                       SUCCESS                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ ✓ %-52s ║%n", message);
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}