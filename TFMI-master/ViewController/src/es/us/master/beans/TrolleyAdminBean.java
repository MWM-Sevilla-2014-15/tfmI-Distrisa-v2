package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Usuariotfmi;

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
public class TrolleyAdminBean extends GeneralBean{
    
    @EJB
    private CarrotfmiBeanLocal carroBean;
    private List<Carrotfmi> carros;

    public TrolleyAdminBean() {
        super();
    }
    
    @PostConstruct
    public void initIt() {        
        setCarros(carroBean.getCarrotfmiFindAll());        
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
