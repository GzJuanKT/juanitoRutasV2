/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juandavidramos.daos;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import juandavidramos.entidades.Barrio;
import juandavidramos.entidades.Buse;
import juandavidramos.entidades.Colegio;
import juandavidramos.entidades.Horario;
import juandavidramos.entidades.Padrefamilia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juandavidramos.daos.exceptions.IllegalOrphanException;
import juandavidramos.daos.exceptions.NonexistentEntityException;
import juandavidramos.entidades.Estudiante;
import juandavidramos.entidades.Reporte;

/**
 *
 * @author juand
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) {
        if (estudiante.getPadrefamiliaList() == null) {
            estudiante.setPadrefamiliaList(new ArrayList<Padrefamilia>());
        }
        if (estudiante.getReporteList() == null) {
            estudiante.setReporteList(new ArrayList<Reporte>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barriosidBarrios = estudiante.getBarriosidBarrios();
            if (barriosidBarrios != null) {
                barriosidBarrios = em.getReference(barriosidBarrios.getClass(), barriosidBarrios.getIdBarrios());
                estudiante.setBarriosidBarrios(barriosidBarrios);
            }
            Buse busesidBuses = estudiante.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses = em.getReference(busesidBuses.getClass(), busesidBuses.getIdBuses());
                estudiante.setBusesidBuses(busesidBuses);
            }
            Colegio colegiosidColegios = estudiante.getColegiosidColegios();
            if (colegiosidColegios != null) {
                colegiosidColegios = em.getReference(colegiosidColegios.getClass(), colegiosidColegios.getIdColegios());
                estudiante.setColegiosidColegios(colegiosidColegios);
            }
            Horario horariosidHorarios = estudiante.getHorariosidHorarios();
            if (horariosidHorarios != null) {
                horariosidHorarios = em.getReference(horariosidHorarios.getClass(), horariosidHorarios.getIdHorarios());
                estudiante.setHorariosidHorarios(horariosidHorarios);
            }
            List<Padrefamilia> attachedPadrefamiliaList = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListPadrefamiliaToAttach : estudiante.getPadrefamiliaList()) {
                padrefamiliaListPadrefamiliaToAttach = em.getReference(padrefamiliaListPadrefamiliaToAttach.getClass(), padrefamiliaListPadrefamiliaToAttach.getIdPadreFamilia());
                attachedPadrefamiliaList.add(padrefamiliaListPadrefamiliaToAttach);
            }
            estudiante.setPadrefamiliaList(attachedPadrefamiliaList);
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            for (Reporte reporteListReporteToAttach : estudiante.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdReportes());
                attachedReporteList.add(reporteListReporteToAttach);
            }
            estudiante.setReporteList(attachedReporteList);
            em.persist(estudiante);
            if (barriosidBarrios != null) {
                barriosidBarrios.getEstudianteList().add(estudiante);
                barriosidBarrios = em.merge(barriosidBarrios);
            }
            if (busesidBuses != null) {
                busesidBuses.getEstudianteList().add(estudiante);
                busesidBuses = em.merge(busesidBuses);
            }
            if (colegiosidColegios != null) {
                colegiosidColegios.getEstudianteList().add(estudiante);
                colegiosidColegios = em.merge(colegiosidColegios);
            }
            if (horariosidHorarios != null) {
                horariosidHorarios.getEstudianteList().add(estudiante);
                horariosidHorarios = em.merge(horariosidHorarios);
            }
            for (Padrefamilia padrefamiliaListPadrefamilia : estudiante.getPadrefamiliaList()) {
                Estudiante oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia = padrefamiliaListPadrefamilia.getEstudiantesidEstudiantes();
                padrefamiliaListPadrefamilia.setEstudiantesidEstudiantes(estudiante);
                padrefamiliaListPadrefamilia = em.merge(padrefamiliaListPadrefamilia);
                if (oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia != null) {
                    oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListPadrefamilia);
                    oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia = em.merge(oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia);
                }
            }
            for (Reporte reporteListReporte : estudiante.getReporteList()) {
                Estudiante oldEstudiantesidEstudiantesOfReporteListReporte = reporteListReporte.getEstudiantesidEstudiantes();
                reporteListReporte.setEstudiantesidEstudiantes(estudiante);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldEstudiantesidEstudiantesOfReporteListReporte != null) {
                    oldEstudiantesidEstudiantesOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldEstudiantesidEstudiantesOfReporteListReporte = em.merge(oldEstudiantesidEstudiantesOfReporteListReporte);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getIdEstudiantes());
            Barrio barriosidBarriosOld = persistentEstudiante.getBarriosidBarrios();
            Barrio barriosidBarriosNew = estudiante.getBarriosidBarrios();
            Buse busesidBusesOld = persistentEstudiante.getBusesidBuses();
            Buse busesidBusesNew = estudiante.getBusesidBuses();
            Colegio colegiosidColegiosOld = persistentEstudiante.getColegiosidColegios();
            Colegio colegiosidColegiosNew = estudiante.getColegiosidColegios();
            Horario horariosidHorariosOld = persistentEstudiante.getHorariosidHorarios();
            Horario horariosidHorariosNew = estudiante.getHorariosidHorarios();
            List<Padrefamilia> padrefamiliaListOld = persistentEstudiante.getPadrefamiliaList();
            List<Padrefamilia> padrefamiliaListNew = estudiante.getPadrefamiliaList();
            List<Reporte> reporteListOld = persistentEstudiante.getReporteList();
            List<Reporte> reporteListNew = estudiante.getReporteList();
            List<String> illegalOrphanMessages = null;
            for (Padrefamilia padrefamiliaListOldPadrefamilia : padrefamiliaListOld) {
                if (!padrefamiliaListNew.contains(padrefamiliaListOldPadrefamilia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Padrefamilia " + padrefamiliaListOldPadrefamilia + " since its estudiantesidEstudiantes field is not nullable.");
                }
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its estudiantesidEstudiantes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (barriosidBarriosNew != null) {
                barriosidBarriosNew = em.getReference(barriosidBarriosNew.getClass(), barriosidBarriosNew.getIdBarrios());
                estudiante.setBarriosidBarrios(barriosidBarriosNew);
            }
            if (busesidBusesNew != null) {
                busesidBusesNew = em.getReference(busesidBusesNew.getClass(), busesidBusesNew.getIdBuses());
                estudiante.setBusesidBuses(busesidBusesNew);
            }
            if (colegiosidColegiosNew != null) {
                colegiosidColegiosNew = em.getReference(colegiosidColegiosNew.getClass(), colegiosidColegiosNew.getIdColegios());
                estudiante.setColegiosidColegios(colegiosidColegiosNew);
            }
            if (horariosidHorariosNew != null) {
                horariosidHorariosNew = em.getReference(horariosidHorariosNew.getClass(), horariosidHorariosNew.getIdHorarios());
                estudiante.setHorariosidHorarios(horariosidHorariosNew);
            }
            List<Padrefamilia> attachedPadrefamiliaListNew = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListNewPadrefamiliaToAttach : padrefamiliaListNew) {
                padrefamiliaListNewPadrefamiliaToAttach = em.getReference(padrefamiliaListNewPadrefamiliaToAttach.getClass(), padrefamiliaListNewPadrefamiliaToAttach.getIdPadreFamilia());
                attachedPadrefamiliaListNew.add(padrefamiliaListNewPadrefamiliaToAttach);
            }
            padrefamiliaListNew = attachedPadrefamiliaListNew;
            estudiante.setPadrefamiliaList(padrefamiliaListNew);
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdReportes());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }
            reporteListNew = attachedReporteListNew;
            estudiante.setReporteList(reporteListNew);
            estudiante = em.merge(estudiante);
            if (barriosidBarriosOld != null && !barriosidBarriosOld.equals(barriosidBarriosNew)) {
                barriosidBarriosOld.getEstudianteList().remove(estudiante);
                barriosidBarriosOld = em.merge(barriosidBarriosOld);
            }
            if (barriosidBarriosNew != null && !barriosidBarriosNew.equals(barriosidBarriosOld)) {
                barriosidBarriosNew.getEstudianteList().add(estudiante);
                barriosidBarriosNew = em.merge(barriosidBarriosNew);
            }
            if (busesidBusesOld != null && !busesidBusesOld.equals(busesidBusesNew)) {
                busesidBusesOld.getEstudianteList().remove(estudiante);
                busesidBusesOld = em.merge(busesidBusesOld);
            }
            if (busesidBusesNew != null && !busesidBusesNew.equals(busesidBusesOld)) {
                busesidBusesNew.getEstudianteList().add(estudiante);
                busesidBusesNew = em.merge(busesidBusesNew);
            }
            if (colegiosidColegiosOld != null && !colegiosidColegiosOld.equals(colegiosidColegiosNew)) {
                colegiosidColegiosOld.getEstudianteList().remove(estudiante);
                colegiosidColegiosOld = em.merge(colegiosidColegiosOld);
            }
            if (colegiosidColegiosNew != null && !colegiosidColegiosNew.equals(colegiosidColegiosOld)) {
                colegiosidColegiosNew.getEstudianteList().add(estudiante);
                colegiosidColegiosNew = em.merge(colegiosidColegiosNew);
            }
            if (horariosidHorariosOld != null && !horariosidHorariosOld.equals(horariosidHorariosNew)) {
                horariosidHorariosOld.getEstudianteList().remove(estudiante);
                horariosidHorariosOld = em.merge(horariosidHorariosOld);
            }
            if (horariosidHorariosNew != null && !horariosidHorariosNew.equals(horariosidHorariosOld)) {
                horariosidHorariosNew.getEstudianteList().add(estudiante);
                horariosidHorariosNew = em.merge(horariosidHorariosNew);
            }
            for (Padrefamilia padrefamiliaListNewPadrefamilia : padrefamiliaListNew) {
                if (!padrefamiliaListOld.contains(padrefamiliaListNewPadrefamilia)) {
                    Estudiante oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia = padrefamiliaListNewPadrefamilia.getEstudiantesidEstudiantes();
                    padrefamiliaListNewPadrefamilia.setEstudiantesidEstudiantes(estudiante);
                    padrefamiliaListNewPadrefamilia = em.merge(padrefamiliaListNewPadrefamilia);
                    if (oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia != null && !oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia.equals(estudiante)) {
                        oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListNewPadrefamilia);
                        oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia = em.merge(oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia);
                    }
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Estudiante oldEstudiantesidEstudiantesOfReporteListNewReporte = reporteListNewReporte.getEstudiantesidEstudiantes();
                    reporteListNewReporte.setEstudiantesidEstudiantes(estudiante);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldEstudiantesidEstudiantesOfReporteListNewReporte != null && !oldEstudiantesidEstudiantesOfReporteListNewReporte.equals(estudiante)) {
                        oldEstudiantesidEstudiantesOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldEstudiantesidEstudiantesOfReporteListNewReporte = em.merge(oldEstudiantesidEstudiantesOfReporteListNewReporte);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getIdEstudiantes();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getIdEstudiantes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Padrefamilia> padrefamiliaListOrphanCheck = estudiante.getPadrefamiliaList();
            for (Padrefamilia padrefamiliaListOrphanCheckPadrefamilia : padrefamiliaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Padrefamilia " + padrefamiliaListOrphanCheckPadrefamilia + " in its padrefamiliaList field has a non-nullable estudiantesidEstudiantes field.");
            }
            List<Reporte> reporteListOrphanCheck = estudiante.getReporteList();
            for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable estudiantesidEstudiantes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Barrio barriosidBarrios = estudiante.getBarriosidBarrios();
            if (barriosidBarrios != null) {
                barriosidBarrios.getEstudianteList().remove(estudiante);
                barriosidBarrios = em.merge(barriosidBarrios);
            }
            Buse busesidBuses = estudiante.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses.getEstudianteList().remove(estudiante);
                busesidBuses = em.merge(busesidBuses);
            }
            Colegio colegiosidColegios = estudiante.getColegiosidColegios();
            if (colegiosidColegios != null) {
                colegiosidColegios.getEstudianteList().remove(estudiante);
                colegiosidColegios = em.merge(colegiosidColegios);
            }
            Horario horariosidHorarios = estudiante.getHorariosidHorarios();
            if (horariosidHorarios != null) {
                horariosidHorarios.getEstudianteList().remove(estudiante);
                horariosidHorarios = em.merge(horariosidHorarios);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
