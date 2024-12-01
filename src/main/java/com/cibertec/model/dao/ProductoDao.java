package com.cibertec.model.dao;

import com.cibertec.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {

    //craemos interfaces que contrendras los metodos escenciales para poder
    //realizar las acciones que necesitemos
    //estas responderan al llamado de cualquier servlet que lo necesite
    List<Producto> listarProducto() throws SQLException;
    Producto obtenerProducto(int id) throws  SQLException;
    void registrarProducto(Producto nuevoProducto) throws SQLException;

}
