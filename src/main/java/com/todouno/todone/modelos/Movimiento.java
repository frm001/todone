package com.todouno.todone.modelos;

/**
 *
 * @author fernando
 */
public class Movimiento {

    private int idmovimiento;
    private String tipo;
    private String fecha;
    private int idproducto;
    private String idpersona;
    private int cantidad;
    private float vlrunidad;
    private String persona;
    private String producto;

    public Movimiento() {
        this.idmovimiento = 0;
        this.idproducto = 0;
        this.cantidad = 0;
        this.vlrunidad = 0;
        this.tipo = "";
        this.fecha = "";
        this.idpersona = "";
        this.producto = "";
    }

    /**
     * @return the idmovimiento
     */
    public int getIdmovimiento() {
        return idmovimiento;
    }

    /**
     * @param idmovimiento the idmovimiento to set
     */
    public void setIdmovimiento(int idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idproducto
     */
    public int getIdproducto() {
        return idproducto;
    }

    /**
     * @param idproducto the idproducto to set
     */
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    /**
     * @return the idpersona
     */
    public String getIdpersona() {
        return idpersona;
    }

    /**
     * @param idpersona the idpersona to set
     */
    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the vlrunidad
     */
    public float getVlrunidad() {
        return vlrunidad;
    }

    /**
     * @param vlrunidad the vlrunidad to set
     */
    public void setVlrunidad(float vlrunidad) {
        this.vlrunidad = vlrunidad;
    }
    
    /**
     * @return the persona
     */
    public String getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(String persona) {
        this.persona = persona;
    }
    

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return this.producto;
    }
}
