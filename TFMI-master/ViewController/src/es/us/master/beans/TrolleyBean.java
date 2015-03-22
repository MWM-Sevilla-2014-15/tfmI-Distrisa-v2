package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Usuariotfmi;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrolleyBean extends GeneralBean{
    
    @EJB
    private CarrotfmiBeanLocal carroBean;
    private Usuariotfmi usuario;
    private List<Carrotfmi> carros;
    public TrolleyBean() {
        super();
        context.getExternalContext().getSessionMap().put( "pageBack", "index.jsf");
        usuario = (Usuariotfmi) context.getExternalContext().getSessionMap().get("usuario");
    }
    public List<Carrotfmi> getCarro() {
        return carros;
    }
            
    public List<Carrotfmi> getCarros() {
        return carroBean.getCarrotfmiFindAll();
    }
}
