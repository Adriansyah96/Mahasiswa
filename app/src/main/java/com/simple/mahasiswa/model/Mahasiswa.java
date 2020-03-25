package com.simple.mahasiswa.model;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    private String nim;
    private String nama;
    private String kelas;
    private String key;

    public Mahasiswa(String nim, String nama, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
    }

    public Mahasiswa(String key) {
        this.key = key;
    }

    public Mahasiswa() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
