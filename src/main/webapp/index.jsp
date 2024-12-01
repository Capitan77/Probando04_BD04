<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cibertec.model.Producto"%>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!--Aqui retornaremos los productos que sean registrados-->
<!--Esto se consideraria como la estructura para el servlet ListadoProductosServlet-->

    <div class="container mt-5">
        <h1>Listado de Productos</h1>
        <a href="registro.jsp" class="btn btn-success mb-3">Agregar nuevo producto</a>
        <!-- Bloque listado de productos -->


        <div class="card-group">
        <% List<Producto> items = (List<Producto>) request.getAttribute("listaProductos"); %>

        <% for (Producto item : items) { %>

           <div class="col-md-4 d-inline p-2 ">
                 <div class="card border-secondary p-5 ">
                       <p><strong>Id:</strong>            <%= item.getId() %></p>
                       <p><strong>Nombre:</strong>        <%= item.getNombre() %></p>
                       <p><strong>Precio:</strong>        <%= item.getPrecio() %></p>
                       <p><strong>Stock:</strong> S/      <%= item.getStock() %></p>
                       <p><strong>Descuento:</strong>     <%= item.getDescuento() %></p>
                       <a class="btn btn-primary">Editar</a>
                       <a class="btn btn-dark">Eliminar</a>
                 </div>
            </div>

        <% } %>


    </div>
</body>
</html>