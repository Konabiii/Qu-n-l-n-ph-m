package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import vn.viettuts.qlsv.dao.AnPhamDao;
import vn.viettuts.qlsv.entity.AnPham;
import vn.viettuts.qlsv.view.AnPhamView;
import vn.viettuts.qlsv.view.MuonTraView;

public class AnPhamController {
    private AnPhamDao anPhamDao;
    private AnPhamView anPhamView;
    private MuonTraView muonTraView;

    public AnPhamController(AnPhamView anPhamView, MuonTraView muonTraView) {
        this.anPhamView = anPhamView;
        this.muonTraView = muonTraView;
        anPhamDao = new AnPhamDao();

        // Add listeners to the AnPhamView
        anPhamView.addAddAnPhamListener(new AddAnPhamListener());
        anPhamView.addEditAnPhamListener(new EditAnPhamListener());
        anPhamView.addDeleteAnPhamListener(new DeleteAnPhamListener());
        anPhamView.addSortAnPhamGiaThanhListener(new SortAnPhamGiaThanhListener());
        anPhamView.addSortAnPhamTenListener(new SortAnPhamTenListener());
        anPhamView.addSearchListener(new SearchAnPhamListener());
        anPhamView.addSwitchToMuonTraViewListener(new SwitchToMuonTraViewListener());

        // Show the initial view
        showAnPhamView();
    }

    public void showAnPhamView() {
        List<AnPham> anPhamList = anPhamDao.getListAnPhams();
        anPhamView.setVisible(true);
        muonTraView.setVisible(false);
        anPhamView.showListAnPham(anPhamList);
    }

    public class AddAnPhamListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AnPham anPham = anPhamView.getAnPhamInfo();
            if (anPham != null) {
                anPhamDao.add(anPham);
                List<AnPham> updatedList = anPhamDao.getListAnPhams();
                anPhamView.showListAnPham(updatedList); // Hiển thị danh sách đã cập nhật
                anPhamView.clearAnPhamInfo();
                anPhamView.showMessage("Thêm thành công!");
            }
        }
    }

    public class EditAnPhamListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AnPham anPham = anPhamView.getAnPhamInfo();
            if (anPham != null) {
                anPhamDao.edit(anPham);
                List<AnPham> updatedList = anPhamDao.getListAnPhams();
                anPhamView.showListAnPham(updatedList); // Hiển thị danh sách đã cập nhật
                anPhamView.clearAnPhamInfo();
                anPhamView.showMessage("Cập nhật thành công!");
            }
        }
    }

    public class DeleteAnPhamListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AnPham anPham = anPhamView.getAnPhamInfo();
            if (anPham != null) {
                boolean deleted = anPhamDao.delete(anPham.getId());
                if (deleted) {
                    List<AnPham> updatedList = anPhamDao.getListAnPhams();
                    anPhamView.showListAnPham(updatedList); // Hiển thị danh sách đã cập nhật
                    anPhamView.clearAnPhamInfo();
                    anPhamView.showMessage("Xóa thành công!");
                } else {
                    anPhamView.showMessage("Xóa thất bại! Không tìm thấy sản phẩm để xóa.");
                }
            }
        }
    }
   public class SortAnPhamGiaThanhListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            anPhamDao.sortAnPhamByGiaThanh();
            List<AnPham> sortedList = anPhamDao.getListAnPhams();
            anPhamView.showListAnPham(sortedList); // Hiển thị danh sách đã sắp xếp
        }
    }

    public class SortAnPhamTenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            anPhamDao.sortAnPhamByTenAnPham();
            List<AnPham> sortedList = anPhamDao.getListAnPhams();
            anPhamView.showListAnPham(sortedList); // Hiển thị danh sách đã sắp xếp
        }
    }


    public class SearchAnPhamListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String query = anPhamView.getSearchField().getText();
            anPhamView.filterTable(query);
        }
    }

    public class SwitchToMuonTraViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            anPhamView.setVisible(false);
            muonTraView.setVisible(true);
            MuonTraController muonTraController = new MuonTraController(muonTraView, anPhamView);
            muonTraController.showMuonTraView();
        }
    }
}
