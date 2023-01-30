/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import juandavidramos.entidades.Buse;
import juandavidramos.entidades.Conductor;

/**
 *
 * @author juand
 */
public class ConductorJpaController implements Serializable {

    public ConductorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conductor conductor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buse busesidBuses = conductor.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses = em.getReference(busesidBuses.getClass(), busesidBuses.getIdBuses());
                conductor.setBusesidBuses(busesidBuses);
            }
            em.persist(conductor);
            if (busesidBuses != null) {
                busesidBuses.getConductorList().add(conductor);
                busesidBuses = em.merge(busesidBuses);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conductor conductor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor persistentConductor = em.find(Conductor.class, conductor.getIdConductores());
            Buse busesidBusesOld = persistentConductor.getBusesidBuses();
            Buse busesidBusesNew = conductor.getBusesidBuses();
            if (busesidBusesNew != null) {
                busesidBusesNew = em.getReference(busesidBusesNew.getClass(), busesidBusesNew.getIdBuses());
                conductor.setBusesidBuses(busesidBusesNew);
            }
            conductor = em.merge(conductor);
            if (busesidBusesOld != null && !busesidBusesOld.equals(busesidBusesNew)) {
                busesidBusesOld.getConductorList().remove(conductor);
                busesidBusesOld = em.merge(busesidBusesOld);
            }
            if (busesidBusesNew != null && !busesidBusesNew.equals(busesidBusesOld)) {
                busesidBusesNew.getConductorList().add(conductor);
                busesidBusesNew = em.merge(busesidBusesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conductor.getIdConductores();
                if (findConductor(id) == null) {
                    throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor conductor;
            try {
                conductor = em.getReference(Conductor.class, id);
                conductor.getIdConductores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.", enfe);
            }
            Buse busesidBuses = conductor.getBusesidBuses();
            if (busesidBuses != null) {
                busesidBuses.getConductorList().remove(conductor);
                busesidBuses = em.merge(busesidBuses);
            }
            em.remove(conductor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conductor> findConductorEntities() {
        return findConductorEntities(true, -1, -1);
    }

    public List<Conductor> findConductorEntities(int maxResults, int firstResult) {
        return findConductorEntities(false, maxResults, firstResult);
    }

    private List<Conductor> findConductorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conductor.class));
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

    public Conductor findConductor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conductor.class, id);
        } finally {
            em.close();
        }
    }

    public int getConductorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conductor> rt = cq.from(Conductor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
