package com.todouno.todone.modelos;

/**
 *
 * @author fernando
 */
public class Inventario {

    private int idproducto;
    private String descripcion;
    private int compras;
    private int ventas;
    private int saldo;

    public Inventario() {
    }

    public Inventario(int idproducto, String descripcion, int compras, 
            int ventas) {
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.compras = compras;
        this.ventas = ventas;
        this.saldo = compras - ventas;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the compras
     */
    public int getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(int compras) {
        this.compras = compras;
    }

    /**
     * @return the ventas
     */
    public int getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the saldo
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

}
