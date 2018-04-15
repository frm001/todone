package com.todouno.todone.clases;

import com.google.gson.Gson;
import com.todouno.todone.modelos.Inventario;
import com.todouno.todone.modelos.Movimiento;
import com.todouno.todone.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Operacion extends Conexciondb {

    private final Connection cnx;

    public Operacion() {
        this.cnx = conectar();
    }

    public void Cerrarcnx() {
        try {
            if (!cnx.isClosed()) {
                cnx.close();
                Logger.getLogger(Operacion.class.getName()).log(Level.INFO, "Conexion cerrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adicion de un producto
     *
     * @param obj : Objeto tipo producto
     */
    public void AddProducto(Producto obj) {
        String sql = "INSERT INTO producto (descripcion) VALUES ('"
                + obj.getDescripcion() + "')";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.Cerrarcnx();
        }
    }

    public void AddCompra(Movimiento o) {
        String sql = "INSERT INTO movimiento (tipo, fecha, idproducto, "
                + "idpersona, cantidad, vlrunidad) VALUES ('" + o.getTipo()
                + "', '" + o.getFecha() + "', " + o.getIdproducto()
                + ", '" + o.getIdpersona() + "', " + o.getCantidad()
                + ", " + o.getVlrunidad() + ")";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.Cerrarcnx();
        }
    }

    /**
     * Recuperacion de todos los productos en formato JSON
     *
     * @return Listado de productos
     */
    public String apiProductos() {
        ArrayList<Producto> datos = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM producto ORDER BY descripcion");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto op = new Producto();
                op.setIdproducto(rs.getInt("idproducto"));
                op.setDescripcion(rs.getString("descripcion"));
                datos.add(op);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.Cerrarcnx();
        }
        Gson gson = new Gson();
        return gson.toJson(datos);
    }

    public String findNPersona(String id) {
        String rsp = "";
        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT nombre FROM persona "
                    + "WHERE idpersona ='" + id + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsp = rs.getString("nombre");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsp;
    }

    public String findNProducto(int id) {
        String rsp = "";
        try {
            PreparedStatement ps = cnx.prepareStatement("SELECT descripcion FROM producto "
                    + "WHERE idproducto =" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsp = rs.getString("descripcion");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsp;
    }
    
    public ArrayList<Inventario> leeInventario(){
        ArrayList<Inventario> datos = new ArrayList<>();
        int ci, ce, ti, te;
        try {
            String sql = "SELECT a.idproducto, a.descripcion, "
                    + "(SELECT SUM(c.cantidad) FROM movimiento c "
                    + "WHERE c.tipo = 'I' AND c.idproducto = a.idproducto) AS com, "
                    + "(SELECT SUM(v.cantidad) FROM movimiento v "
                    + "WHERE v.tipo = 'S' AND v.idproducto = a.idproducto) AS ven, "
                    + "(SELECT COUNT(*) FROM movimiento h "
                    + "WHERE h.tipo = 'I' AND h.idproducto = a.idproducto) AS cti, "                    
                    + "(SELECT COUNT(*) FROM movimiento i "
                    + "WHERE i.tipo = 'S' AND i.idproducto = a.idproducto) AS ctv "                    
                    + "FROM producto a ORDER BY a.descripcion";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ci = rs.getInt("cti");
                ce = rs.getInt("ctv");
                if(ci>0)
                    ti = rs.getInt("com");
                else
                    ti = 0;
                if(ce>0)
                    te = rs.getInt("ven");
                else
                    te = 0;
                datos.add(new Inventario(rs.getInt("idproducto"), 
                        rs.getString("descripcion"), ti, te));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.Cerrarcnx();
        }        
        return datos;        
    }
    
    public int leeSaldo(int id){
        int rsp = 0;
        String sql;
        int ci, ce, ti, te;
        try{
            sql = "SELECT a.idproducto, "
                    + "(SELECT SUM(c.cantidad) FROM movimiento c "
                    + "WHERE c.tipo = 'I' AND c.idproducto = a.idproducto) AS com, "
                    + "(SELECT SUM(v.cantidad) FROM movimiento v "
                    + "WHERE v.tipo = 'S' AND v.idproducto = a.idproducto) AS ven, "
                    + "(SELECT COUNT(*) FROM movimiento h "
                    + "WHERE h.tipo = 'I' AND h.idproducto = a.idproducto) AS cti, "                    
                    + "(SELECT COUNT(*) FROM movimiento i "
                    + "WHERE i.tipo = 'S' AND i.idproducto = a.idproducto) AS ctv "                    
                    + "FROM producto a WHERE a.idproducto =" + id;
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ci = rs.getInt("cti");
                ce = rs.getInt("ctv");
                if(ci>0)
                    ti = rs.getInt("com");
                else
                    ti = 0;
                
                if(ce>0)
                    te = rs.getInt("ven");
                else
                    te = 0;
                
                rsp = ti - te;
            }
            rs.close();
            ps.close();            
        } catch (SQLException ex) {
            Logger.getLogger(Operacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsp;
    }

}
