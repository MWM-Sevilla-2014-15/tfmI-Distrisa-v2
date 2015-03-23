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
        String res;
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
                res = "CLIENT";
                context.getExternalContext().getSessionMap().put("usuario", u);
                context.getExternalContext().getSessionMap().put("pageBack", "clientZone.jsf");
            }
        }
        return res;
    }
}
