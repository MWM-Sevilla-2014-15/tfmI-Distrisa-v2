package es.us.master.beans;

import es.us.master.entities.Listacompratfmi;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "TFMI-Model-ListacompratfmiBean")
public class ListacompratfmiBean implements ListacompratfmiBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "TFMI")
    private EntityManager em;

    public ListacompratfmiBean() {
    }

    public Listacompratfmi persistListacompratfmi(Listacompratfmi listacompratfmi) {
        em.persist(listacompratfmi);
        return listacompratfmi;
    }

    public Listacompratfmi mergeListacompratfmi(Listacompratfmi listacompratfmi) {
        return em.merge(listacompratfmi);
    }

    public void removeListacompratfmi(Listacompratfmi listacompratfmi) {
        listacompratfmi = em.find(Listacompratfmi.class, listacompratfmi.getCodListacompra());
        em.remove(listacompratfmi);
    }

    /** <code>select o from Listacompratfmi o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Listacompratfmi> getListacompratfmiFindAll() {
        return em.createNamedQuery("Listacompratfmi.findAll", Listacompratfmi.class).getResultList();
    }
}
