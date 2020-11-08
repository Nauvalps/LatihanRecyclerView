package com.stmikbanisaleh.latihanrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class Dosen {
    private int id;
    private String nama;
    private String kompetensi;
    private boolean status;

    public Dosen() {
    }

    public Dosen(int id, String nama, String kompetensi, boolean status) {
        this.id = id;
        this.nama = nama;
        this.kompetensi = kompetensi;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static List<Dosen> initData() {
        List<Dosen> dosenList = new ArrayList<>();
        dosenList.add(new Dosen(1, "Nauval Purnomo Sidi S.Kom", "IT Literature, Programming Literature", true));
        dosenList.add(new Dosen(2, "Drs. Taufik Maulana, MBA ", "Statistic, Data Analysis", true));
        dosenList.add(new Dosen(3, "Suhadi", "Sistem Cerdas,Data Mining", true));
        dosenList.add(new Dosen(4, "Hudi Kusuma S.Kom, M.Kom", "Artificial Intelligent, Big Data", true));
        dosenList.add(new Dosen(5, "Rudi Budi Agung, S.Kom, M.Si", "SAP Specialist, System Architecture", true));
        dosenList.add(new Dosen(6, "Subandri S.Kom, M.Kom", "IT Infrastructure, Networking", true));
        dosenList.add(new Dosen(7, "Hendra Setiawan S.Kom, M.Kom", "Web Design, Information System", true));
        dosenList.add(new Dosen(8, "Ndaru Ruseno S.Kom, M.Kom", "Human Computer Interaction", true));
        dosenList.add(new Dosen(9, "Soelistyowati, Ir, Msc", "Statistic, Data Analysis", true));
        dosenList.add(new Dosen(10, "Sjaeful Irwan Drs, M.Si", "Statistic, Data Analysis", true));
        dosenList.add(new Dosen(11, "Ramdani, S.Kom, M.Kom", "Internet Of Things, Robotic", true));
        dosenList.add(new Dosen(12, "Muhammad Nur, S.Kom, M.Kom", "Software Engineering, Programming", true));
        return dosenList;
    }
}
