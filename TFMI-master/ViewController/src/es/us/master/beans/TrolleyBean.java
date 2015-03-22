package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrolleyBean extends GeneralBean{
    
    @EJB
    private CarrotfmiBeanLocal carroBean;
    private Carrotfmi carro;
    public TrolleyBean() {
        super();
        carro= new Carrotfmi();
        context.getExternalContext().getSessionMap().put( "pageBack", "index.jsf");
    }
    public Carrotfmi getCarro() {
        return carro;
    }
            
    public List<Carrotfmi> getCarros() {
        return carroBean.getCarrotfmiFindAll();
    }
}
