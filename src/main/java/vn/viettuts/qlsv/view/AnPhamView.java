package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import vn.viettuts.qlsv.entity.AnPham;

public class AnPhamView extends JFrame implements ListSelectionListener {
    private static final long serialVersionUID = 1L;

    private DefaultTableModel tableModel;
    private JTable anPhamTable;
    private JScrollPane jScrollPaneAnPhamTable;
    private JTextField tenAnPhamField, loaiAnPhamField, giaThanhField, soLuongField, maSoField, nhaXuatBanField, tacGiaField, searchField;
    private JButton addAnPhamBtn, editAnPhamBtn, deleteAnPhamBtn, sortAnPhamGiaThanhBtn, sortAnPhamTenAnPhamBtn, searchButton, switchToMuonTraViewBtn;
    private String[] columnNames = {"ID", "Tên Ấn Phẩm", "Loại Ấn Phẩm", "Giá Thành", "Số Lượng", "Mã Số", "Nhà Xuất Bản", "Tác Giả"};

    public AnPhamView() {
        setTitle("Quản lý Ấn Phẩm");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setLayouts();
        setupListeners();
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);

        anPhamTable = new JTable(tableModel);
        anPhamTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPaneAnPhamTable = new JScrollPane(anPhamTable);
        jScrollPaneAnPhamTable.setPreferredSize(new Dimension(700, 300));

        tenAnPhamField = new JTextField(20);
        loaiAnPhamField = new JTextField(20);
        giaThanhField = new JTextField(10);
        soLuongField = new JTextField(5);
        maSoField = new JTextField(10);
        nhaXuatBanField = new JTextField(20);
        tacGiaField = new JTextField(20);

        addAnPhamBtn = new JButton("Thêm");
        editAnPhamBtn = new JButton("Sửa");
        deleteAnPhamBtn = new JButton("Xóa");
        sortAnPhamGiaThanhBtn = new JButton("Sắp xếp theo Giá Thành");
        sortAnPhamTenAnPhamBtn = new JButton("Sắp xếp theo Tên Ấn Phẩm");

        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");

        switchToMuonTraViewBtn = new JButton("Chuyển sang Mượn Trả");
    }

    private void setLayouts() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Tên Ấn Phẩm:"));
        formPanel.add(tenAnPhamField);
        formPanel.add(new JLabel("Loại Ấn Phẩm:"));
        formPanel.add(loaiAnPhamField);
        formPanel.add(new JLabel("Giá Thành:"));
        formPanel.add(giaThanhField);
        formPanel.add(new JLabel("Số Lượng:"));
        formPanel.add(soLuongField);
        formPanel.add(new JLabel("Mã Số:"));
        formPanel.add(maSoField);
        formPanel.add(new JLabel("Nhà Xuất Bản:"));
        formPanel.add(nhaXuatBanField);
        formPanel.add(new JLabel("Tác Giả:"));
        formPanel.add(tacGiaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addAnPhamBtn);
        buttonPanel.add(editAnPhamBtn);
        buttonPanel.add(deleteAnPhamBtn);
        buttonPanel.add(sortAnPhamGiaThanhBtn);
        buttonPanel.add(sortAnPhamTenAnPhamBtn);
        buttonPanel.add(switchToMuonTraViewBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(formPanel);
        panel.add(jScrollPaneAnPhamTable);
        panel.add(buttonPanel);
        panel.add(searchPanel);

        add(panel);
    }

    private void setupListeners() {
        anPhamTable.getSelectionModel().addListSelectionListener(this);
    }

    public void addAddAnPhamListener(ActionListener listener) {
        addAnPhamBtn.addActionListener(listener);
    }

    public void addEditAnPhamListener(ActionListener listener) {
        editAnPhamBtn.addActionListener(listener);
    }

    public void addDeleteAnPhamListener(ActionListener listener) {
        deleteAnPhamBtn.addActionListener(listener);
    }

    public void addSortAnPhamGiaThanhListener(ActionListener listener) {
        sortAnPhamGiaThanhBtn.addActionListener(listener);
    }

    public void addSortAnPhamTenListener(ActionListener listener) {
        sortAnPhamTenAnPhamBtn.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addSwitchToMuonTraViewListener(ActionListener listener) {
        switchToMuonTraViewBtn.addActionListener(listener);
    }

    public void resetButtons() {
        addAnPhamBtn.setEnabled(true);
        editAnPhamBtn.setEnabled(false);
        deleteAnPhamBtn.setEnabled(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListAnPham(List<AnPham> list) {
        int size = list.size();
        tableModel.setRowCount(0);
        Object[][] anPhamObjects = new Object[size][8];
        for (int i = 0; i < size; i++) {
            AnPham ap = list.get(i);
            String formattedGiaThanh = String.format("%,.0f", ap.getGiaThanh());
            anPhamObjects[i][0] = ap.getId(); // Hiển thị ID từ đối tượng AnPham
            anPhamObjects[i][1] = ap.getTenAnPham();
            anPhamObjects[i][2] = ap.getLoaiAnPham();
            anPhamObjects[i][3] = formattedGiaThanh;
            anPhamObjects[i][4] = ap.getSoLuong();
            anPhamObjects[i][5] = ap.getMaSo();
            anPhamObjects[i][6] = ap.getNhaXuatBan();
            anPhamObjects[i][7] = ap.getTacGia();
        }
        anPhamTable.setModel(new DefaultTableModel(
                anPhamObjects, columnNames));
    }

    public void fillAnPhamFromSelectedRow() {
        int row = anPhamTable.getSelectedRow();
        if (row != -1) {
            tenAnPhamField.setText(anPhamTable.getValueAt(row, 1).toString());
            loaiAnPhamField.setText(anPhamTable.getValueAt(row, 2).toString());
            String giaThanhStr = anPhamTable.getValueAt(row, 3).toString().replace(",", ""); // Remove commas
            giaThanhField.setText(giaThanhStr);
            soLuongField.setText(anPhamTable.getValueAt(row, 4).toString());
            maSoField.setText(anPhamTable.getValueAt(row, 5).toString());
            nhaXuatBanField.setText(anPhamTable.getValueAt(row, 6).toString());
            tacGiaField.setText(anPhamTable.getValueAt(row, 7).toString());

            editAnPhamBtn.setEnabled(true);
            deleteAnPhamBtn.setEnabled(true);
            addAnPhamBtn.setEnabled(false);
        }
    }

    public void clearAnPhamInfo() {
        tenAnPhamField.setText("");
        loaiAnPhamField.setText("");
        giaThanhField.setText("");
        soLuongField.setText("");
        maSoField.setText("");
        nhaXuatBanField.setText("");
        tacGiaField.setText("");

        editAnPhamBtn.setEnabled(false);
        deleteAnPhamBtn.setEnabled(false);
        addAnPhamBtn.setEnabled(true);
    }

    public void filterTable(String query) {
        DefaultTableModel model = (DefaultTableModel) anPhamTable.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        anPhamTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }

    public AnPham getAnPhamInfo() {
        try {
            int id = -1; // Default to -1 for new item
            if (anPhamTable.getSelectedRow() != -1) {
                id = Integer.parseInt(anPhamTable.getValueAt(anPhamTable.getSelectedRow(), 0).toString());
            }

            String ten = tenAnPhamField.getText();
            String loai = loaiAnPhamField.getText();

            double giaThanh = 0.0;
            String giaThanhStr = giaThanhField.getText();
            if (giaThanhStr != null && !giaThanhStr.isEmpty()) {
                giaThanh = Double.parseDouble(giaThanhStr);
            }

            int soLuong = 0;
            String soLuongStr = soLuongField.getText();
            if (soLuongStr != null && !soLuongStr.isEmpty()) {
                soLuong = Integer.parseInt(soLuongStr);
            }

            String maSo = maSoField.getText();
            String nhaXuatBan = nhaXuatBanField.getText();
            String tacGia = tacGiaField.getText();

            return new AnPham(id, ten, loai, giaThanh, soLuong, maSo, nhaXuatBan, tacGia);
        } catch (NumberFormatException e) {
            showMessage("Lỗi: Vui lòng nhập đúng định dạng số cho giá thành và số lượng.");
        } catch (Exception e) {
            showMessage("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void updateAfterEdit() {
        clearAnPhamInfo(); 
        resetButtons();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            fillAnPhamFromSelectedRow();
        }
    }
}
