
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LampuRuangTamu ruangTamu = new LampuRuangTamu("L001");
        LampuTeras teras = new LampuTeras("L002", "Lampu Teras");
        LampuPintar aktif = ruangTamu;
        SensorKehadiran sensor = ruangTamu;
        PengaturJadwal jadwal = ruangTamu;

        ujiKonsep();
        ruangTamu.evaluasiOtomatis();
        teras.evaluasiOtomatis();
        int pilihan;

        do {
            System.out.println("\n=== SMART LIGHTING SYSTEM ===");
            System.out.println("Lampu aktif: " + aktif.getNamaLampu());
            System.out.println("1. Status lampu\n2. Ubah jarak orang\n3. Ubah jam");
            System.out.println("4. Atur jadwal\n5. Ganti lampu\n0. Keluar");
            System.out.print("Pilih: ");
            pilihan = bacaAngka(input);

            switch (pilihan) {
                case 1:
                    break;
                case 2:
                    if (aktif == ruangTamu) {
                        System.out.print("Jarak orang (cm): ");
                        sensor.setJarakOrangCm(bacaAngka(input));
                    } else {
                        System.out.println("Lampu teras tidak memakai sensor.");
                    }
                    break;
                case 3:
                    System.out.print("Jam simulasi (0-23): ");
                    int jam = bacaAngka(input);
                    ruangTamu.setJamSimulasi(jam);
                    teras.setJamSimulasi(jam);
                    break;
                case 4:
                    if (aktif == ruangTamu) {
                        System.out.print("Jam mulai: ");
                        int mulai = bacaAngka(input);
                        System.out.print("Jam selesai: ");
                        jadwal.setJadwal(mulai, bacaAngka(input));
                    } else {
                        System.out.println("Jadwal teras tetap 18:00-06:00.");
                    }
                    break;
                case 5:
                    aktif = (aktif == ruangTamu) ? teras : ruangTamu;
                    break;
                case 0:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
            }

            if (pilihan >= 1 && pilihan <= 5) {
                ruangTamu.evaluasiOtomatis();
                teras.evaluasiOtomatis();
                aktif.tampilkanInfo();
            }

        } while (pilihan != 0);

        input.close();
    }

    private static int bacaAngka(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Masukkan angka bulat: ");
            input.next();
        }
        return input.nextInt();
    }

    private static void ujiKonsep() {
        LampuRuangTamu uji = new LampuRuangTamu("U001", "Lampu Uji");
        uji.setNamaLampu("Lampu Pengujian");

        SensorKehadiran sensor = uji;
        PengaturJadwal jadwal = uji;

        sensor.setJarakOrangCm(80);
        jadwal.setJadwal(18, 22);
        uji.evaluasiOtomatis();

        LampuPintar teras = new LampuTeras("U002", "Teras Uji");
        teras.setJamSimulasi(23);
        teras.evaluasiOtomatis();

        if (!uji.getNamaLampu().equals("Lampu Pengujian") ||
                !uji.getIdLampu().equals("U001") ||
                LampuRuangTamu.BATAS_DETEKSI_CM != 150 ||
                !sensor.adaOrang() || jadwal.jadwalAktif() ||
                !uji.isMenyala() || !teras.isMenyala()) {
            System.out.println("Peringatan: pengujian internal gagal.");
        }
    }
}