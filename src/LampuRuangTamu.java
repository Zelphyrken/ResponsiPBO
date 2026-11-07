public class LampuRuangTamu extends LampuPintar
        implements SensorKehadiran, PengaturJadwal {

    public static final int BATAS_DETEKSI_CM = 150;
    private int jarakOrangCm = 400;
    private int jamMulai = 18;
    private int jamSelesai = 22;

    public LampuRuangTamu(String id, String nama) { super(id, nama); }
    public LampuRuangTamu(String id) { this(id, "Lampu Ruang Tamu"); }

    public int getJarakOrangCm() { return jarakOrangCm; }

    @Override
    public void setJarakOrangCm(int jarak) {
        if (jarak >= 0) this.jarakOrangCm = jarak;
        else System.out.println("Jarak tidak boleh negatif.");
    }

    @Override
    public boolean adaOrang() { return jarakOrangCm <= BATAS_DETEKSI_CM; }

    public int getJamMulai() { return jamMulai; }
    public int getJamSelesai() { return jamSelesai; }
    public void setJamMulai(int jam) { setJadwal(jam, jamSelesai); }
    public void setJamSelesai(int jam) { setJadwal(jamMulai, jam); }

    @Override
    public void setJadwal(int mulai, int selesai) {
        if (mulai >= 0 && mulai < selesai && selesai <= 23) {
            this.jamMulai = mulai;
            this.jamSelesai = selesai;
        } else System.out.println("Jadwal tidak valid (0-23, mulai < selesai).");
    }

    @Override
    public boolean jadwalAktif() {
        return getJamSimulasi() >= jamMulai && getJamSimulasi() < jamSelesai;
    }

    @Override
    public void evaluasiOtomatis() {
        if (adaOrang() || jadwalAktif()) setMenyala(true);
        else setMenyala(false);
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jarak: " + jarakOrangCm + " cm | Jadwal: "
                + jamMulai + ":00-" + jamSelesai + ":00");
    }
}
