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
import juandavidramos.entidades.Barrio;
import juandavidramos.entidades.Estudiante;

/**
 *
 * @author juand
 */
public class BarrioJpaController implements Serializable {

    public BarrioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barrio barrio) {
        if (barrio.getBuseList() == null) {
            barrio.setBuseList(new ArrayList<Buse>());
        }
        if (barrio.getEstudianteList() == null) {
            barrio.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Buse> attachedBuseList = new ArrayList<Buse>();
            for (Buse buseListBuseToAttach : barrio.getBuseList()) {
                buseListBuseToAttach = em.getReference(buseListBuseToAttach.getClass(), buseListBuseToAttach.getIdBuses());
                attachedBuseList.add(buseListBuseToAttach);
            }
            barrio.setBuseList(attachedBuseList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : barrio.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            barrio.setEstudianteList(attachedEstudianteList);
            em.persist(barrio);
            for (Buse buseListBuse : barrio.getBuseList()) {
                Barrio oldBarriosidBarriosOfBuseListBuse = buseListBuse.getBarriosidBarrios();
                buseListBuse.setBarriosidBarrios(barrio);
                buseListBuse = em.merge(buseListBuse);
                if (oldBarriosidBarriosOfBuseListBuse != null) {
                    oldBarriosidBarriosOfBuseListBuse.getBuseList().remove(buseListBuse);
                    oldBarriosidBarriosOfBuseListBuse = em.merge(oldBarriosidBarriosOfBuseListBuse);
                }
            }
            for (Estudiante estudianteListEstudiante : barrio.getEstudianteList()) {
                Barrio oldBarriosidBarriosOfEstudianteListEstudiante = estudianteListEstudiante.getBarriosidBarrios();
                estudianteListEstudiante.setBarriosidBarrios(barrio);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldBarriosidBarriosOfEstudianteListEstudiante != null) {
                    oldBarriosidBarriosOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldBarriosidBarriosOfEstudianteListEstudiante = em.merge(oldBarriosidBarriosOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barrio barrio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio persistentBarrio = em.find(Barrio.class, barrio.getIdBarrios());
            List<Buse> buseListOld = persistentBarrio.getBuseList();
            List<Buse> buseListNew = barrio.getBuseList();
            List<Estudiante> estudianteListOld = persistentBarrio.getEstudianteList();
            List<Estudiante> estudianteListNew = barrio.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Buse buseListOldBuse : buseListOld) {
                if (!buseListNew.contains(buseListOldBuse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Buse " + buseListOldBuse + " since its barriosidBarrios field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its barriosidBarrios field is not nullable.");
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
            barrio.setBuseList(buseListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            barrio.setEstudianteList(estudianteListNew);
            barrio = em.merge(barrio);
            for (Buse buseListNewBuse : buseListNew) {
                if (!buseListOld.contains(buseListNewBuse)) {
                    Barrio oldBarriosidBarriosOfBuseListNewBuse = buseListNewBuse.getBarriosidBarrios();
                    buseListNewBuse.setBarriosidBarrios(barrio);
                    buseListNewBuse = em.merge(buseListNewBuse);
                    if (oldBarriosidBarriosOfBuseListNewBuse != null && !oldBarriosidBarriosOfBuseListNewBuse.equals(barrio)) {
                        oldBarriosidBarriosOfBuseListNewBuse.getBuseList().remove(buseListNewBuse);
                        oldBarriosidBarriosOfBuseListNewBuse = em.merge(oldBarriosidBarriosOfBuseListNewBuse);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Barrio oldBarriosidBarriosOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getBarriosidBarrios();
                    estudianteListNewEstudiante.setBarriosidBarrios(barrio);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldBarriosidBarriosOfEstudianteListNewEstudiante != null && !oldBarriosidBarriosOfEstudianteListNewEstudiante.equals(barrio)) {
                        oldBarriosidBarriosOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldBarriosidBarriosOfEstudianteListNewEstudiante = em.merge(oldBarriosidBarriosOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = barrio.getIdBarrios();
                if (findBarrio(id) == null) {
                    throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.");
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
            Barrio barrio;
            try {
                barrio = em.getReference(Barrio.class, id);
                barrio.getIdBarrios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Buse> buseListOrphanCheck = barrio.getBuseList();
            for (Buse buseListOrphanCheckBuse : buseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Barrio (" + barrio + ") cannot be destroyed since the Buse " + buseListOrphanCheckBuse + " in its buseList field has a non-nullable barriosidBarrios field.");
            }
            List<Estudiante> estudianteListOrphanCheck = barrio.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Barrio (" + barrio + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable barriosidBarrios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(barrio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barrio> findBarrioEntities() {
        return findBarrioEntities(true, -1, -1);
    }

    public List<Barrio> findBarrioEntities(int maxResults, int firstResult) {
        return findBarrioEntities(false, maxResults, firstResult);
    }

    private List<Barrio> findBarrioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barrio.class));
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

    public Barrio findBarrio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barrio.class, id);
        } finally {
            em.close();
        }
    }

    public int getBarrioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barrio> rt = cq.from(Barrio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
