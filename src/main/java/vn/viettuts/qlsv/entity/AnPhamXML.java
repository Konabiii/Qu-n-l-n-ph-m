package vn.viettuts.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AnPhams")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnPhamXML {
    @XmlElement(name = "AnPham")
    private List<AnPham> anPhams = null;

    public List<AnPham> getAnPhams() {
        return anPhams;
    }

    public void setAnPhams(List<AnPham> anPhams) {
        this.anPhams = anPhams;
    }
}
