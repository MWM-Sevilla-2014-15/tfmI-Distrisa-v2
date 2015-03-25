package es.us.master.beans;

import es.us.master.entities.Productotfmi;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName = "TFMI-Model-ProductotfmiBean")
public class ProductotfmiBean implements ProductotfmiBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "TFMI")
    private EntityManager em;

    public ProductotfmiBean() {
    }

    public Productotfmi persistProductotfmi(Productotfmi productotfmi) {
        em.persist(productotfmi);
        return productotfmi;
    }

    public Productotfmi mergeProductotfmi(Productotfmi productotfmi) {
        Productotfmi producto = em.find(Productotfmi.class, productotfmi.getCodProducto());
        return em.merge(productotfmi);
    }

    public void removeProductotfmi(Productotfmi productotfmi) {
        productotfmi = em.find(Productotfmi.class, productotfmi.getCodProducto());
        em.remove(productotfmi);
    }

    /** <code>select o from Productotfmi o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Productotfmi> getProductotfmiFindAll() {
        return em.createNamedQuery("Productotfmi.findAll", Productotfmi.class).getResultList();
    }

    @Override
    public List<Productotfmi> getProductotfmiFindByCategory(String categoria){
        Query query = em.createNamedQuery("Productotfmi.findByCategory", Productotfmi.class);
        query.setParameter("c", categoria);
        return query.getResultList();
    }
         
    @Override
    public List<Productotfmi> getProductotfmiFindByName(String nombre){
        Query query = em.createNamedQuery("Productotfmi.findByName", Productotfmi.class);
        query.setParameter("n", nombre);
        return query.getResultList();
    }
    
    @Override
    public List<Productotfmi> getProductotfmiFindByActivo(int activo){
        /*activo = 1;*/
        Query query = em.createNamedQuery("Productotfmi.findByActivo", Productotfmi.class);
        query.setParameter("a", activo);
        return query.getResultList();
    }
}
