package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Usuariotfmi;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TrolleyBean extends GeneralBean {

    @EJB
    private CarrotfmiBeanLocal carroBean;
    private Usuariotfmi usuario;
    private List<Carrotfmi> carros;

    public TrolleyBean() {
        super();
        usuario = (Usuariotfmi) context.getExternalContext().getSessionMap().get("usuario");
    }
    
    @PostConstruct
    public void initIt() {
        
        setCarros(carroBean.getCarrotfmiFindByOwn(usuario));        
        context.addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_INFO, "Felicidades,",
                                            " su envio se entregara lo mas pronto posible"));
    }

    public List<Carrotfmi> getCarros() {
        return carros;
    }

    public void setCarros(List<Carrotfmi> carros){
        for(Carrotfmi carro : carros){
            
            //Convertir Date a String
            
            Date date = carro.getFechacompra();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            String fecha = sdf.format(date);
            
            //Convertir String a Date y almacenar en carro
            
            try{
                Date fechaCarro = sdf.parse(fecha);
                carro.setFechacompra(fechaCarro);
            } catch (ParseException e){
                e.printStackTrace();
            }            
        }
        this.carros=carros;
    }       
    
}
