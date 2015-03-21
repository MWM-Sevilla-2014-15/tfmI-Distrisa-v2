package es.us.master.beans;

import es.us.master.entities.Carrotfmi;

import es.us.master.entities.Listacompratfmi;
import es.us.master.entities.Usuariotfmi;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName = "TFMI-Model-CarrotfmiBean")
public class CarrotfmiBean implements CarrotfmiBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "TFMI")
    private EntityManager em;

    public CarrotfmiBean() {
    }

    public Carrotfmi persistCarrotfmi(Carrotfmi carrotfmi) {
        em.persist(carrotfmi);
        return carrotfmi;
    }

    public Carrotfmi mergeCarrotfmi(Carrotfmi carrotfmi) {
        return em.merge(carrotfmi);
    }

    public void removeCarrotfmi(Carrotfmi carrotfmi) {
        carrotfmi = em.find(Carrotfmi.class, carrotfmi.getCodCarro());
        em.remove(carrotfmi);
    }

    /** <code>select o from Carrotfmi o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Carrotfmi> getCarrotfmiFindAll() {
        return em.createNamedQuery("Carrotfmi.findAll", Carrotfmi.class).getResultList();
    }

    @Override
    public List<Carrotfmi> getCarrotfmiFindByOwn(Usuariotfmi usuario){
        Query query = em.createNamedQuery("Carrotfmi.findByOwn", Carrotfmi.class);
        query.setParameter("u", usuario);
        return query.getResultList();
    }
    
    @Override
    public Carrotfmi getCarrotfmiByList(Listacompratfmi lista){
        try {
            Query query = em.createNamedQuery("Carrotfmi.findByList", Carrotfmi.class);
            query.setParameter("l", lista);
            return (Carrotfmi)query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }            
    }    
}
