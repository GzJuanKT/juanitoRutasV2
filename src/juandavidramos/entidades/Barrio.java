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
@Table(name = "barrios")
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b"),
    @NamedQuery(name = "Barrio.findByIdBarrios", query = "SELECT b FROM Barrio b WHERE b.idBarrios = :idBarrios"),
    @NamedQuery(name = "Barrio.findByBarrio", query = "SELECT b FROM Barrio b WHERE b.barrio = :barrio")})
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBarrios")
    private Integer idBarrios;
    @Basic(optional = false)
    @Column(name = "barrio")
    private String barrio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barriosidBarrios")
    private List<Bus> buseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barriosidBarrios")
    private List<Estudiante> estudianteList;

    public Barrio() {
    }

    public Barrio(Integer idBarrios) {
        this.idBarrios = idBarrios;
    }

    public Barrio(Integer idBarrios, String barrio) {
        this.idBarrios = idBarrios;
        this.barrio = barrio;
    }

    public Integer getIdBarrios() {
        return idBarrios;
    }

    public void setIdBarrios(Integer idBarrios) {
        this.idBarrios = idBarrios;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public List<Bus> getBuseList() {
        return buseList;
    }

    public void setBuseList(List<Bus> buseList) {
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
        hash += (idBarrios != null ? idBarrios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        if ((this.idBarrios == null && other.idBarrios != null) || (this.idBarrios != null && !this.idBarrios.equals(other.idBarrios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Barrio[ idBarrios=" + idBarrios + " ]";
    }
    
}
