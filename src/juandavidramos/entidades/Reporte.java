/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juandavidramos.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "reportes")
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findByIdReportes", query = "SELECT r FROM Reporte r WHERE r.idReportes = :idReportes"),
    @NamedQuery(name = "Reporte.findByFechaReporte", query = "SELECT r FROM Reporte r WHERE r.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "Reporte.findByPagoDeServicio", query = "SELECT r FROM Reporte r WHERE r.pagoDeServicio = :pagoDeServicio")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idReportes")
    private String idReportes;
    @Basic(optional = false)
    @Column(name = "fechaReporte")
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @Basic(optional = false)
    @Column(name = "pagoDeServicio")
    private double pagoDeServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportesidReportes")
    private List<Padrefamilia> padrefamiliaList;
    @JoinColumn(name = "Buses_idBuses", referencedColumnName = "idBuses")
    @ManyToOne(optional = false)
    private Bus busesidBuses;
    @JoinColumn(name = "Estudiantes_idEstudiantes", referencedColumnName = "idEstudiantes")
    @ManyToOne(optional = false)
    private Estudiante estudiantesidEstudiantes;

    public Reporte() {
    }

    public Reporte(String idReportes) {
        this.idReportes = idReportes;
    }

    public Reporte(String idReportes, Date fechaReporte, double pagoDeServicio) {
        this.idReportes = idReportes;
        this.fechaReporte = fechaReporte;
        this.pagoDeServicio = pagoDeServicio;
    }

    public String getIdReportes() {
        return idReportes;
    }

    public void setIdReportes(String idReportes) {
        this.idReportes = idReportes;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public double getPagoDeServicio() {
        return pagoDeServicio;
    }

    public void setPagoDeServicio(double pagoDeServicio) {
        this.pagoDeServicio = pagoDeServicio;
    }

    public List<Padrefamilia> getPadrefamiliaList() {
        return padrefamiliaList;
    }

    public void setPadrefamiliaList(List<Padrefamilia> padrefamiliaList) {
        this.padrefamiliaList = padrefamiliaList;
    }

    public Bus getBusesidBuses() {
        return busesidBuses;
    }

    public void setBusesidBuses(Bus busesidBuses) {
        this.busesidBuses = busesidBuses;
    }

    public Estudiante getEstudiantesidEstudiantes() {
        return estudiantesidEstudiantes;
    }

    public void setEstudiantesidEstudiantes(Estudiante estudiantesidEstudiantes) {
        this.estudiantesidEstudiantes = estudiantesidEstudiantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReportes != null ? idReportes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.idReportes == null && other.idReportes != null) || (this.idReportes != null && !this.idReportes.equals(other.idReportes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Reporte[ idReportes=" + idReportes + " ]";
    }
    
}
