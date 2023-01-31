/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to editar this template
 */
package juandavidramos.daos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import juandavidramos.daos.exceptions.NonexistentEntityException;
import juandavidramos.entidades.Estudiante;
import juandavidramos.entidades.Padrefamilia;
import juandavidramos.entidades.Reporte;

/**
 *
 * @author juand
 */
public class DaoPadreFamilia implements Serializable {

    public DaoPadreFamilia(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Padrefamilia padrefamilia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiantesidEstudiantes = padrefamilia.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes = em.getReference(estudiantesidEstudiantes.getClass(), estudiantesidEstudiantes.getIdEstudiantes());
                padrefamilia.setEstudiantesidEstudiantes(estudiantesidEstudiantes);
            }
            Reporte reportesidReportes = padrefamilia.getReportesidReportes();
            if (reportesidReportes != null) {
                reportesidReportes = em.getReference(reportesidReportes.getClass(), reportesidReportes.getIdReportes());
                padrefamilia.setReportesidReportes(reportesidReportes);
            }
            em.persist(padrefamilia);
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getPadrefamiliaList().add(padrefamilia);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            if (reportesidReportes != null) {
                reportesidReportes.getPadrefamiliaList().add(padrefamilia);
                reportesidReportes = em.merge(reportesidReportes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Padrefamilia padrefamilia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Padrefamilia persistentPadrefamilia = em.find(Padrefamilia.class, padrefamilia.getIdPadreFamilia());
            Estudiante estudiantesidEstudiantesOld = persistentPadrefamilia.getEstudiantesidEstudiantes();
            Estudiante estudiantesidEstudiantesNew = padrefamilia.getEstudiantesidEstudiantes();
            Reporte reportesidReportesOld = persistentPadrefamilia.getReportesidReportes();
            Reporte reportesidReportesNew = padrefamilia.getReportesidReportes();
            if (estudiantesidEstudiantesNew != null) {
                estudiantesidEstudiantesNew = em.getReference(estudiantesidEstudiantesNew.getClass(), estudiantesidEstudiantesNew.getIdEstudiantes());
                padrefamilia.setEstudiantesidEstudiantes(estudiantesidEstudiantesNew);
            }
            if (reportesidReportesNew != null) {
                reportesidReportesNew = em.getReference(reportesidReportesNew.getClass(), reportesidReportesNew.getIdReportes());
                padrefamilia.setReportesidReportes(reportesidReportesNew);
            }
            padrefamilia = em.merge(padrefamilia);
            if (estudiantesidEstudiantesOld != null && !estudiantesidEstudiantesOld.equals(estudiantesidEstudiantesNew)) {
                estudiantesidEstudiantesOld.getPadrefamiliaList().remove(padrefamilia);
                estudiantesidEstudiantesOld = em.merge(estudiantesidEstudiantesOld);
            }
            if (estudiantesidEstudiantesNew != null && !estudiantesidEstudiantesNew.equals(estudiantesidEstudiantesOld)) {
                estudiantesidEstudiantesNew.getPadrefamiliaList().add(padrefamilia);
                estudiantesidEstudiantesNew = em.merge(estudiantesidEstudiantesNew);
            }
            if (reportesidReportesOld != null && !reportesidReportesOld.equals(reportesidReportesNew)) {
                reportesidReportesOld.getPadrefamiliaList().remove(padrefamilia);
                reportesidReportesOld = em.merge(reportesidReportesOld);
            }
            if (reportesidReportesNew != null && !reportesidReportesNew.equals(reportesidReportesOld)) {
                reportesidReportesNew.getPadrefamiliaList().add(padrefamilia);
                reportesidReportesNew = em.merge(reportesidReportesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = padrefamilia.getIdPadreFamilia();
                if (buscarPadrefamilia(id) == null) {
                    throw new NonexistentEntityException("The padrefamilia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Padrefamilia padrefamilia;
            try {
                padrefamilia = em.getReference(Padrefamilia.class, id);
                padrefamilia.getIdPadreFamilia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The padrefamilia with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudiantesidEstudiantes = padrefamilia.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getPadrefamiliaList().remove(padrefamilia);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            Reporte reportesidReportes = padrefamilia.getReportesidReportes();
            if (reportesidReportes != null) {
                reportesidReportes.getPadrefamiliaList().remove(padrefamilia);
                reportesidReportes = em.merge(reportesidReportes);
            }
            em.remove(padrefamilia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Padrefamilia> listarTodosLosPadresFamilia() {
        return findPadrefamiliaEntities(true, -1, -1);
    }

    public List<Padrefamilia> findPadrefamiliaEntities(int maxResults, int firstResult) {
        return findPadrefamiliaEntities(false, maxResults, firstResult);
    }

    private List<Padrefamilia> findPadrefamiliaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Padrefamilia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Padrefamilia buscarPadrefamilia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Padrefamilia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalPadreFamilia() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Padrefamilia> rt = cq.from(Padrefamilia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
