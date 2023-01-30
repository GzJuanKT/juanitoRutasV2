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
import juandavidramos.entidades.Buse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juandavidramos.daos.exceptions.IllegalOrphanException;
import juandavidramos.daos.exceptions.NonexistentEntityException;
import juandavidramos.entidades.Estudiante;
import juandavidramos.entidades.Horario;

/**
 *
 * @author juand
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) {
        if (horario.getBuseList() == null) {
            horario.setBuseList(new ArrayList<Buse>());
        }
        if (horario.getEstudianteList() == null) {
            horario.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Buse> attachedBuseList = new ArrayList<Buse>();
            for (Buse buseListBuseToAttach : horario.getBuseList()) {
                buseListBuseToAttach = em.getReference(buseListBuseToAttach.getClass(), buseListBuseToAttach.getIdBuses());
                attachedBuseList.add(buseListBuseToAttach);
            }
            horario.setBuseList(attachedBuseList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : horario.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            horario.setEstudianteList(attachedEstudianteList);
            em.persist(horario);
            for (Buse buseListBuse : horario.getBuseList()) {
                Horario oldHorariosidHorariosOfBuseListBuse = buseListBuse.getHorariosidHorarios();
                buseListBuse.setHorariosidHorarios(horario);
                buseListBuse = em.merge(buseListBuse);
                if (oldHorariosidHorariosOfBuseListBuse != null) {
                    oldHorariosidHorariosOfBuseListBuse.getBuseList().remove(buseListBuse);
                    oldHorariosidHorariosOfBuseListBuse = em.merge(oldHorariosidHorariosOfBuseListBuse);
                }
            }
            for (Estudiante estudianteListEstudiante : horario.getEstudianteList()) {
                Horario oldHorariosidHorariosOfEstudianteListEstudiante = estudianteListEstudiante.getHorariosidHorarios();
                estudianteListEstudiante.setHorariosidHorarios(horario);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldHorariosidHorariosOfEstudianteListEstudiante != null) {
                    oldHorariosidHorariosOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldHorariosidHorariosOfEstudianteListEstudiante = em.merge(oldHorariosidHorariosOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getIdHorarios());
            List<Buse> buseListOld = persistentHorario.getBuseList();
            List<Buse> buseListNew = horario.getBuseList();
            List<Estudiante> estudianteListOld = persistentHorario.getEstudianteList();
            List<Estudiante> estudianteListNew = horario.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Buse buseListOldBuse : buseListOld) {
                if (!buseListNew.contains(buseListOldBuse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Buse " + buseListOldBuse + " since its horariosidHorarios field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its horariosidHorarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Buse> attachedBuseListNew = new ArrayList<Buse>();
            for (Buse buseListNewBuseToAttach : buseListNew) {
                buseListNewBuseToAttach = em.getReference(buseListNewBuseToAttach.getClass(), buseListNewBuseToAttach.getIdBuses());
                attachedBuseListNew.add(buseListNewBuseToAttach);
            }
            buseListNew = attachedBuseListNew;
            horario.setBuseList(buseListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            horario.setEstudianteList(estudianteListNew);
            horario = em.merge(horario);
            for (Buse buseListNewBuse : buseListNew) {
                if (!buseListOld.contains(buseListNewBuse)) {
                    Horario oldHorariosidHorariosOfBuseListNewBuse = buseListNewBuse.getHorariosidHorarios();
                    buseListNewBuse.setHorariosidHorarios(horario);
                    buseListNewBuse = em.merge(buseListNewBuse);
                    if (oldHorariosidHorariosOfBuseListNewBuse != null && !oldHorariosidHorariosOfBuseListNewBuse.equals(horario)) {
                        oldHorariosidHorariosOfBuseListNewBuse.getBuseList().remove(buseListNewBuse);
                        oldHorariosidHorariosOfBuseListNewBuse = em.merge(oldHorariosidHorariosOfBuseListNewBuse);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Horario oldHorariosidHorariosOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getHorariosidHorarios();
                    estudianteListNewEstudiante.setHorariosidHorarios(horario);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldHorariosidHorariosOfEstudianteListNewEstudiante != null && !oldHorariosidHorariosOfEstudianteListNewEstudiante.equals(horario)) {
                        oldHorariosidHorariosOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldHorariosidHorariosOfEstudianteListNewEstudiante = em.merge(oldHorariosidHorariosOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horario.getIdHorarios();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getIdHorarios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Buse> buseListOrphanCheck = horario.getBuseList();
            for (Buse buseListOrphanCheckBuse : buseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Buse " + buseListOrphanCheckBuse + " in its buseList field has a non-nullable horariosidHorarios field.");
            }
            List<Estudiante> estudianteListOrphanCheck = horario.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable horariosidHorarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
