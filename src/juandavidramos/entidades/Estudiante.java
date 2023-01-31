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
@Table(name = "estudiantes")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiantes", query = "SELECT e FROM Estudiante e WHERE e.idEstudiantes = :idEstudiantes"),
    @NamedQuery(name = "Estudiante.findByNombre", query = "SELECT e FROM Estudiante e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estudiante.findByApellido", query = "SELECT e FROM Estudiante e WHERE e.apellido = :apellido")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstudiantes")
    private Integer idEstudiantes;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Apellido")
    private String apellido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantesidEstudiantes")
    private List<Padrefamilia> padrefamiliaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantesidEstudiantes")
    private List<Reporte> reporteList;
    @JoinColumn(name = "Barrios_idBarrios", referencedColumnName = "idBarrios")
    @ManyToOne(optional = false)
    private Barrio barriosidBarrios;
    @JoinColumn(name = "Buses_idBuses", referencedColumnName = "idBuses")
    @ManyToOne(optional = false)
    private Bus busesidBuses;
    @JoinColumn(name = "Colegios_idColegios", referencedColumnName = "idColegios")
    @ManyToOne(optional = false)
    private Colegio colegiosidColegios;
    @JoinColumn(name = "Horarios_idHorarios", referencedColumnName = "idHorarios")
    @ManyToOne(optional = false)
    private Horario horariosidHorarios;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiantes) {
        this.idEstudiantes = idEstudiantes;
    }

    public Estudiante(Integer idEstudiantes, String nombre, String apellido) {
        this.idEstudiantes = idEstudiantes;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdEstudiantes() {
        return idEstudiantes;
    }

    public void setIdEstudiantes(Integer idEstudiantes) {
        this.idEstudiantes = idEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Padrefamilia> getPadrefamiliaList() {
        return padrefamiliaList;
    }

    public void setPadrefamiliaList(List<Padrefamilia> padrefamiliaList) {
        this.padrefamiliaList = padrefamiliaList;
    }

    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    public Barrio getBarriosidBarrios() {
        return barriosidBarrios;
    }

    public void setBarriosidBarrios(Barrio barriosidBarrios) {
        this.barriosidBarrios = barriosidBarrios;
    }

    public Bus getBusesidBuses() {
        return busesidBuses;
    }

    public void setBusesidBuses(Bus busesidBuses) {
        this.busesidBuses = busesidBuses;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiantes != null ? idEstudiantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiantes == null && other.idEstudiantes != null) || (this.idEstudiantes != null && !this.idEstudiantes.equals(other.idEstudiantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Estudiante[ idEstudiantes=" + idEstudiantes + " ]";
    }
    
}
