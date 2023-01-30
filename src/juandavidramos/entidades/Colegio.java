/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juandavidramos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "colegios")
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c"),
    @NamedQuery(name = "Colegio.findByIdColegios", query = "SELECT c FROM Colegio c WHERE c.idColegios = :idColegios"),
    @NamedQuery(name = "Colegio.findByColegio", query = "SELECT c FROM Colegio c WHERE c.colegio = :colegio")})
public class Colegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idColegios")
    private Integer idColegios;
    @Basic(optional = false)
    @Column(name = "colegio")
    private String colegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colegiosidColegios")
    private List<Buse> buseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colegiosidColegios")
    private List<Estudiante> estudianteList;

    public Colegio() {
    }

    public Colegio(Integer idColegios) {
        this.idColegios = idColegios;
    }

    public Colegio(Integer idColegios, String colegio) {
        this.idColegios = idColegios;
        this.colegio = colegio;
    }

    public Integer getIdColegios() {
        return idColegios;
    }

    public void setIdColegios(Integer idColegios) {
        this.idColegios = idColegios;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public List<Buse> getBuseList() {
        return buseList;
    }

    public void setBuseList(List<Buse> buseList) {
        this.buseList = buseList;
    }

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColegios != null ? idColegios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idColegios == null && other.idColegios != null) || (this.idColegios != null && !this.idColegios.equals(other.idColegios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Colegio[ idColegios=" + idColegios + " ]";
    }
    
}
