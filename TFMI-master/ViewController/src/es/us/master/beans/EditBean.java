package es.us.master.beans;

import es.us.master.entities.Usuariotfmi;

import java.io.IOException;

import java.util.Date;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class EditBean extends GeneralBean{
        
        private String nombre;
        private String apellidos;
        private String username;
        private String estado;
        private String email;
        private String password;
        private String repassword;

        @EJB
        private UsuariotfmiBeanLocal usuarioBean;
        private Usuariotfmi usuario;

        public EditBean() {
            context = FacesContext.getCurrentInstance();
            usuario = (Usuariotfmi) context.getExternalContext().getSessionMap().get("usuario");
        }

        @PostConstruct
        public void initIt() {
            if (usuario == null) {


                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    context.redirect(context.getRequestContextPath() + "/faces/index.jsf");
                } catch (IOException e) {
                    System.out.println("Error");
                }
            } else {
                nombre = usuario.getNombre();
                apellidos = usuario.getApellidos();
                estado = String.valueOf(usuario.getActivo());
                email = usuario.getEmail();
                username = usuario.getUsername();

            }

        }

        public String edit() {
            if (!password.isEmpty() || !repassword.isEmpty()) {
                if (!password.equals(repassword)) {

                    context.addMessage(null,
                                       new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No coinciden las contrase�as"));

                } else {
                    usuario.setNombre(nombre);
                    usuario.setApellidos(apellidos);
                    usuario.setEmail(email);
                    usuario.setPassword(password);
                    usuario.setFechaact(new Date());
                    usuarioBean.mergeUsuariotfmi(usuario);
                }
            } else {
                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setEmail(email);
                usuario.setFechaact(new Date());
                usuarioBean.mergeUsuariotfmi(usuario);
            }
            return "OK";

        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getApellidos() {
            return apellidos;
        }


        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setRepassword(String repassword) {
            this.repassword = repassword;
        }

        public String getRepassword() {
            return repassword;
        }
}
