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
@Table(name = "tutores")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByIdTutores", query = "SELECT t FROM Tutor t WHERE t.idTutores = :idTutores"),
    @NamedQuery(name = "Tutor.findByNombreTutor", query = "SELECT t FROM Tutor t WHERE t.nombreTutor = :nombreTutor")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTutores")
    private Integer idTutores;
    @Basic(optional = false)
    @Column(name = "nombreTutor")
    private String nombreTutor;
    @JoinColumn(name = "Buses_idBuses", referencedColumnName = "idBuses")
    @ManyToOne(optional = false)
    private Buse busesidBuses;

    public Tutor() {
    }

    public Tutor(Integer idTutores) {
        this.idTutores = idTutores;
    }

    public Tutor(Integer idTutores, String nombreTutor) {
        this.idTutores = idTutores;
        this.nombreTutor = nombreTutor;
    }

    public Integer getIdTutores() {
        return idTutores;
    }

    public void setIdTutores(Integer idTutores) {
        this.idTutores = idTutores;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public Buse getBusesidBuses() {
        return busesidBuses;
    }

    public void setBusesidBuses(Buse busesidBuses) {
        this.busesidBuses = busesidBuses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTutores != null ? idTutores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.idTutores == null && other.idTutores != null) || (this.idTutores != null && !this.idTutores.equals(other.idTutores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Tutor[ idTutores=" + idTutores + " ]";
    }
    
}
