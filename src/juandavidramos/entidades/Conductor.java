/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juandavidramos.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "conductores")
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByIdConductores", query = "SELECT c FROM Conductor c WHERE c.idConductores = :idConductores"),
    @NamedQuery(name = "Conductor.findByNombreConductor", query = "SELECT c FROM Conductor c WHERE c.nombreConductor = :nombreConductor")})
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConductores")
    private Integer idConductores;
    @Basic(optional = false)
    @Column(name = "nombreConductor")
    private String nombreConductor;
    @JoinColumn(name = "Buses_idBuses", referencedColumnName = "idBuses")
    @ManyToOne(optional = false)
    private Bus busesidBuses;

    public Conductor() {
    }

    public Conductor(Integer idConductores) {
        this.idConductores = idConductores;
    }

    public Conductor(Integer idConductores, String nombreConductor) {
        this.idConductores = idConductores;
        this.nombreConductor = nombreConductor;
    }

    public Integer getIdConductores() {
        return idConductores;
    }

    public void setIdConductores(Integer idConductores) {
        this.idConductores = idConductores;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public Bus getBusesidBuses() {
        return busesidBuses;
    }

    public void setBusesidBuses(Bus busesidBuses) {
        this.busesidBuses = busesidBuses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConductores != null ? idConductores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.idConductores == null && other.idConductores != null) || (this.idConductores != null && !this.idConductores.equals(other.idConductores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Conductor[ idConductores=" + idConductores + " ]";
    }
    
}
