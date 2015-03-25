package es.us.master.utils;

import es.us.master.entities.Listacompratfmi;

public class ListaSelected {
    private Listacompratfmi lista;
    /*For UI purporses*/
    private boolean selected = false;
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setLista(Listacompratfmi lista) {
        this.lista = lista;
    }

    public Listacompratfmi getLista() {
        return lista;
    }
}
