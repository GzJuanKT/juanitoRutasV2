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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "buses")
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findByIdBuses", query = "SELECT b FROM Bus b WHERE b.idBuses = :idBuses")})
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idBuses")
    private String idBuses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesidBuses")
    private List<Conductor> conductorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesidBuses")
    private List<Tutor> tutorList;
    @JoinColumn(name = "Barrios_idBarrios", referencedColumnName = "idBarrios")
    @ManyToOne(optional = false)
    private Barrio barriosidBarrios;
    @JoinColumn(name = "Colegios_idColegios", referencedColumnName = "idColegios")
    @ManyToOne(optional = false)
    private Colegio colegiosidColegios;
    @JoinColumn(name = "Horarios_idHorarios", referencedColumnName = "idHorarios")
    @ManyToOne(optional = false)
    private Horario horariosidHorarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesidBuses")
    private List<Reporte> reporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesidBuses")
    private List<Estudiante> estudianteList;

    public Bus() {
    }

    public Bus(String idBuses) {
        this.idBuses = idBuses;
    }

    public String getIdBuses() {
        return idBuses;
    }

    public void setIdBuses(String idBuses) {
        this.idBuses = idBuses;
    }

    public List<Conductor> getConductorList() {
        return conductorList;
    }

    public void setConductorList(List<Conductor> conductorList) {
        this.conductorList = conductorList;
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public Barrio getBarriosidBarrios() {
        return barriosidBarrios;
    }

    public void setBarriosidBarrios(Barrio barriosidBarrios) {
        this.barriosidBarrios = barriosidBarrios;
    }

    public Colegio getColegiosidColegios() {
        return colegiosidColegios;
    }

    public void setColegiosidColegios(Colegio colegiosidColegios) {
        this.colegiosidColegios = colegiosidColegios;
    }

    public Horario getHorariosidHorarios() {
        return horariosidHorarios;
    }

    public void setHorariosidHorarios(Horario horariosidHorarios) {
        this.horariosidHorarios = horariosidHorarios;
    }

    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
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
        hash += (idBuses != null ? idBuses.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.idBuses == null && other.idBuses != null) || (this.idBuses != null && !this.idBuses.equals(other.idBuses))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Buse[ idBuses=" + idBuses + " ]";
    }
    
}
