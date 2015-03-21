package es.us.master.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * To create ID generator sequence "CARROTFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "CARROTFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Carrotfmi.findAll", query = "select o from Carrotfmi o"),
                @NamedQuery(name = "Carrotfmi.findByOwn", query = "select o from Carrotfmi o where o.usuariotfmi=:u"),
                @NamedQuery(name = "Carrotfmi.findByList", query = "select o from Carrotfmi o where o.listacompratfmi=:l")
            })
@SequenceGenerator(name = "Carrotfmi_Id_Seq_Gen", sequenceName = "CARROTFMI_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
public class Carrotfmi implements Serializable {
    private static final long serialVersionUID = 575609652654615844L;
    @Id
    @Column(name = "COD_CARRO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Carrotfmi_Id_Seq_Gen")
    private BigDecimal codCarro;
    @Temporal(TemporalType.DATE)
    private Date fechacompra;
    @Column(nullable = false)
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "COD_USUARIO")
    private Usuariotfmi usuariotfmi;
    @ManyToOne
    @JoinColumn(name = "COD_LISTACOMPRA")
    private Listacompratfmi listacompratfmi;

    public Carrotfmi() {
    }

    public Carrotfmi(BigDecimal codCarro, Listacompratfmi listacompratfmi, Usuariotfmi usuariotfmi, Date fechacompra,
                     BigDecimal total) {
        this.codCarro = codCarro;
        this.listacompratfmi = listacompratfmi;
        this.usuariotfmi = usuariotfmi;
        this.fechacompra = fechacompra;
        this.total = total;
    }

    public BigDecimal getCodCarro() {
        return codCarro;
    }


    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Usuariotfmi getUsuariotfmi() {
        return usuariotfmi;
    }

    public void setUsuariotfmi(Usuariotfmi usuariotfmi) {
        this.usuariotfmi = usuariotfmi;
    }

    public Listacompratfmi getListacompratfmi() {
        return listacompratfmi;
    }

    public void setListacompratfmi(Listacompratfmi listacompratfmi) {
        this.listacompratfmi = listacompratfmi;
    }
}
