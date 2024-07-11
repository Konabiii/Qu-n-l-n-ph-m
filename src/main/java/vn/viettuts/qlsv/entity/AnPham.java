package vn.viettuts.qlsv.entity;

public class AnPham {
    private int id;
    private String tenAnPham;
    private String loaiAnPham;
    private double giaThanh;
    private int soLuong;
    private String maSo;
    private String nhaXuatBan;
    private String tacGia;

    public AnPham() {
    }

    public AnPham(int id, String tenAnPham, String loaiAnPham, double giaThanh, int soLuong, String maSo, String nhaXuatBan, String tacGia) {
        this.id = id;
        this.tenAnPham = tenAnPham;
        this.loaiAnPham = loaiAnPham;
        this.giaThanh = giaThanh;
        this.soLuong = soLuong;
        this.maSo = maSo;
        this.nhaXuatBan = nhaXuatBan;
        this.tacGia = tacGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenAnPham() {
        return tenAnPham;
    }

    public void setTenAnPham(String tenAnPham) {
        this.tenAnPham = tenAnPham;
    }

    public String getLoaiAnPham() {
        return loaiAnPham;
    }

    public void setLoaiAnPham(String loaiAnPham) {
        this.loaiAnPham = loaiAnPham;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
