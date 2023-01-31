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
@Table(name = "horarios")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdHorarios", query = "SELECT h FROM Horario h WHERE h.idHorarios = :idHorarios"),
    @NamedQuery(name = "Horario.findByJornada", query = "SELECT h FROM Horario h WHERE h.jornada = :jornada"),
    @NamedQuery(name = "Horario.findByHoraPartida", query = "SELECT h FROM Horario h WHERE h.horaPartida = :horaPartida"),
    @NamedQuery(name = "Horario.findByHoraLLegada", query = "SELECT h FROM Horario h WHERE h.horaLLegada = :horaLLegada")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHorarios")
    private Integer idHorarios;
    @Basic(optional = false)
    @Column(name = "Jornada")
    private String jornada;
    @Basic(optional = false)
    @Column(name = "HoraPartida")
    private String horaPartida;
    @Basic(optional = false)
    @Column(name = "HoraLLegada")
    private String horaLLegada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosidHorarios")
    private List<Bus> buseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosidHorarios")
    private List<Estudiante> estudianteList;

    public Horario() {
    }

    public Horario(Integer idHorarios) {
        this.idHorarios = idHorarios;
    }

    public Horario(Integer idHorarios, String jornada, String horaPartida, String horaLLegada) {
        this.idHorarios = idHorarios;
        this.jornada = jornada;
        this.horaPartida = horaPartida;
        this.horaLLegada = horaLLegada;
    }

    public Integer getIdHorarios() {
        return idHorarios;
    }

    public void setIdHorarios(Integer idHorarios) {
        this.idHorarios = idHorarios;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(String horaLLegada) {
        this.horaLLegada = horaLLegada;
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
        hash += (idHorarios != null ? idHorarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorarios == null && other.idHorarios != null) || (this.idHorarios != null && !this.idHorarios.equals(other.idHorarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Horario[ idHorarios=" + idHorarios + " ]";
    }
    
}
