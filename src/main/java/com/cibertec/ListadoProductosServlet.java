package com.cibertec;

import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;
import com.cibertec.model.dao.impl.ProductoDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


//Establecemos la conexion WEB de los servlets Con el NAME y su respectiva URL
@WebServlet(name = "ListadoProductosServlet", urlPatterns = "/ListadoProductosServlet")
public class ListadoProductosServlet extends HttpServlet {


    //creamos una nueva variable que nos permita establecer una conexion
    //con ProductoDao para luego ingresar y a ProductoDaoImpl
    ProductoDao productoDao = new ProductoDaoImpl();


    //llamamos aun metodo GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {


        try {
            //Creamos una nueva lista llamada productos, ingresando a traves
            // de la variable productoDao que declaramos al inicio y asi
            // que nos permita llamar al metodo listarProducto que representa el
            //llamado de los productos registrados en la base de datos
            List<Producto> productos = productoDao.listarProducto();


            //recuperamos la lista y la almacenamos para asi poder enviarsela
            //al index.jsp
            request.setAttribute("listaProductos", productos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, resp);

        }//capturamos posibles errores que puedan ocurrir
        catch (SQLException error){
            throw new ServletException("Error al obtener la lista", error);
        }

    }
}