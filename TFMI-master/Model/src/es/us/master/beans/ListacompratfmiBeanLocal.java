package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Listacompratfmi;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ListacompratfmiBeanLocal {
    Listacompratfmi persistListacompratfmi(Listacompratfmi listacompratfmi);

    Listacompratfmi mergeListacompratfmi(Listacompratfmi listacompratfmi);

    void removeListacompratfmi(Listacompratfmi listacompratfmi);

    List<Listacompratfmi> getListacompratfmiFindAll();
    
    int unpdateCodCarro(Carrotfmi carro);
}
