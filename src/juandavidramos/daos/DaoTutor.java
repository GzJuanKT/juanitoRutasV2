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
import juandavidramos.entidades.Bus;
import juandavidramos.entidades.Tutor;

/**
 *
 * @author juand
 */
public class DaoTutor implements Serializable {

    public DaoTutor(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Tutor tutor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus busesidBuses = tutor.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses = em.getReference(busesidBuses.getClass(), busesidBuses.getIdBuses());
                tutor.setBusesidBuses(busesidBuses);
            }
            em.persist(tutor);
            if (busesidBuses != null) {
                busesidBuses.getTutorList().add(tutor);
                busesidBuses = em.merge(busesidBuses);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Tutor tutor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutores());
            Bus busesidBusesOld = persistentTutor.getBusesidBuses();
            Bus busesidBusesNew = tutor.getBusesidBuses();
            if (busesidBusesNew != null) {
                busesidBusesNew = em.getReference(busesidBusesNew.getClass(), busesidBusesNew.getIdBuses());
                tutor.setBusesidBuses(busesidBusesNew);
            }
            tutor = em.merge(tutor);
            if (busesidBusesOld != null && !busesidBusesOld.equals(busesidBusesNew)) {
                busesidBusesOld.getTutorList().remove(tutor);
                busesidBusesOld = em.merge(busesidBusesOld);
            }
            if (busesidBusesNew != null && !busesidBusesNew.equals(busesidBusesOld)) {
                busesidBusesNew.getTutorList().add(tutor);
                busesidBusesNew = em.merge(busesidBusesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutor.getIdTutores();
                if (buscarTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getIdTutores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            Bus busesidBuses = tutor.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses.getTutorList().remove(tutor);
                busesidBuses = em.merge(busesidBuses);
            }
            em.remove(tutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutor> listarTodosLosTutores() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor buscarTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalTutores() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
