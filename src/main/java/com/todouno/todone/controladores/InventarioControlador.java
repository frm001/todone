package com.todouno.todone.controladores;

import com.todouno.todone.clases.Operacion;
import com.todouno.todone.modelos.Inventario;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fernando
 */
@ManagedBean
@ViewScoped
public class InventarioControlador {

    private ArrayList<Inventario> inv;

    /**
     * Creates a new instance of InventarioControlador
     */
    public InventarioControlador() {        
        Operacion op = new Operacion();
        this.inv = op.leeInventario();
    }

    /**
     * @return the inv
     */
    public ArrayList<Inventario> getInv() {
        return inv;
    }

    /**
     * @param inv the inv to set
     */
    public void setInv(ArrayList<Inventario> inv) {
        this.inv = inv;
    }

}
