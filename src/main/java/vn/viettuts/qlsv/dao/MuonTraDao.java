package vn.viettuts.qlsv.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import vn.viettuts.qlsv.entity.MuonTra;
import vn.viettuts.qlsv.entity.MuonTraXML;

public class MuonTraDao {
    private static final String FILE_PATH = "muontra.xml";

    public List<MuonTra> getListMuonTra() {
        List<MuonTra> muonTras = new ArrayList<>();
        try {
            File xmlFile = new File(FILE_PATH);
            if (xmlFile.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(MuonTraXML.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                MuonTraXML muonTraList = (MuonTraXML) jaxbUnmarshaller.unmarshal(xmlFile);
                if (muonTraList != null && muonTraList.getMuonTras() != null) {
                    muonTras = muonTraList.getMuonTras();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return muonTras;
    }

    public void addMuonTra(MuonTra muonTra) {
        List<MuonTra> muonTras = getListMuonTra();
        int maxId = muonTras.stream().mapToInt(MuonTra::getId).max().orElse(0);
        muonTra.setId(maxId + 1);
        muonTras.add(muonTra);
        writeListToXml(muonTras);
    }

    public boolean updateMuonTra(MuonTra muonTra) {
        List<MuonTra> muonTras = getListMuonTra();
        boolean found = false;
        for (int i = 0; i < muonTras.size(); i++) {
            if (muonTras.get(i).getId() == muonTra.getId()) {
                muonTras.set(i, muonTra);
                found = true;
                break;
            }
        }
        if (found) {
            writeListToXml(muonTras);
        }
        return found;
    }

    public boolean deleteMuonTra(int id) {
        List<MuonTra> muonTras = getListMuonTra();
        boolean removed = muonTras.removeIf(muonTra -> muonTra.getId() == id);
        if (removed) {
            for (int i = 0; i < muonTras.size(); i++) {
                muonTras.get(i).setId(i + 1);
            }
            writeListToXml(muonTras);
        }
        return removed;
    }

    public List<MuonTra> searchMuonTraByTen(String ten) {
        List<MuonTra> muonTras = getListMuonTra();
        return muonTras.stream()
                       .filter(muonTra -> muonTra.getTen().toLowerCase().contains(ten.toLowerCase()))
                       .collect(Collectors.toList());
    }

    private void writeListToXml(List<MuonTra> muonTras) {
        try {
            File xmlFile = new File(FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(MuonTraXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            MuonTraXML muonTraList = new MuonTraXML();
            muonTraList.setMuonTras(muonTras);

            jaxbMarshaller.marshal(muonTraList, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
