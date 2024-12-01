<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cibertec.model.Producto"%>

<!DOCTYPE html>
<html>
<head>
    <title>Registro de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
     <div class="container mt-5">
           <h1>Registrar Producto</h1>


      <!--Elaboramos un formulario donde registraremos: nombre, precio, stock-->
      <!--Los datos de este formulario seran enviado al action llamado RegistroProductoServlet-->
      <!--El metodo de este formulario sera tipo POST-->
           <form action="RegistroProductoServlet" method="POST">
                     <div class="mb-3">
                          <label for="nombre" class="form-label">Nombre</label>
                          <input type="text" class="form-control" id="nombre" name="nombre" required>
                     </div>
                      <div class="mb-3">
                            <label for="precio" class="form-label">precio</label>
                            <input type="number" class="form-control" id="precio" name="precio" required>
                       </div>
                     <div class="mb-3">
                          <label for="stock" class="form-label">stock</label>
                          <input type="number" class="form-control" id="stock" name="stock" required>
                     </div>
                     <button type="submit" class="btn btn-primary">Guardar</button>


           </form>
     </div>
</body>
</html>