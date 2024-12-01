package com.cibertec;

import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;
import com.cibertec.model.dao.impl.ProductoDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


//establecemos la conexion con la WEB de Servlet
@WebServlet(name = "RegistroProductoServlet", urlPatterns = "/RegistroProductoServlet")
public class RegistroProductoServlet extends HttpServlet {


    //creamos una variable que nos permita ingresar a productoDao y asi luego a ProductoDaoImpl
    ProductoDao productoDao = new ProductoDaoImpl();


    //llamamos a un metodo POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //Declaramos variables tipo cadena para almacenar los parametros ingresados
        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        String stockStr  = req.getParameter("stock");


        try {
            //en caso de nombre , precio, stock esten vacios nos lanzará un mensaje
            //indicandonos que necesitamos llenar los campos
            if(nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                throw  new Exception("los campos no deben ser vacias");
            }

            //En caso de estene llenos los retornaremos a su tipo de dato original
            //el descuetno se validara por medio de una condicion
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            int descuento = (precio > 500) ? 10 : 0;

            // operador ternario
            // (condicon) ? si es verdadero : si es falso


            //se creara un objeto llamado nuevoProducto que capturara
            //los valores ingresados
            Producto nuevoProducto = new Producto(
                    0,nombre,precio,stock,descuento

            );


            //llamamos al interfaz registrarProducto y agregamos el nuevo producto
            productoDao.registrarProducto(nuevoProducto);


            //esto sera enviado al servlet indicado en los parentesis ListadoProductosServlet
            resp.sendRedirect("ListadoProductosServlet");


        }//capturamos posibles errores
        catch (Exception error){
            throw  new ServletException("No se pudo registrar",error);
        }

    }
}
