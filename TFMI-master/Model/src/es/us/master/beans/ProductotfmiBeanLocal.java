package es.us.master.beans;

import es.us.master.entities.Productotfmi;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProductotfmiBeanLocal {
    Productotfmi persistProductotfmi(Productotfmi productotfmi);

    Productotfmi mergeProductotfmi(Productotfmi productotfmi);

    void removeProductotfmi(Productotfmi productotfmi);

    List<Productotfmi> getProductotfmiFindAll();

    List<Productotfmi> getProductotfmiFindByCategory(String categoria);

    List<Productotfmi> getProductotfmiFindByName(String nombre);
    
    List<Productotfmi> getProductotfmiFindByActivo(int activo);
}
