package es.us.master.beans;

import es.us.master.entities.Usuariotfmi;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UsuariotfmiBeanLocal {
    Usuariotfmi persistUsuariotfmi(Usuariotfmi usuariotfmi);

    Usuariotfmi mergeUsuariotfmi(Usuariotfmi usuariotfmi);

    void removeUsuariotfmi(Usuariotfmi usuariotfmi);

    List<Usuariotfmi> getUsuariotfmiFindAll();

    Usuariotfmi getUsuariotfmiUsernamePassword(String username, String password);
    
    int updateUsuariotfmi(Usuariotfmi usuario);
            
    int unregisterUsuariotfmi(Usuariotfmi usuario);
    
    Usuariotfmi getUsuarioByName(String username);
}
