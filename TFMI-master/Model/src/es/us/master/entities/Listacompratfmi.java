package es.us.master.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * To create ID generator sequence "LISTACOMPRATFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "LISTACOMPRATFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Listacompratfmi.findAll", query = "select o from Listacompratfmi o") })
@SequenceGenerator(name = "Listacompratfmi_Id_Seq_Gen", sequenceName = "LISTACOMPRATFMI_ID_SEQ_GEN",
                   allocationSize = 50, initialValue = 50)
public class Listacompratfmi implements Serializable {
    private static final long serialVersionUID = 4802206896525593530L;
    @Column(nullable = false)
    private BigDecimal cantidad;
    @Id
    @Column(name = "COD_LISTACOMPRA", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Listacompratfmi_Id_Seq_Gen")
    private BigDecimal codListacompra;
    @Column(nullable = false)
    private BigDecimal subtotal;
    @ManyToOne
    @JoinColumn(name = "COD_PRODUCTO")
    private Productotfmi productotfmi;
    @OneToMany(mappedBy = "listacompratfmi", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Carrotfmi> carrotfmiList1;

    public Listacompratfmi() {
    }

    public Listacompratfmi(BigDecimal cantidad, BigDecimal codListacompra, Productotfmi productotfmi,
                           BigDecimal subtotal) {
        this.cantidad = cantidad;
        this.codListacompra = codListacompra;
        this.productotfmi = productotfmi;
        this.subtotal = subtotal;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCodListacompra() {
        return codListacompra;
    }


    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Productotfmi getProductotfmi() {
        return productotfmi;
    }

    public void setProductotfmi(Productotfmi productotfmi) {
        this.productotfmi = productotfmi;
    }

    public List<Carrotfmi> getCarrotfmiList1() {
        return carrotfmiList1;
    }

    public void setCarrotfmiList1(List<Carrotfmi> carrotfmiList1) {
        this.carrotfmiList1 = carrotfmiList1;
    }

    public Carrotfmi addCarrotfmi(Carrotfmi carrotfmi) {
        getCarrotfmiList1().add(carrotfmi);
        carrotfmi.setListacompratfmi(this);
        return carrotfmi;
    }

    public Carrotfmi removeCarrotfmi(Carrotfmi carrotfmi) {
        getCarrotfmiList1().remove(carrotfmi);
        carrotfmi.setListacompratfmi(null);
        return carrotfmi;
    }
}
