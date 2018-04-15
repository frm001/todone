package com.todouno.todone.ws;

import com.google.gson.Gson;
import com.todouno.todone.clases.Operacion;
import com.todouno.todone.modelos.Inventario;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author fernando
 */
@Path("service/{type}")
public class Wsrest {
    
    @GET  
    @Produces({"text/plain", "text/html", "application/json"}) 
    public String mostrarMensaje(@PathParam("type") String tipo){
        if (tipo.equalsIgnoreCase("prod")) {
             Operacion ops = new Operacion();                    
            return ops.apiProductos();
        } else if (tipo.equalsIgnoreCase("inv")) {
            Operacion ops = new Operacion();
            ArrayList<Inventario> inv = ops.leeInventario();
            Gson gson = new Gson();
            return gson.toJson(inv);            
        } else {
            return "Operacion no permitida";
        }
    }    
    
}
