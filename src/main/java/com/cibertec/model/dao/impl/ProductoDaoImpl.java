package com.cibertec.model.dao.impl;

import com.cibertec.DBConnection;
import com.cibertec.model.Producto;
import com.cibertec.model.dao.ProductoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//como es una extension que usará las interfaces se agrega
//el implements ProductoDao para establecer la relacion
public class ProductoDaoImpl  implements ProductoDao {
    @Override
    public List<Producto> listarProducto() throws SQLException {

        //crae una lista resultado para almacenar los Objetos que se logren recuperar
        List<Producto> resultado = new ArrayList<>();
        //almacena el select que usaremos para listar los productos
        String query = "SELECT id, nombre, precio, stock, descuento FROM Productos";

        try(
                //Estable ce la conexión momentanea para retornar los valores
                //una vez hecho se rompera, sirve para ahorrar recursos
                Connection connection = DBConnection.getConnector();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                )
        {

            //mientras resultSet encuentre valores seguirá imprimiendo y devolviendo los
            //registros de Producto
            while(resultSet.next()){
                //Crea un nuevo productop llamado item
                Producto item = new Producto();

                //retorna los atributos de un objeto producto a un nuevo objeto
                //llamado item
                item.setId(resultSet.getInt("id"));
                item.setNombre(resultSet.getString("nombre"));
                item.setPrecio(resultSet.getDouble("precio"));
                item.setStock(resultSet.getInt("stock"));
                item.setDescuento(resultSet.getInt("descuento"));

                //termina añadiendo el objeto recuperado a la lista resultado
                resultado.add(item);

            }
        } //captura los errores en caso haya uno
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //retorna la lista que creamos al comienzo
        // de esta interfaz con los objetos recuperados
        return resultado;
    }

    @Override
    public Producto obtenerProducto(int id) throws SQLException {
       //creamos una variable global llamada producto
        Producto producto = null;

        //creamos un select que almacene la consulta pero esta vez con
        //con un parametro, para ccapturar un objeto por su id
        String query = "SELECT id, nombre, precio, stock, descuento FROM Productos WHERE id = ? ";

        try(

                //establecemos conexion pero esta  vez no se usa el resultset
                //ya que no vamos a almacenar nada
                Connection connection = DBConnection.getConnector();
                PreparedStatement statement = connection.prepareStatement(query);
                ) {
            //preparamos para usar la consulta con el parametro indicado
            statement.setInt(1,id);
            //creamos ahora si una variable que capture la consulta
            try(ResultSet result = statement.executeQuery()){
                //si la variable puede capturar registros lo
                //seguira haciendo hasta que no haya mas
                if(result.next()){

                    //obtenemos los atributos del objeto encontrado
                    //a traves del id solicitado
                    producto=new Producto(
                            result.getInt("id"),
                            result.getString("nombre"),
                            result.getDouble("precio"),
                            result.getInt("stock"),
                            result.getInt("descuento")
                    );
                }
            }

        } //capturamos los errores que puedan ocurrir
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        //retornamos el objeto producto creado al comienzo de esta interfaz
        return producto;
    }

    @Override
    public void registrarProducto(Producto nuevoProducto) throws SQLException {
        //almacenamos la consulta que deseamos hacer en un string query
        //donde colocaremos 4 signos "?" para poder representar el valor
        //aleatorio que vayamos a introducir
        String query = "INSERT INTO Productos (nombre, precio, stock, descuento) VALUES (?, ?, ?, ?)";

        try(
                //establecemos la conexion momentanea
                Connection connection = DBConnection.getConnector();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            // Connection: interfaz para implementar una sesión cliente-servidor con una base de datos.
            // STATEMENT : sirve para procesar una sentencia SQL estática y obtener los resultados producidos por ella
            // ResultSet : sirve para almacenar la consulta sql

            //colocamos statment para procesar la sentencia SQL y poder
            //introducir los atributos para el nuevo objeto
            statement.setString(1, nuevoProducto.getNombre());
            statement.setDouble(2, nuevoProducto.getPrecio());
            statement.setInt(3, nuevoProducto.getStock());
            statement.setInt(4, nuevoProducto.getDescuento());
            //finalmento actualizamos la consulta
            statement.executeUpdate();


        }
        //capturamos posibles errores que puedan suceder
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
