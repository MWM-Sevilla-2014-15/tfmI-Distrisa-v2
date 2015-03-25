package es.us.master.beans;

import es.us.master.entities.Carrotfmi;
import es.us.master.entities.Listacompratfmi;
import es.us.master.entities.Productotfmi;
import es.us.master.entities.Usuariotfmi;
import es.us.master.utils.ListaSelected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ShopBean extends GeneralBean {
    @EJB
    private ProductotfmiBeanLocal productoBean;
    private List<Productotfmi> productos;
    private Map<Integer, Boolean> selectedCodes;
    @EJB
    private ListacompratfmiBeanLocal listaBean;
    private List<ListaSelected> listasSelected;
    @EJB
    private CarrotfmiBeanLocal carroBean;
    

    public ShopBean() {
        super();
        selectedCodes = new HashMap<Integer, Boolean>();
        context.getExternalContext().getSessionMap().put("pageBack", "clientZone.jsf");
        listasSelected = new ArrayList<ListaSelected>();
    }

    @PostConstruct
    public void init() {
        ListaSelected listaSelected;
        Listacompratfmi lista;
        productos = productoBean.getProductotfmiFindByActivo(1);
        for (Productotfmi dataItem : productos) {
            lista = new Listacompratfmi();
            lista.setProductotfmi(dataItem);
            lista.setCantidad(0);
            listaSelected = new ListaSelected();
            listaSelected.setLista(lista);
            listasSelected.add(listaSelected);
        }
    }

    // Actions -----------------------------------------------------------------------------------

    public String buy1() {
        Listacompratfmi lista;
        Carrotfmi carro;
        Double total=0.0;
        for (ListaSelected dataItem : listasSelected) {
            if (dataItem.isSelected() && dataItem.getLista().getCantidad() > 0) {
                lista = new Listacompratfmi(dataItem.getLista().getCantidad(), dataItem.getLista().getProductotfmi());
                listaBean.persistListacompratfmi(lista);
                total+=(dataItem.getLista().getCantidad()*dataItem.getLista().getProductotfmi().getPrecio());
            }
        }
        carro = new Carrotfmi ((Usuariotfmi)context.getExternalContext().getSessionMap().get("usuario"),
                               total);
        carroBean.persistCarrotfmi(carro);
        return "SHOP2";
    }

    public List<Productotfmi> getProductos() {
        return productos;
    }

    public void setProductos(List<Productotfmi> productos) {
        this.productos = productos;
    }

    public void setListasSelected(List<ListaSelected> listasSelected) {
        this.listasSelected = listasSelected;
    }

    public List<ListaSelected> getListasSelected() {
        return listasSelected;
    }
}
