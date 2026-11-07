public class LampuTeras extends LampuPintar {
    public LampuTeras(String id, String nama) { super(id, nama); }

    @Override
    public void evaluasiOtomatis() {
        int jam = getJamSimulasi();
        if (jam >= 18 || jam < 6) setMenyala(true);
        else setMenyala(false);
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jadwal teras: 18:00-06:00");
    }
}
