package es.us.master.beans;

import es.us.master.entities.Usuariotfmi;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean extends GeneralBean {

    @EJB
    private UsuariotfmiBeanLocal usuarioBean;
    private Usuariotfmi usuario;

    public LoginBean() {
        usuario = new Usuariotfmi();
        context.getExternalContext().getSessionMap().put("pageBack", "index.jsf");
    }

    public Usuariotfmi getUsuario() {
        return usuario;
    }

    public String login() {
        String res = "";
        String requestUser = usuario.getUsername();
        String requestPass = usuario.getPassword();
        String adminUser = prop.getProp("loginAdmin");
        String adminPass = prop.getProp("passAdmin");

        if (adminUser.equals(requestUser) && adminPass.equals(requestPass)) {
            Usuariotfmi u = usuarioBean.getUsuariotfmiUsernamePassword(requestUser, requestPass);
            res = "ADMIN";
            context.getExternalContext().getSessionMap().put("usuario", u);
            context.getExternalContext().getSessionMap().put("pageBack", "adminZone.jsf");
        } else {
            Usuariotfmi u = usuarioBean.getUsuariotfmiUsernamePassword(requestUser, requestPass);
            if (u == null) {
                context.addMessage(null,
                                   new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
                                                    "No existe un usuario con esa contrase�a"));
                res = "ERROR";
            } else {
                if(Integer.valueOf(u.getActivo()).equals(1)){
                    res = "CLIENT";
                    context.getExternalContext().getSessionMap().put("usuario", u);
                    context.getExternalContext().getSessionMap().put("pageBack", "clientZone.jsf");
                } else {
                    context.addMessage(null,
                                       new FacesMessage(FacesMessage.SEVERITY_INFO, "Error,",
                                                        " lo sentimos pero ese usuario esta dado de baja"));
                }
            }
        }
        return res;
    }
}
