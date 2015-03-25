package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Usuariotfmi;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CarrotfmiBeanLocal {
    Carrotfmi persistCarrotfmi(Carrotfmi carrotfmi);

    Carrotfmi mergeCarrotfmi(Carrotfmi carrotfmi);

    void removeCarrotfmi(Carrotfmi carrotfmi);

    List<Carrotfmi> getCarrotfmiFindAll();

    List<Carrotfmi> getCarrotfmiFindByOwn(Usuariotfmi usuario);

}
