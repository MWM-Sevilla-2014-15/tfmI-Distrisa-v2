package es.us.master.beans;

import es.us.master.entities.Usuariotfmi;
import es.us.master.utils.InterfazFichaProp;

import java.io.IOException;

import java.util.Locale;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class GeneralBean {

    protected FacesContext context;
    protected InterfazFichaProp prop;
    protected Locale miLocale;
    protected Usuariotfmi loggedUser;
    protected String loggedUserName;

    public GeneralBean() {
        context = FacesContext.getCurrentInstance();
        miLocale = new Locale("es");
        loggedUser = (Usuariotfmi) context.getExternalContext().getSessionMap().get("usuario");
        if (loggedUser == null) {
            loggedUserName = "invitado";
        } else {
            loggedUserName = loggedUser.getUsername();
        }
    }

    public String logout() {
        context.getExternalContext().invalidateSession();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Ha sido desconectado."));
        return "LOGOUT";
    }

    public void goBack() {
        String goBack = (String) context.getExternalContext().getSessionMap().get("pageBack");
        try {
            context.getExternalContext().redirect(goBack);
        } catch (IOException e) {
            context.addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "No se sabe de donde viene"));
        }
    }

    public Locale getMiLocale() {
        return miLocale;
    }
    
    public void cambiarLocale(ActionEvent e) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("idioma");
        miLocale = new Locale(action);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(miLocale);

    }

    public void setLocale(Locale miLocale) {
        this.miLocale = miLocale;
    }

    /*public void cambiarLocale(ActionEvent e) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        if (e.getComponent().getId().equals("ingles"))
            this.miLocale = new Locale("en");
        else
            this.miLocale = new Locale("es");
        contexto.getViewRoot().setLocale(miLocale);
    }*/

    public String getLoggedUserName() {
        return loggedUserName;
    }

    /***************************************************
     *********************MENU**************************
     ***************************************************/

    /*Menu PUBLICO*/
    public String goToRegister() {
        return "REGISTER";
    }

    public String goToLogin() {
        return "LOGIN";
    }

    public String goToShowProducts() {
        return "SHOWPRODUCTS";
    }
    /*Menu CLIENTES*/
    public String goToClientMenu() {
        return "CLIENT";
    }

    public String goToProfile() {
        return "PROFILE";
    }

    public String goToShop() {
        return "SHOP";
    }
    /*Menu ADMINISTRACION*/
    public String goToAdminMenu() {
        return "ADMIN";
    }

    public String goToManageProducts() {
        return "PRODUCTOS";
    }

    public String goToShowTrolley() {
        return "TROLLEY";
    }
    
    public String goToShowTrolleyAdmin(){
        return "TROLLEYADMIN";
    }

}
