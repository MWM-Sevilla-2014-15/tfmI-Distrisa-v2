package es.us.master.beans;

import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BeanIdioma {

    private Locale miLocale;
    private FacesContext contextoIdioma;

    public BeanIdioma() {
        this.miLocale = new Locale("es");
    }

    public Locale getLocale() {
        return miLocale;
    }

    public void cambiarLocale(ActionEvent e) {
        Map<String, String> params = contextoIdioma.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("idioma");
        miLocale = new Locale(action);
        contextoIdioma.getCurrentInstance().getViewRoot().setLocale(miLocale);
    }

    public void setLocale(Locale miLocale) {
        this.miLocale = miLocale;
    }
}