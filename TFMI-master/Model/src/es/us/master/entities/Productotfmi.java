package es.us.master.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * To create ID generator sequence "PRODUCTOTFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "PRODUCTOTFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Productotfmi.findAll", query = "select o from Productotfmi o"), 
                @NamedQuery(name = "Productotfmi.findByCategory", query = "select o from Productotfmi o where o.categoria=:c"),
                @NamedQuery(name = "Productotfmi.findByName", query = "select o from Productotfmi o where o.nombre=:n"), 
                @NamedQuery(name = "Productotfmi.findByActivo", query = "select o from Productotfmi o where o.activo=:a")
            })
@SequenceGenerator(name = "Productotfmi_Id_Seq_Gen", sequenceName = "PRODUCTOTFMI_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
public class Productotfmi implements Serializable {
    private static final long serialVersionUID = 728280768720261771L;
    @Column(nullable = false)
    private int activo = 1;
    @Column(nullable = false, length = 20)
    private String categoria;
    @Version
    private Integer version;
    @Id
    @Column(name = "COD_PRODUCTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Productotfmi_Id_Seq_Gen")
    private int codProducto;
    @Column(nullable = false, length = 20)
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date fechaact = new Date();
    @Temporal(TemporalType.DATE)
    private Date fechaalt = new Date();
    @Column(nullable = false, length = 20)
    private String nombre;
    @Column(nullable = false)
    private double precio;
    @OneToMany(mappedBy = "productotfmi", cascade = CascadeType.ALL)
    private List<Listacompratfmi> listacompratfmiList;
    @Transient
    private boolean editable = false;
    
    public Productotfmi() {
    }

    public Productotfmi(int activo, String categoria, int codProducto, String descripcion, Date fechaact,
                        Date fechaalt, String nombre, int precio) {
        this.activo = activo;
        this.categoria = categoria;
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.fechaact = fechaact;
        this.fechaalt = fechaalt;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public List<Listacompratfmi> getListacompratfmiList() {
        return listacompratfmiList;
    }

    public void setListacompratfmiList(List<Listacompratfmi> listacompratfmiList) {
        this.listacompratfmiList = listacompratfmiList;
    }

    public Listacompratfmi addListacompratfmi(Listacompratfmi listacompratfmi) {
        getListacompratfmiList().add(listacompratfmi);
        listacompratfmi.setProductotfmi(this);
        return listacompratfmi;
    }

    public Listacompratfmi removeListacompratfmi(Listacompratfmi listacompratfmi) {
        getListacompratfmiList().remove(listacompratfmi);
        listacompratfmi.setProductotfmi(null);
        return listacompratfmi;
    }
    
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }
    
    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public long getCodProducto() {
        return codProducto;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}