package es.us.master.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * To create ID generator sequence "LISTACOMPRATFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "LISTACOMPRATFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Listacompratfmi.findAll", query = "select o from Listacompratfmi o"),
                @NamedQuery(name = "Listacompratfmi.findbyCarro", query = "select o from Listacompratfmi o where o.carrotfmi=:c")})
@SequenceGenerator(name = "Listacompratfmi_Id_Seq_Gen", sequenceName = "LISTACOMPRATFMI_ID_SEQ_GEN",
                   allocationSize = 50, initialValue = 50)
public class Listacompratfmi implements Serializable {
    private static final long serialVersionUID = 4802206896525593530L;
    @Column(nullable = false)
    private int cantidad;
    @Id
    @Column(name = "COD_LISTACOMPRA", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Listacompratfmi_Id_Seq_Gen")
    private int codListacompra;
    @Column(nullable = false)
    private double subtotal;
    @ManyToOne
    @JoinColumn(name = "COD_PRODUCTO")
    private Productotfmi productotfmi;
    @ManyToOne
    @JoinColumn(name="COD_CARRO")
    private Carrotfmi carrotfmi;

    public Listacompratfmi() {
    }

    public Listacompratfmi(int cantidad, Productotfmi productotfmi) {
        this.cantidad = cantidad;
        this.productotfmi = productotfmi;
        this.subtotal = cantidad*productotfmi.getPrecio();
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public void setCodListacompra(int codListacompra) {
        this.codListacompra = codListacompra;
    }

    public long getCodListacompra() {
        return codListacompra;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }
     
    public Productotfmi getProductotfmi() {
        return productotfmi;
    }

    public void setProductotfmi(Productotfmi productotfmi) {
        this.productotfmi = productotfmi;
    }
    
    public void setCarrotfmi(Carrotfmi carrotfmi) {
        this.carrotfmi = carrotfmi;
    }

    public Carrotfmi getCarrotfmi() {
        return carrotfmi;
    }

}
