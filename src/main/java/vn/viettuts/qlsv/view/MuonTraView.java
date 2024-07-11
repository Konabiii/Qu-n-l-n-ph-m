package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vn.viettuts.qlsv.entity.MuonTra;

public class MuonTraView extends JFrame {
    private static final long serialVersionUID = 1L;

    private DefaultTableModel tableModel;
    private JTable muonTraTable;
    private JScrollPane jScrollPaneMuonTraTable;
    private JTextField diaChiField, tenField, lopField, tenSachField, searchField;
    private JComboBox<String> trangThaiComboBox;
    public JButton addMuonTraBtn, editMuonTraBtn, deleteMuonTraBtn, searchButton, switchToAnPhamViewBtn;
    private String[] columnNames = {"ID", "Địa chỉ", "Tên", "Lớp", "Tên sách", "Trạng thái"};

    public MuonTraView() {
        setTitle("Quản lý mượn trả");
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

        muonTraTable = new JTable(tableModel);
        muonTraTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPaneMuonTraTable = new JScrollPane(muonTraTable);
        jScrollPaneMuonTraTable.setPreferredSize(new Dimension(700, 300));

        diaChiField = new JTextField(20);
        tenField = new JTextField(20);
        lopField = new JTextField(10);
        tenSachField = new JTextField(20);
        trangThaiComboBox = new JComboBox<>(new String[]{"Đã trả", "Chưa trả"});

        addMuonTraBtn = new JButton("Thêm");
        editMuonTraBtn = new JButton("Sửa");
        deleteMuonTraBtn = new JButton("Xóa");

        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");

        switchToAnPhamViewBtn = new JButton("Chuyển sang An Phẩm");
    }

    private void setLayouts() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Địa chỉ:"));
        formPanel.add(diaChiField);
        formPanel.add(new JLabel("Tên:"));
        formPanel.add(tenField);
        formPanel.add(new JLabel("Lớp:"));
        formPanel.add(lopField);
        formPanel.add(new JLabel("Tên sách:"));
        formPanel.add(tenSachField);
        formPanel.add(new JLabel("Trạng thái:"));
        formPanel.add(trangThaiComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addMuonTraBtn);
        buttonPanel.add(editMuonTraBtn);
        buttonPanel.add(deleteMuonTraBtn);
        buttonPanel.add(switchToAnPhamViewBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(formPanel);
        panel.add(jScrollPaneMuonTraTable);
        panel.add(buttonPanel);
        panel.add(searchPanel);

        add(panel);
    }

    private void setupListeners() {
        muonTraTable.getSelectionModel().addListSelectionListener((ListSelectionListener) e -> {
            if (!e.getValueIsAdjusting()) {
                fillMuonTraFromSelectedRow();
            }
        });
    }

    public void addAddMuonTraListener(ActionListener listener) {
        addMuonTraBtn.addActionListener(listener);
    }

    public void addEditMuonTraListener(ActionListener listener) {
        editMuonTraBtn.addActionListener(listener);
    }

    public void addDeleteMuonTraListener(ActionListener listener) {
        deleteMuonTraBtn.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addSwitchToAnPhamViewListener(ActionListener listener) {
        switchToAnPhamViewBtn.addActionListener(listener);
    }

    public void resetButtons() {
        addMuonTraBtn.setEnabled(true);
        editMuonTraBtn.setEnabled(false);
        deleteMuonTraBtn.setEnabled(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListMuonTra(List<MuonTra> list) {
        int size = list.size();
        tableModel.setRowCount(0);
        Object[][] muonTraObjects = new Object[size][6];
        for (int i = 0; i < size; i++) {
            MuonTra mt = list.get(i);
            muonTraObjects[i][0] = mt.getId();
            muonTraObjects[i][1] = mt.getDiaChi();
            muonTraObjects[i][2] = mt.getTen();
            muonTraObjects[i][3] = mt.getLop();
            muonTraObjects[i][4] = mt.getTenSach();
            muonTraObjects[i][5] = mt.getTrangThai();
        }
        muonTraTable.setModel(new DefaultTableModel(muonTraObjects, columnNames));
    }

    public void fillMuonTraFromSelectedRow() {
        int row = muonTraTable.getSelectedRow();
        if (row != -1) {
            diaChiField.setText(muonTraTable.getValueAt(row, 1).toString());
            tenField.setText(muonTraTable.getValueAt(row, 2).toString());
            lopField.setText(muonTraTable.getValueAt(row, 3).toString());
            tenSachField.setText(muonTraTable.getValueAt(row, 4).toString());
            trangThaiComboBox.setSelectedItem(muonTraTable.getValueAt(row, 5).toString());

            editMuonTraBtn.setEnabled(true);
            deleteMuonTraBtn.setEnabled(true);
            addMuonTraBtn.setEnabled(false);
        }
    }

    public void clearMuonTraInfo() {
        diaChiField.setText("");
        tenField.setText("");
        lopField.setText("");
        tenSachField.setText("");
        trangThaiComboBox.setSelectedIndex(0);

        editMuonTraBtn.setEnabled(false);
        deleteMuonTraBtn.setEnabled(false);
        addMuonTraBtn.setEnabled(true);
    }

    public MuonTra getMuonTraInfo() {
        try {
            int id = -1; // Default to -1 for new item
            if (muonTraTable.getSelectedRow() != -1) {
                id = Integer.parseInt(muonTraTable.getValueAt(muonTraTable.getSelectedRow(), 0).toString());
            }

            String diaChi = diaChiField.getText();
            String ten = tenField.getText();
            String lop = lopField.getText();
            String tenSach = tenSachField.getText();
            String trangThai = (String) trangThaiComboBox.getSelectedItem();

            return new MuonTra(id, diaChi, ten, lop, tenSach, trangThai);
        } catch (NumberFormatException e) {
            showMessage("Lỗi: Vui lòng nhập đúng định dạng số.");
        } catch (Exception e) {
            showMessage("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void updateAfterEdit() {
        clearMuonTraInfo();
        resetButtons();
    }
}
