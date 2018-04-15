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
public class VentaControlador {

    private Movimiento venta;
    private Operacion opr;

    /**
     * Creates a new instance of VentaControlador
     */
    public VentaControlador() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.venta = new Movimiento();
        opr = new Operacion();
        this.venta.setTipo("S");
        this.venta.setFecha(sdf.format(Calendar.getInstance().getTime()));
    }

    public void grabar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!this.venta.getIdpersona().isEmpty() && this.venta.getIdproducto() > 0) {
            int msaldo = opr.leeSaldo(this.venta.getIdproducto()); 
            if (msaldo >= this.venta.getCantidad()) {
                opr.AddCompra(venta);
                context.addMessage(null, new FacesMessage("Mensaje", "El registro se ha grabado exitosamente"));
            } else {
                context.addMessage(null, new FacesMessage("Mensaje", "La cantida a vender supera la existencia. Saldo ( " + msaldo +" )" ));
            }
        } else {
            context.addMessage(null, new FacesMessage("Error", "Datos esenciales no diligenciados"));
        }
    }

    public void findPersona() {
        if (!this.venta.getIdpersona().isEmpty()) {
            this.venta.setPersona(opr.findNPersona(venta.getIdpersona()));
        }
    }

    public void findProducto() {
        if (this.venta.getIdproducto() > 0) {
            this.venta.setProducto(opr.findNProducto(venta.getIdproducto()));
        }
    }

    /**
     * @return the venta
     */
    public Movimiento getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Movimiento venta) {
        this.venta = venta;
    }

}
