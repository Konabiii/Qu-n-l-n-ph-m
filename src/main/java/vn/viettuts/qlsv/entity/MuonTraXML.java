package vn.viettuts.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MuonTras")
@XmlAccessorType(XmlAccessType.FIELD)
public class MuonTraXML {
    
    @XmlElement(name = "MuonTra")
    private List<MuonTra> muonTras = null;

    public List<MuonTra> getMuonTras() {
        return muonTras;
    }

    public void setMuonTras(List<MuonTra> muonTras) {
        this.muonTras = muonTras;
    }
}
