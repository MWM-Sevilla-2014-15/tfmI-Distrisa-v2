package es.us.master.beans;

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

@Stateless(mappedName = "TFMI-Model-UsuariotfmiBean")
public class UsuariotfmiBean implements UsuariotfmiBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "TFMI")
    private EntityManager em;

    public UsuariotfmiBean() {
    }

    public Usuariotfmi persistUsuariotfmi(Usuariotfmi usuariotfmi) {
        em.persist(usuariotfmi);
        return usuariotfmi;
    }

    public Usuariotfmi mergeUsuariotfmi(Usuariotfmi usuariotfmi) {
        return em.merge(usuariotfmi);
    }

    public void removeUsuariotfmi(Usuariotfmi usuariotfmi) {
        usuariotfmi = em.find(Usuariotfmi.class, usuariotfmi.getCodUsuario());
        em.remove(usuariotfmi);
    }

    /** <code>select o from Usuariotfmi o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuariotfmi> getUsuariotfmiFindAll() {
        return em.createNamedQuery("Usuariotfmi.findAll", Usuariotfmi.class).getResultList();
    }

    @Override
    public Usuariotfmi getUsuariotfmiUsernamePassword(String username, String password) {
        try {
            Query query = em.createNamedQuery("Usuariotfmi.findByUserAndPass", Usuariotfmi.class);
            query.setParameter("u", username);
            query.setParameter("p", password);
            return (Usuariotfmi) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
        
    @Override
    public int updateUsuariotfmi(Usuariotfmi usuario) {
        int updatedRecords=0;
        Query query = em.createQuery("update Usuariotfmi o " +
                                         "SET o.nombre=:n, o.apellidos=:a, o.email =:e, o.password =:p, o.fechaAct =:f " +
                                            "where o.username =:u");
        query.setParameter( "u", usuario.getUsername());
        query.setParameter( "p", usuario.getPassword());
        query.setParameter( "n", usuario.getNombre());
        query.setParameter( "a", usuario.getApellidos());
        query.setParameter( "e", usuario.getEmail());
        query.setParameter( "f", usuario.getFechaact());
        updatedRecords = query.executeUpdate();
        return updatedRecords;
    }
        
    @Override
    public int unregisterUsuariotfmi(Usuariotfmi usuario) {
        int updatedRecords=0;
        Query query = em.createQuery("update Usuariotfmi o SET o.estado=0 where o.username =:u");
        query.setParameter( "u", usuario.getUsername());
        updatedRecords = query.executeUpdate();
        return updatedRecords;
    }    
}
