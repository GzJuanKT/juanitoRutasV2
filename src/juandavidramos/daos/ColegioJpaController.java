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
import juandavidramos.entidades.Colegio;
import juandavidramos.entidades.Estudiante;

/**
 *
 * @author juand
 */
public class ColegioJpaController implements Serializable {

    public ColegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Colegio colegio) {
        if (colegio.getBuseList() == null) {
            colegio.setBuseList(new ArrayList<Buse>());
        }
        if (colegio.getEstudianteList() == null) {
            colegio.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Buse> attachedBuseList = new ArrayList<Buse>();
            for (Buse buseListBuseToAttach : colegio.getBuseList()) {
                buseListBuseToAttach = em.getReference(buseListBuseToAttach.getClass(), buseListBuseToAttach.getIdBuses());
                attachedBuseList.add(buseListBuseToAttach);
            }
            colegio.setBuseList(attachedBuseList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : colegio.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            colegio.setEstudianteList(attachedEstudianteList);
            em.persist(colegio);
            for (Buse buseListBuse : colegio.getBuseList()) {
                Colegio oldColegiosidColegiosOfBuseListBuse = buseListBuse.getColegiosidColegios();
                buseListBuse.setColegiosidColegios(colegio);
                buseListBuse = em.merge(buseListBuse);
                if (oldColegiosidColegiosOfBuseListBuse != null) {
                    oldColegiosidColegiosOfBuseListBuse.getBuseList().remove(buseListBuse);
                    oldColegiosidColegiosOfBuseListBuse = em.merge(oldColegiosidColegiosOfBuseListBuse);
                }
            }
            for (Estudiante estudianteListEstudiante : colegio.getEstudianteList()) {
                Colegio oldColegiosidColegiosOfEstudianteListEstudiante = estudianteListEstudiante.getColegiosidColegios();
                estudianteListEstudiante.setColegiosidColegios(colegio);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldColegiosidColegiosOfEstudianteListEstudiante != null) {
                    oldColegiosidColegiosOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldColegiosidColegiosOfEstudianteListEstudiante = em.merge(oldColegiosidColegiosOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Colegio colegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colegio persistentColegio = em.find(Colegio.class, colegio.getIdColegios());
            List<Buse> buseListOld = persistentColegio.getBuseList();
            List<Buse> buseListNew = colegio.getBuseList();
            List<Estudiante> estudianteListOld = persistentColegio.getEstudianteList();
            List<Estudiante> estudianteListNew = colegio.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Buse buseListOldBuse : buseListOld) {
                if (!buseListNew.contains(buseListOldBuse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Buse " + buseListOldBuse + " since its colegiosidColegios field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its colegiosidColegios field is not nullable.");
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
            colegio.setBuseList(buseListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            colegio.setEstudianteList(estudianteListNew);
            colegio = em.merge(colegio);
            for (Buse buseListNewBuse : buseListNew) {
                if (!buseListOld.contains(buseListNewBuse)) {
                    Colegio oldColegiosidColegiosOfBuseListNewBuse = buseListNewBuse.getColegiosidColegios();
                    buseListNewBuse.setColegiosidColegios(colegio);
                    buseListNewBuse = em.merge(buseListNewBuse);
                    if (oldColegiosidColegiosOfBuseListNewBuse != null && !oldColegiosidColegiosOfBuseListNewBuse.equals(colegio)) {
                        oldColegiosidColegiosOfBuseListNewBuse.getBuseList().remove(buseListNewBuse);
                        oldColegiosidColegiosOfBuseListNewBuse = em.merge(oldColegiosidColegiosOfBuseListNewBuse);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Colegio oldColegiosidColegiosOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getColegiosidColegios();
                    estudianteListNewEstudiante.setColegiosidColegios(colegio);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldColegiosidColegiosOfEstudianteListNewEstudiante != null && !oldColegiosidColegiosOfEstudianteListNewEstudiante.equals(colegio)) {
                        oldColegiosidColegiosOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldColegiosidColegiosOfEstudianteListNewEstudiante = em.merge(oldColegiosidColegiosOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colegio.getIdColegios();
                if (findColegio(id) == null) {
                    throw new NonexistentEntityException("The colegio with id " + id + " no longer exists.");
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
            Colegio colegio;
            try {
                colegio = em.getReference(Colegio.class, id);
                colegio.getIdColegios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Buse> buseListOrphanCheck = colegio.getBuseList();
            for (Buse buseListOrphanCheckBuse : buseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colegio (" + colegio + ") cannot be destroyed since the Buse " + buseListOrphanCheckBuse + " in its buseList field has a non-nullable colegiosidColegios field.");
            }
            List<Estudiante> estudianteListOrphanCheck = colegio.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colegio (" + colegio + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable colegiosidColegios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(colegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Colegio> findColegioEntities() {
        return findColegioEntities(true, -1, -1);
    }

    public List<Colegio> findColegioEntities(int maxResults, int firstResult) {
        return findColegioEntities(false, maxResults, firstResult);
    }

    private List<Colegio> findColegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colegio.class));
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

    public Colegio findColegio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Colegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getColegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Colegio> rt = cq.from(Colegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
