/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to editar this template
 */
package juandavidramos.daos;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import juandavidramos.entidades.Bus;
import juandavidramos.entidades.Estudiante;
import juandavidramos.entidades.Padrefamilia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juandavidramos.daos.exceptions.IllegalOrphanException;
import juandavidramos.daos.exceptions.NonexistentEntityException;
import juandavidramos.daos.exceptions.PreexistingEntityException;
import juandavidramos.entidades.Reporte;
import java.sql.*;

/**
 *
 * @author juand
 */
public class DaoReporte implements Serializable {

    public DaoReporte(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Reporte reporte) throws PreexistingEntityException, Exception {
        if (reporte.getPadrefamiliaList() == null) {
            reporte.setPadrefamiliaList(new ArrayList<Padrefamilia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus busesidBuses = reporte.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses = em.getReference(busesidBuses.getClass(), busesidBuses.getIdBuses());
                reporte.setBusesidBuses(busesidBuses);
            }
            Estudiante estudiantesidEstudiantes = reporte.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes = em.getReference(estudiantesidEstudiantes.getClass(), estudiantesidEstudiantes.getIdEstudiantes());
                reporte.setEstudiantesidEstudiantes(estudiantesidEstudiantes);
            }
            List<Padrefamilia> attachedPadrefamiliaList = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListPadrefamiliaToAttach : reporte.getPadrefamiliaList()) {
                padrefamiliaListPadrefamiliaToAttach = em.getReference(padrefamiliaListPadrefamiliaToAttach.getClass(), padrefamiliaListPadrefamiliaToAttach.getIdPadreFamilia());
                attachedPadrefamiliaList.add(padrefamiliaListPadrefamiliaToAttach);
            }
            reporte.setPadrefamiliaList(attachedPadrefamiliaList);
            em.persist(reporte);
            if (busesidBuses != null) {
                busesidBuses.getReporteList().add(reporte);
                busesidBuses = em.merge(busesidBuses);
            }
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getReporteList().add(reporte);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            for (Padrefamilia padrefamiliaListPadrefamilia : reporte.getPadrefamiliaList()) {
                Reporte oldReportesidReportesOfPadrefamiliaListPadrefamilia = padrefamiliaListPadrefamilia.getReportesidReportes();
                padrefamiliaListPadrefamilia.setReportesidReportes(reporte);
                padrefamiliaListPadrefamilia = em.merge(padrefamiliaListPadrefamilia);
                if (oldReportesidReportesOfPadrefamiliaListPadrefamilia != null) {
                    oldReportesidReportesOfPadrefamiliaListPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListPadrefamilia);
                    oldReportesidReportesOfPadrefamiliaListPadrefamilia = em.merge(oldReportesidReportesOfPadrefamiliaListPadrefamilia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarReporte(reporte.getIdReportes()) != null) {
                throw new PreexistingEntityException("Reporte " + reporte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Reporte reporte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte persistentReporte = em.find(Reporte.class, reporte.getIdReportes());
            Bus busesidBusesOld = persistentReporte.getBusesidBuses();
            Bus busesidBusesNew = reporte.getBusesidBuses();
            Estudiante estudiantesidEstudiantesOld = persistentReporte.getEstudiantesidEstudiantes();
            Estudiante estudiantesidEstudiantesNew = reporte.getEstudiantesidEstudiantes();
            List<Padrefamilia> padrefamiliaListOld = persistentReporte.getPadrefamiliaList();
            List<Padrefamilia> padrefamiliaListNew = reporte.getPadrefamiliaList();
            List<String> illegalOrphanMessages = null;
            for (Padrefamilia padrefamiliaListOldPadrefamilia : padrefamiliaListOld) {
                if (!padrefamiliaListNew.contains(padrefamiliaListOldPadrefamilia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Padrefamilia " + padrefamiliaListOldPadrefamilia + " since its reportesidReportes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (busesidBusesNew != null) {
                busesidBusesNew = em.getReference(busesidBusesNew.getClass(), busesidBusesNew.getIdBuses());
                reporte.setBusesidBuses(busesidBusesNew);
            }
            if (estudiantesidEstudiantesNew != null) {
                estudiantesidEstudiantesNew = em.getReference(estudiantesidEstudiantesNew.getClass(), estudiantesidEstudiantesNew.getIdEstudiantes());
                reporte.setEstudiantesidEstudiantes(estudiantesidEstudiantesNew);
            }
            List<Padrefamilia> attachedPadrefamiliaListNew = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListNewPadrefamiliaToAttach : padrefamiliaListNew) {
                padrefamiliaListNewPadrefamiliaToAttach = em.getReference(padrefamiliaListNewPadrefamiliaToAttach.getClass(), padrefamiliaListNewPadrefamiliaToAttach.getIdPadreFamilia());
                attachedPadrefamiliaListNew.add(padrefamiliaListNewPadrefamiliaToAttach);
            }
            padrefamiliaListNew = attachedPadrefamiliaListNew;
            reporte.setPadrefamiliaList(padrefamiliaListNew);
            reporte = em.merge(reporte);
            if (busesidBusesOld != null && !busesidBusesOld.equals(busesidBusesNew)) {
                busesidBusesOld.getReporteList().remove(reporte);
                busesidBusesOld = em.merge(busesidBusesOld);
            }
            if (busesidBusesNew != null && !busesidBusesNew.equals(busesidBusesOld)) {
                busesidBusesNew.getReporteList().add(reporte);
                busesidBusesNew = em.merge(busesidBusesNew);
            }
            if (estudiantesidEstudiantesOld != null && !estudiantesidEstudiantesOld.equals(estudiantesidEstudiantesNew)) {
                estudiantesidEstudiantesOld.getReporteList().remove(reporte);
                estudiantesidEstudiantesOld = em.merge(estudiantesidEstudiantesOld);
            }
            if (estudiantesidEstudiantesNew != null && !estudiantesidEstudiantesNew.equals(estudiantesidEstudiantesOld)) {
                estudiantesidEstudiantesNew.getReporteList().add(reporte);
                estudiantesidEstudiantesNew = em.merge(estudiantesidEstudiantesNew);
            }
            for (Padrefamilia padrefamiliaListNewPadrefamilia : padrefamiliaListNew) {
                if (!padrefamiliaListOld.contains(padrefamiliaListNewPadrefamilia)) {
                    Reporte oldReportesidReportesOfPadrefamiliaListNewPadrefamilia = padrefamiliaListNewPadrefamilia.getReportesidReportes();
                    padrefamiliaListNewPadrefamilia.setReportesidReportes(reporte);
                    padrefamiliaListNewPadrefamilia = em.merge(padrefamiliaListNewPadrefamilia);
                    if (oldReportesidReportesOfPadrefamiliaListNewPadrefamilia != null && !oldReportesidReportesOfPadrefamiliaListNewPadrefamilia.equals(reporte)) {
                        oldReportesidReportesOfPadrefamiliaListNewPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListNewPadrefamilia);
                        oldReportesidReportesOfPadrefamiliaListNewPadrefamilia = em.merge(oldReportesidReportesOfPadrefamiliaListNewPadrefamilia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reporte.getIdReportes();
                if (buscarReporte(id) == null) {
                    throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte reporte;
            try {
                reporte = em.getReference(Reporte.class, id);
                reporte.getIdReportes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Padrefamilia> padrefamiliaListOrphanCheck = reporte.getPadrefamiliaList();
            for (Padrefamilia padrefamiliaListOrphanCheckPadrefamilia : padrefamiliaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reporte (" + reporte + ") cannot be destroyed since the Padrefamilia " + padrefamiliaListOrphanCheckPadrefamilia + " in its padrefamiliaList field has a non-nullable reportesidReportes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Bus busesidBuses = reporte.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses.getReporteList().remove(reporte);
                busesidBuses = em.merge(busesidBuses);
            }
            Estudiante estudiantesidEstudiantes = reporte.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getReporteList().remove(reporte);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            em.remove(reporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reporte> listarTodosLosReportes() {
        return findReporteEntities(true, -1, -1);
    }

    public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
        return findReporteEntities(false, maxResults, firstResult);
    }

    private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reporte.class));
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

    public Reporte buscarReporte(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporte.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getTotalReporte() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reporte> rt = cq.from(Reporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
