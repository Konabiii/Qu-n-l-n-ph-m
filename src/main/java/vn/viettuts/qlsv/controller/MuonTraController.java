package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import vn.viettuts.qlsv.dao.MuonTraDao;
import vn.viettuts.qlsv.entity.MuonTra;
import vn.viettuts.qlsv.view.AnPhamView;
import vn.viettuts.qlsv.view.MuonTraView;

public class MuonTraController {
    private MuonTraDao muonTraDao;
    private MuonTraView muonTraView;
    private AnPhamView anPhamView;

    public MuonTraController(MuonTraView muonTraView, AnPhamView anPhamView) {
        this.muonTraView = muonTraView;
        this.anPhamView = anPhamView;
        this.muonTraDao = new MuonTraDao();

        muonTraView.addAddMuonTraListener(new AddMuonTraListener());
        muonTraView.addEditMuonTraListener(new EditMuonTraListener());
        muonTraView.addDeleteMuonTraListener(new DeleteMuonTraListener());
        muonTraView.addSearchListener(new SearchMuonTraListener());
        muonTraView.addSwitchToAnPhamViewListener(new SwitchToAnPhamViewListener());
    }

    public void showMuonTraView() {
        List<MuonTra> muonTraList = muonTraDao.getListMuonTra();
        muonTraView.setVisible(true);
        anPhamView.setVisible(false);
        muonTraView.showListMuonTra(muonTraList);
    }

    public class AddMuonTraListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MuonTra muonTra = muonTraView.getMuonTraInfo();
            if (muonTra != null) {
                muonTraDao.addMuonTra(muonTra);
                refreshView(); // Cập nhật lại giao diện sau khi thêm thành công
                muonTraView.showMessage("Thêm thành công!");
            }
        }
    }

    public class EditMuonTraListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MuonTra muonTra = muonTraView.getMuonTraInfo();
            if (muonTra != null) {
                muonTraDao.updateMuonTra(muonTra);
                refreshView();
                muonTraView.showMessage("Cập nhật thành công!");
            }
        }
    }

    public class DeleteMuonTraListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MuonTra muonTra = muonTraView.getMuonTraInfo();
            if (muonTra != null) {
                boolean deleted = muonTraDao.deleteMuonTra(muonTra.getId());
                if (deleted) {
                    refreshView();
                    muonTraView.showMessage("Xóa thành công!");
                } else {
                    refreshView();
                    muonTraView.showMessage("Xóa thất bại! Không tìm thấy mượn trả để xóa.");
                }
            }
        }
    }

    public class SearchMuonTraListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = muonTraView.getSearchField().getText();
            List<MuonTra> filteredList = muonTraDao.searchMuonTraByTen(query);
            muonTraView.showListMuonTra(filteredList);
        }
    }

    public class SwitchToAnPhamViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            anPhamView.setVisible(true);
            muonTraView.setVisible(false);
            AnPhamController anPhamController = new AnPhamController(anPhamView, muonTraView);
            anPhamController.showAnPhamView();
        }
    }

    private void refreshView() {
        List<MuonTra> updatedList = muonTraDao.getListMuonTra();
        muonTraView.showListMuonTra(updatedList);
        muonTraView.clearMuonTraInfo();
        muonTraView.resetButtons();
    }
}
