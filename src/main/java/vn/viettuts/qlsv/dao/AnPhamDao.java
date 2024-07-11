package vn.viettuts.qlsv.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import vn.viettuts.qlsv.entity.AnPham;
import vn.viettuts.qlsv.entity.AnPhamXML;

public class AnPhamDao {
    private static final String FILE_PATH = "anpham.xml";

    public List<AnPham> getListAnPhams() {
        List<AnPham> anPhams = new ArrayList<>();
        try {
            File xmlFile = new File(FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(AnPhamXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            AnPhamXML anPhamList = (AnPhamXML) jaxbUnmarshaller.unmarshal(xmlFile);
            if (anPhamList != null && anPhamList.getAnPhams() != null) {
                anPhams = anPhamList.getAnPhams();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anPhams;
    }

    public void add(AnPham anPham) {
        List<AnPham> anPhams = getListAnPhams();
        int nextId = generateNextId(anPhams); // Generate the next available ID
        anPham.setId(nextId);
        anPhams.add(anPham);
        writeListToXml(anPhams);
    }

    public void edit(AnPham anPham) {
        List<AnPham> anPhams = getListAnPhams();
        for (int i = 0; i < anPhams.size(); i++) {
            if (anPhams.get(i).getId() == anPham.getId()) {
                anPhams.set(i, anPham);
                writeListToXml(anPhams);
                return;
            }
        }
    }

    public boolean delete(int id) {
        List<AnPham> anPhams = getListAnPhams();
        boolean removed = anPhams.removeIf(anPham -> anPham.getId() == id);

        if (removed) {
            // Update IDs after deletion
            for (int i = 0; i < anPhams.size(); i++) {
                anPhams.get(i).setId(i + 1); // IDs start from 1
            }
            writeListToXml(anPhams);
        }

        return removed;
    }

    public void sortAnPhamByGiaThanh() {
        List<AnPham> anPhams = getListAnPhams();
        anPhams.sort(Comparator.comparingDouble(AnPham::getGiaThanh));
        writeListToXml(anPhams);
    }

    public void sortAnPhamByTenAnPham() {
        List<AnPham> anPhams = getListAnPhams();
        anPhams.sort(Comparator.comparing(AnPham::getTenAnPham));
        writeListToXml(anPhams);
    }

    public List<AnPham> searchAnPhamByName(String name) {
        List<AnPham> anPhams = getListAnPhams();
        return anPhams.stream()
                .filter(anPham -> anPham.getTenAnPham().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void writeListToXml(List<AnPham> anPhams) {
        try {
            File xmlFile = new File(FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(AnPhamXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            AnPhamXML anPhamList = new AnPhamXML();
            anPhamList.setAnPhams(anPhams);

            jaxbMarshaller.marshal(anPhamList, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int generateNextId(List<AnPham> anPhams) {
        int maxId = 0;
        for (AnPham anPham : anPhams) {
            if (anPham.getId() > maxId) {
                maxId = anPham.getId();
            }
        }
        return maxId + 1; // Return the next available ID
    }
}
