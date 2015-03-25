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

/**
 * To create ID generator sequence "USUARIOTFMI_ID_SEQ_GEN":
 * CREATE SEQUENCE "USUARIOTFMI_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Usuariotfmi.findAll", query = "select o from Usuariotfmi o"), 
                @NamedQuery (name = "Usuariotfmi.findByUserAndPass", query = "select o from Usuariotfmi o where o.username=:u and o.password=:p")
            })
@SequenceGenerator(name = "Usuariotfmi_Id_Seq_Gen", sequenceName = "USUARIOTFMI_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
public class Usuariotfmi implements Serializable {
    private static final long serialVersionUID = 2357059021468722777L;
    @Column(nullable = false)
    private int activo = 1;
    @Column(nullable = false, length = 20)
    private String apellidos;
    @Id
    @Column(name = "COD_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuariotfmi_Id_Seq_Gen")
    private int codUsuario;
    @Column(nullable = false, length = 20)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date fechaact = new Date();
    @Temporal(TemporalType.DATE)
    private Date fechaalt = new Date();
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    @Column(nullable = false, length = 20)
    private String nombre;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false)
    private int rol = 2;
    @Column(nullable = false, length = 20)
    private String username;
    @OneToMany(mappedBy = "usuariotfmi", cascade = CascadeType.ALL)
    private List<Carrotfmi> carrotfmiList;

    public Usuariotfmi() {
    }

    public Usuariotfmi(int activo, String apellidos, int codUsuario, String email, Date fechaact,
                       Date fechaalt, Date fechanac, String nombre, String password, int rol, String username) {
        this.activo = activo;
        this.apellidos = apellidos;
        this.codUsuario = codUsuario;
        this.email = email;
        this.fechaact = fechaact;
        this.fechaalt = fechaalt;
        this.fechanac = fechanac;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.username = username;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    
    public void setCodUsuario(int codUsuario){
        this.codUsuario=codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Carrotfmi> getCarrotfmiList() {
        return carrotfmiList;
    }

    public void setCarrotfmiList(List<Carrotfmi> carrotfmiList) {
        this.carrotfmiList = carrotfmiList;
    }

    public Carrotfmi addCarrotfmi(Carrotfmi carrotfmi) {
        getCarrotfmiList().add(carrotfmi);
        carrotfmi.setUsuariotfmi(this);
        return carrotfmi;
    }

    public Carrotfmi removeCarrotfmi(Carrotfmi carrotfmi) {
        getCarrotfmiList().remove(carrotfmi);
        carrotfmi.setUsuariotfmi(null);
        return carrotfmi;
    }
}
