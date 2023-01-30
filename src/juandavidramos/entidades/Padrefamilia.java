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
@Table(name = "padrefamilia")
@NamedQueries({
    @NamedQuery(name = "Padrefamilia.findAll", query = "SELECT p FROM Padrefamilia p"),
    @NamedQuery(name = "Padrefamilia.findByIdPadreFamilia", query = "SELECT p FROM Padrefamilia p WHERE p.idPadreFamilia = :idPadreFamilia"),
    @NamedQuery(name = "Padrefamilia.findByNombrePadre", query = "SELECT p FROM Padrefamilia p WHERE p.nombrePadre = :nombrePadre")})
public class Padrefamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPadreFamilia")
    private Integer idPadreFamilia;
    @Basic(optional = false)
    @Column(name = "NombrePadre")
    private String nombrePadre;
    @JoinColumn(name = "Estudiantes_idEstudiantes", referencedColumnName = "idEstudiantes")
    @ManyToOne(optional = false)
    private Estudiante estudiantesidEstudiantes;
    @JoinColumn(name = "Reportes_idReportes", referencedColumnName = "idReportes")
    @ManyToOne(optional = false)
    private Reporte reportesidReportes;

    public Padrefamilia() {
    }

    public Padrefamilia(Integer idPadreFamilia) {
        this.idPadreFamilia = idPadreFamilia;
    }

    public Padrefamilia(Integer idPadreFamilia, String nombrePadre) {
        this.idPadreFamilia = idPadreFamilia;
        this.nombrePadre = nombrePadre;
    }

    public Integer getIdPadreFamilia() {
        return idPadreFamilia;
    }

    public void setIdPadreFamilia(Integer idPadreFamilia) {
        this.idPadreFamilia = idPadreFamilia;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public Estudiante getEstudiantesidEstudiantes() {
        return estudiantesidEstudiantes;
    }

    public void setEstudiantesidEstudiantes(Estudiante estudiantesidEstudiantes) {
        this.estudiantesidEstudiantes = estudiantesidEstudiantes;
    }

    public Reporte getReportesidReportes() {
        return reportesidReportes;
    }

    public void setReportesidReportes(Reporte reportesidReportes) {
        this.reportesidReportes = reportesidReportes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPadreFamilia != null ? idPadreFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Padrefamilia)) {
            return false;
        }
        Padrefamilia other = (Padrefamilia) object;
        if ((this.idPadreFamilia == null && other.idPadreFamilia != null) || (this.idPadreFamilia != null && !this.idPadreFamilia.equals(other.idPadreFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juandavidramos.entidades.Padrefamilia[ idPadreFamilia=" + idPadreFamilia + " ]";
    }
    
}
