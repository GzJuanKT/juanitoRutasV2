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
import juandavidramos.entidades.Colegio;
import juandavidramos.entidades.Horario;
import juandavidramos.entidades.Conductor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juandavidramos.daos.exceptions.IllegalOrphanException;
import juandavidramos.daos.exceptions.NonexistentEntityException;
import juandavidramos.daos.exceptions.PreexistingEntityException;
import juandavidramos.entidades.Buse;
import juandavidramos.entidades.Tutor;
import juandavidramos.entidades.Reporte;
import juandavidramos.entidades.Estudiante;

/**
 *
 * @author juand
 */
public class BuseJpaController implements Serializable {

    public BuseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Buse buse) throws PreexistingEntityException, Exception {
        if (buse.getConductorList() == null) {
            buse.setConductorList(new ArrayList<Conductor>());
        }
        if (buse.getTutorList() == null) {
            buse.setTutorList(new ArrayList<Tutor>());
        }
        if (buse.getReporteList() == null) {
            buse.setReporteList(new ArrayList<Reporte>());
        }
        if (buse.getEstudianteList() == null) {
            buse.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barriosidBarrios = buse.getBarriosidBarrios();
            if (barriosidBarrios != null) {
                barriosidBarrios = em.getReference(barriosidBarrios.getClass(), barriosidBarrios.getIdBarrios());
                buse.setBarriosidBarrios(barriosidBarrios);
            }
            Colegio colegiosidColegios = buse.getColegiosidColegios();
            if (colegiosidColegios != null) {
                colegiosidColegios = em.getReference(colegiosidColegios.getClass(), colegiosidColegios.getIdColegios());
                buse.setColegiosidColegios(colegiosidColegios);
            }
            Horario horariosidHorarios = buse.getHorariosidHorarios();
            if (horariosidHorarios != null) {
                horariosidHorarios = em.getReference(horariosidHorarios.getClass(), horariosidHorarios.getIdHorarios());
                buse.setHorariosidHorarios(horariosidHorarios);
            }
            List<Conductor> attachedConductorList = new ArrayList<Conductor>();
            for (Conductor conductorListConductorToAttach : buse.getConductorList()) {
                conductorListConductorToAttach = em.getReference(conductorListConductorToAttach.getClass(), conductorListConductorToAttach.getIdConductores());
                attachedConductorList.add(conductorListConductorToAttach);
            }
            buse.setConductorList(attachedConductorList);
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : buse.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutores());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            buse.setTutorList(attachedTutorList);
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            for (Reporte reporteListReporteToAttach : buse.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdReportes());
                attachedReporteList.add(reporteListReporteToAttach);
            }
            buse.setReporteList(attachedReporteList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : buse.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            buse.setEstudianteList(attachedEstudianteList);
            em.persist(buse);
            if (barriosidBarrios != null) {
                barriosidBarrios.getBuseList().add(buse);
                barriosidBarrios = em.merge(barriosidBarrios);
            }
            if (colegiosidColegios != null) {
                colegiosidColegios.getBuseList().add(buse);
                colegiosidColegios = em.merge(colegiosidColegios);
            }
            if (horariosidHorarios != null) {
                horariosidHorarios.getBuseList().add(buse);
                horariosidHorarios = em.merge(horariosidHorarios);
            }
            for (Conductor conductorListConductor : buse.getConductorList()) {
                Buse oldBusesidBusesOfConductorListConductor = conductorListConductor.getBusesidBuses();
                conductorListConductor.setBusesidBuses(buse);
                conductorListConductor = em.merge(conductorListConductor);
                if (oldBusesidBusesOfConductorListConductor != null) {
                    oldBusesidBusesOfConductorListConductor.getConductorList().remove(conductorListConductor);
                    oldBusesidBusesOfConductorListConductor = em.merge(oldBusesidBusesOfConductorListConductor);
                }
            }
            for (Tutor tutorListTutor : buse.getTutorList()) {
                Buse oldBusesidBusesOfTutorListTutor = tutorListTutor.getBusesidBuses();
                tutorListTutor.setBusesidBuses(buse);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldBusesidBusesOfTutorListTutor != null) {
                    oldBusesidBusesOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldBusesidBusesOfTutorListTutor = em.merge(oldBusesidBusesOfTutorListTutor);
                }
            }
            for (Reporte reporteListReporte : buse.getReporteList()) {
                Buse oldBusesidBusesOfReporteListReporte = reporteListReporte.getBusesidBuses();
                reporteListReporte.setBusesidBuses(buse);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldBusesidBusesOfReporteListReporte != null) {
                    oldBusesidBusesOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldBusesidBusesOfReporteListReporte = em.merge(oldBusesidBusesOfReporteListReporte);
                }
            }
            for (Estudiante estudianteListEstudiante : buse.getEstudianteList()) {
                Buse oldBusesidBusesOfEstudianteListEstudiante = estudianteListEstudiante.getBusesidBuses();
                estudianteListEstudiante.setBusesidBuses(buse);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldBusesidBusesOfEstudianteListEstudiante != null) {
                    oldBusesidBusesOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldBusesidBusesOfEstudianteListEstudiante = em.merge(oldBusesidBusesOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBuse(buse.getIdBuses()) != null) {
                throw new PreexistingEntityException("Buse " + buse + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Buse buse) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buse persistentBuse = em.find(Buse.class, buse.getIdBuses());
            Barrio barriosidBarriosOld = persistentBuse.getBarriosidBarrios();
            Barrio barriosidBarriosNew = buse.getBarriosidBarrios();
            Colegio colegiosidColegiosOld = persistentBuse.getColegiosidColegios();
            Colegio colegiosidColegiosNew = buse.getColegiosidColegios();
            Horario horariosidHorariosOld = persistentBuse.getHorariosidHorarios();
            Horario horariosidHorariosNew = buse.getHorariosidHorarios();
            List<Conductor> conductorListOld = persistentBuse.getConductorList();
            List<Conductor> conductorListNew = buse.getConductorList();
            List<Tutor> tutorListOld = persistentBuse.getTutorList();
            List<Tutor> tutorListNew = buse.getTutorList();
            List<Reporte> reporteListOld = persistentBuse.getReporteList();
            List<Reporte> reporteListNew = buse.getReporteList();
            List<Estudiante> estudianteListOld = persistentBuse.getEstudianteList();
            List<Estudiante> estudianteListNew = buse.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Conductor conductorListOldConductor : conductorListOld) {
                if (!conductorListNew.contains(conductorListOldConductor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conductor " + conductorListOldConductor + " since its busesidBuses field is not nullable.");
                }
            }
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its busesidBuses field is not nullable.");
                }
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its busesidBuses field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its busesidBuses field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (barriosidBarriosNew != null) {
                barriosidBarriosNew = em.getReference(barriosidBarriosNew.getClass(), barriosidBarriosNew.getIdBarrios());
                buse.setBarriosidBarrios(barriosidBarriosNew);
            }
            if (colegiosidColegiosNew != null) {
                colegiosidColegiosNew = em.getReference(colegiosidColegiosNew.getClass(), colegiosidColegiosNew.getIdColegios());
                buse.setColegiosidColegios(colegiosidColegiosNew);
            }
            if (horariosidHorariosNew != null) {
                horariosidHorariosNew = em.getReference(horariosidHorariosNew.getClass(), horariosidHorariosNew.getIdHorarios());
                buse.setHorariosidHorarios(horariosidHorariosNew);
            }
            List<Conductor> attachedConductorListNew = new ArrayList<Conductor>();
            for (Conductor conductorListNewConductorToAttach : conductorListNew) {
                conductorListNewConductorToAttach = em.getReference(conductorListNewConductorToAttach.getClass(), conductorListNewConductorToAttach.getIdConductores());
                attachedConductorListNew.add(conductorListNewConductorToAttach);
            }
            conductorListNew = attachedConductorListNew;
            buse.setConductorList(conductorListNew);
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutores());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            buse.setTutorList(tutorListNew);
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdReportes());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }
            reporteListNew = attachedReporteListNew;
            buse.setReporteList(reporteListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            buse.setEstudianteList(estudianteListNew);
            buse = em.merge(buse);
            if (barriosidBarriosOld != null && !barriosidBarriosOld.equals(barriosidBarriosNew)) {
                barriosidBarriosOld.getBuseList().remove(buse);
                barriosidBarriosOld = em.merge(barriosidBarriosOld);
            }
            if (barriosidBarriosNew != null && !barriosidBarriosNew.equals(barriosidBarriosOld)) {
                barriosidBarriosNew.getBuseList().add(buse);
                barriosidBarriosNew = em.merge(barriosidBarriosNew);
            }
            if (colegiosidColegiosOld != null && !colegiosidColegiosOld.equals(colegiosidColegiosNew)) {
                colegiosidColegiosOld.getBuseList().remove(buse);
                colegiosidColegiosOld = em.merge(colegiosidColegiosOld);
            }
            if (colegiosidColegiosNew != null && !colegiosidColegiosNew.equals(colegiosidColegiosOld)) {
                colegiosidColegiosNew.getBuseList().add(buse);
                colegiosidColegiosNew = em.merge(colegiosidColegiosNew);
            }
            if (horariosidHorariosOld != null && !horariosidHorariosOld.equals(horariosidHorariosNew)) {
                horariosidHorariosOld.getBuseList().remove(buse);
                horariosidHorariosOld = em.merge(horariosidHorariosOld);
            }
            if (horariosidHorariosNew != null && !horariosidHorariosNew.equals(horariosidHorariosOld)) {
                horariosidHorariosNew.getBuseList().add(buse);
                horariosidHorariosNew = em.merge(horariosidHorariosNew);
            }
            for (Conductor conductorListNewConductor : conductorListNew) {
                if (!conductorListOld.contains(conductorListNewConductor)) {
                    Buse oldBusesidBusesOfConductorListNewConductor = conductorListNewConductor.getBusesidBuses();
                    conductorListNewConductor.setBusesidBuses(buse);
                    conductorListNewConductor = em.merge(conductorListNewConductor);
                    if (oldBusesidBusesOfConductorListNewConductor != null && !oldBusesidBusesOfConductorListNewConductor.equals(buse)) {
                        oldBusesidBusesOfConductorListNewConductor.getConductorList().remove(conductorListNewConductor);
                        oldBusesidBusesOfConductorListNewConductor = em.merge(oldBusesidBusesOfConductorListNewConductor);
                    }
                }
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Buse oldBusesidBusesOfTutorListNewTutor = tutorListNewTutor.getBusesidBuses();
                    tutorListNewTutor.setBusesidBuses(buse);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldBusesidBusesOfTutorListNewTutor != null && !oldBusesidBusesOfTutorListNewTutor.equals(buse)) {
                        oldBusesidBusesOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldBusesidBusesOfTutorListNewTutor = em.merge(oldBusesidBusesOfTutorListNewTutor);
                    }
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Buse oldBusesidBusesOfReporteListNewReporte = reporteListNewReporte.getBusesidBuses();
                    reporteListNewReporte.setBusesidBuses(buse);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldBusesidBusesOfReporteListNewReporte != null && !oldBusesidBusesOfReporteListNewReporte.equals(buse)) {
                        oldBusesidBusesOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldBusesidBusesOfReporteListNewReporte = em.merge(oldBusesidBusesOfReporteListNewReporte);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Buse oldBusesidBusesOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getBusesidBuses();
                    estudianteListNewEstudiante.setBusesidBuses(buse);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldBusesidBusesOfEstudianteListNewEstudiante != null && !oldBusesidBusesOfEstudianteListNewEstudiante.equals(buse)) {
                        oldBusesidBusesOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldBusesidBusesOfEstudianteListNewEstudiante = em.merge(oldBusesidBusesOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = buse.getIdBuses();
                if (findBuse(id) == null) {
                    throw new NonexistentEntityException("The buse with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buse buse;
            try {
                buse = em.getReference(Buse.class, id);
                buse.getIdBuses();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buse with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Conductor> conductorListOrphanCheck = buse.getConductorList();
            for (Conductor conductorListOrphanCheckConductor : conductorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Buse (" + buse + ") cannot be destroyed since the Conductor " + conductorListOrphanCheckConductor + " in its conductorList field has a non-nullable busesidBuses field.");
            }
            List<Tutor> tutorListOrphanCheck = buse.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Buse (" + buse + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable busesidBuses field.");
            }
            List<Reporte> reporteListOrphanCheck = buse.getReporteList();
            for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Buse (" + buse + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable busesidBuses field.");
            }
            List<Estudiante> estudianteListOrphanCheck = buse.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Buse (" + buse + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable busesidBuses field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Barrio barriosidBarrios = buse.getBarriosidBarrios();
            if (barriosidBarrios != null) {
                barriosidBarrios.getBuseList().remove(buse);
                barriosidBarrios = em.merge(barriosidBarrios);
            }
            Colegio colegiosidColegios = buse.getColegiosidColegios();
            if (colegiosidColegios != null) {
                colegiosidColegios.getBuseList().remove(buse);
                colegiosidColegios = em.merge(colegiosidColegios);
            }
            Horario horariosidHorarios = buse.getHorariosidHorarios();
            if (horariosidHorarios != null) {
                horariosidHorarios.getBuseList().remove(buse);
                horariosidHorarios = em.merge(horariosidHorarios);
            }
            em.remove(buse);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Buse> findBuseEntities() {
        return findBuseEntities(true, -1, -1);
    }

    public List<Buse> findBuseEntities(int maxResults, int firstResult) {
        return findBuseEntities(false, maxResults, firstResult);
    }

    private List<Buse> findBuseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Buse.class));
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

    public Buse findBuse(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Buse.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Buse> rt = cq.from(Buse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
