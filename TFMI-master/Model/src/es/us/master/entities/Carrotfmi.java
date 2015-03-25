package es.us.master.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * To create ID generator sequence "CARROTFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "CARROTFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Carrotfmi.findAll", query = "select o from Carrotfmi o"),
                @NamedQuery(name = "Carrotfmi.findByOwn", query = "select o from Carrotfmi o where o.usuariotfmi=:u")
            })
@SequenceGenerator(name = "Carrotfmi_Id_Seq_Gen", sequenceName = "CARROTFMI_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
public class Carrotfmi implements Serializable {
    private static final long serialVersionUID = 575609652654615844L;
    @Id
    @Column(name = "COD_CARRO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Carrotfmi_Id_Seq_Gen")
    private int codCarro;
    @Temporal(TemporalType.DATE)
    private Date fechacompra = new Date();
    @Column(nullable = true)
    private double total;
    @ManyToOne
    @JoinColumn(name = "COD_USUARIO")
    private Usuariotfmi usuariotfmi;
    @OneToMany(mappedBy = "carrotfmi", cascade=CascadeType.PERSIST)
    private List<Listacompratfmi> listafmList1 = new ArrayList<Listacompratfmi>();

    public Carrotfmi(Usuariotfmi usuariotfmi, double total) {
        this.usuariotfmi = usuariotfmi;
        this.total = total;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    
    public Usuariotfmi getUsuariotfmi() {
        return usuariotfmi;
    }

    public void setUsuariotfmi(Usuariotfmi usuariotfmi) {
        this.usuariotfmi = usuariotfmi;
    }

    public void setCodCarro(int codCarro) {
        this.codCarro = codCarro;
    }

    public int getCodCarro() {
        return codCarro;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setListafmList1(List<Listacompratfmi> listafmList1) {
        this.listafmList1 = listafmList1;
    }

    public List<Listacompratfmi> getListafmList1() {
        return listafmList1;
    }

    public Carrotfmi() {
    }
    
    public Listacompratfmi addListacompratfmi(Listacompratfmi listacompra) {
        getListafmList1().add(listacompra);
        listacompra.setCarrotfmi(this);
        return listacompra;
    }

    public Listacompratfmi removeCarrotfmi(Listacompratfmi listacompra) {
        getListafmList1().remove(listacompra);
        listacompra.setCarrotfmi(null);
        return listacompra;
    }
}
