public abstract class LampuPintar {
    private final String idLampu;
    private String namaLampu;
    private boolean menyala;
    private int jamSimulasi = 10;

    public LampuPintar(String idLampu, String namaLampu) {
        this.idLampu = idLampu;
        this.namaLampu = namaLampu;
    }

    public String getIdLampu() { return idLampu; }
    public String getNamaLampu() { return namaLampu; }
    public void setNamaLampu(String namaLampu) { this.namaLampu = namaLampu; }
    public boolean isMenyala() { return menyala; }
    protected void setMenyala(boolean menyala) { this.menyala = menyala; }
    public int getJamSimulasi() { return jamSimulasi; }

    public void setJamSimulasi(int jam) {
        if (jam >= 0 && jam <= 23) this.jamSimulasi = jam;
        else System.out.println("Jam harus 0 sampai 23.");
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + idLampu + " | Nama: " + namaLampu);
        System.out.println("Jam: " + jamSimulasi + ":00 | Status: " + (menyala ? "MENYALA" : "MATI"));
    }

    public abstract void evaluasiOtomatis();
}
