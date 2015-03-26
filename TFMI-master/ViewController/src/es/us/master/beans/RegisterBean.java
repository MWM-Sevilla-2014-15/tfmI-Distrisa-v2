package es.us.master.beans;

import es.us.master.entities.Usuariotfmi;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterBean extends GeneralBean {

    @EJB
    private UsuariotfmiBeanLocal usuarioBean;
    private Usuariotfmi usuario;
    private String repassword;

    public RegisterBean() {
        usuario = new Usuariotfmi();
    }

    public Usuariotfmi getUsuario() {
        return usuario;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String register() {
        String requestUser = usuario.getUsername();
        String res = validate();
        if (!res.equals("ERROR")) {
            Usuariotfmi u = usuarioBean.persistUsuariotfmi(usuario);
            if (u == null) {
                context.addMessage(null,
                                   new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
                                                    "No se ha podido crear el usuario."));
                res = "ERROR";
            } else if (u.getUsername().equals(requestUser)){
                context.addMessage(null,
                                   new FacesMessage(FacesMessage.SEVERITY_INFO, "Error, ", " nombre de usuario actualmente en uso. Intentelo de nuevo."));
                res = "ERROR";
            }else {
                context.addMessage(null,
                                   new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "El usuario ha sido creado."));
            }
        }
        return res;
    }

    private String validate() {
        String res = "OK";
        if (!usuario.getPassword().equals(repassword)) {
            context.addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
                                                "Las contrase�as no son iguales."));
            res = "ERROR";
        }
        return res;
    }
}
