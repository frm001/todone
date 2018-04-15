package com.todouno.todone.modelos;

/**
 *
 * @author fernando
 */
public class Persona {

    private String idpersona;
    private String nombre;

    public Persona() {
        this.idpersona = "";
        this.nombre = "";
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
