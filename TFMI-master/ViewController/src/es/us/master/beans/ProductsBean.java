package es.us.master.beans;

import es.us.master.entities.Productotfmi;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ProductsBean extends GeneralBean{
    
        @EJB
        private ProductotfmiBeanLocal productoBean;
        private Productotfmi producto;
        public ProductsBean() {
            super();
            producto= new Productotfmi();
            context.getExternalContext().getSessionMap().put( "pageBack", "index.jsf");
        }
        public Productotfmi getProducto() {
            return producto;
        }
                
        public List<Productotfmi> getProductos() {
            return productoBean.getProductotfmiFindAll();
        }
        
        public String register() {
            String res;
            Productotfmi p = productoBean.persistProductotfmi(producto);
            if ( p == null ) {
                context.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No se ha podido crear la producto."));
                res = "ERROR";
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "La producto se ha creado con exito."));
                res="OK";
            }
            return res;
        }

}
