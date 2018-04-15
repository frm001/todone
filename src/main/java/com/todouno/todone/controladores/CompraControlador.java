package com.todouno.todone.controladores;

import com.todouno.todone.clases.Operacion;
import com.todouno.todone.modelos.Movimiento;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class CompraControlador {

    private Movimiento compra;
    private Operacion opr;

    /**
     * Creates a new instance of CompraControlador
     */
    public CompraControlador() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.compra = new Movimiento();
        opr = new Operacion();
        this.compra.setTipo("I");
        this.compra.setFecha(sdf.format(Calendar.getInstance().getTime()));
    }

    public void grabar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!this.compra.getIdpersona().isEmpty() && this.compra.getIdproducto() > 0) {
            opr.AddCompra(compra);
            context.addMessage(null, new FacesMessage("Mensaje", "El registro se ha grabado exitosamente"));
        } else{
            context.addMessage(null, new FacesMessage("Error", "Datos esenciales no diligenciados"));
        }
    }

    public void findPersona() {
        if (!this.compra.getIdpersona().isEmpty()) {
            this.compra.setPersona(opr.findNPersona(compra.getIdpersona()));
        }
    }

    public void findProducto() {
        if (this.compra.getIdproducto() > 0) {
            this.compra.setProducto(opr.findNProducto(compra.getIdproducto()));
        }
    }

    /**
     * @return the compra
     */
    public Movimiento getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(Movimiento compra) {
        this.compra = compra;
    }

}
