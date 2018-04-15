package com.todouno.todone.controladores;

import com.todouno.todone.clases.Operacion;
import com.todouno.todone.modelos.Producto;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author fernando
 */
@ManagedBean
@ViewScoped
public class ProductoControlador {
    
    private Producto producto;

    /**
     * Creates a new instance of ProductoControlador
     */
    public ProductoControlador() {
        this.producto = new Producto();
    }
    
    public void grabar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(!producto.getDescripcion().isEmpty()){
            Operacion op = new Operacion();
            op.AddProducto(this.producto);
            context.addMessage(null, new FacesMessage("Mensaje", "El registro se ha grabado exitosamente"));
        } else{
            context.addMessage(null, new FacesMessage("Error", "Por favor indique el nombre del producto"));
        }
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
