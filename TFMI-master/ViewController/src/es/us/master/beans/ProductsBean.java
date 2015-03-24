package es.us.master.beans;


import es.us.master.entities.Productotfmi;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ProductsBean extends GeneralBean {

    private String nombre, descripcion, categoria;
    private Integer precio;
    private Date fechaalt, fechaact;

    @EJB
    private ProductotfmiBeanLocal productoBean;
    private List<Productotfmi> productos;

    public ProductsBean() {
        super();
    }

    @PostConstruct
    public void initIt() {
        setProductos(productoBean.getProductotfmiFindByActivo(1));
    }

    public List<Productotfmi> getProductos() {
        return productos;
    }

    public void setProductos(List<Productotfmi> productos) {
        this.productos = productos;
    }

    public String register() {
        String res;

        Productotfmi producto = new Productotfmi();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setFechaact(new Date());
        producto.setFechaalt(new Date());

        Productotfmi p = productoBean.persistProductotfmi(producto);
        if (p == null) {
            context.addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
                                                "No se ha podido crear la producto."));
            res = "ERROR";
        } else {
            context.addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO, "OK",
                                                "La producto se ha creado con exito."));
            res = "OK";
        }
        return res;
    }

    public String editAction(Productotfmi producto) {
        productos.get(productos.indexOf(producto)).setEditable(true);
        return null;
    }

    public String save(Productotfmi producto) {
        productos.get(productos.indexOf(producto)).setEditable(false);
        productoBean.mergeProductotfmi(producto);
        return null;
    }
    
    /*public String saveItem() {
        for(Productotfmi producto : productos){
            producto.setEditable(false);
            producto.setCategoria();
            producto.setDescripcion();
            producto.setPrecio();
            productoBean.mergeProductotfmi(producto);
        }
        return null;
    }*/

    public String remove(Productotfmi producto) {
        String res;
        productos.get(productos.indexOf(producto)).setActivo(0);
        productoBean.mergeProductotfmi(producto);
        return res = "REMOVE";
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaact() {
        return fechaact;
    }

    public void setFechaact(Date fechaact) {
        this.fechaact = fechaact;
    }

    public Date getFechaalt() {
        return fechaalt;
    }

    public void setFechaalt(Date fechaalt) {
        this.fechaalt = fechaalt;
    }

}
