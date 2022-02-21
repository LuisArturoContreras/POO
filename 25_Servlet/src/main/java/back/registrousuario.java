/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back;



import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class registrousuario extends HttpServlet {

    /**
   para poder realizar una conezion con la base de datps se necesota el uso
   * de los objetos
   * statement
   * conecction
   * resulset
   * esos objetos deben de ser privados porque vamos a establecer una conexion remota
   * a un servidor diferente
   * 
     */
    
    
    private Connection con = null; //establece la conexion con la bd
    private Statement set;//establece operaciones o sentencias sql para insert, update, delete
                                 //maneja sql como lenguaje de ldd (lenguaje de definicion de datos)
    private ResultSet rs = null;//maneja todo tipo de consultas
    
    
    //constructor de la clase
    
    public void init(ServletConfig cfg)throws ServletException{
        //hay que definir los elementos del constructor que se encargan ahora de concetar con la bd
        //para establecer la conexon necesitamos la ruta
        
        String URL = "jdbc:mysql:3306//localhost/funkos";//jbdc:mysql:puerto//localhost//nombrebd
        String userName = "Luis";
        String password = "1626LUISCONTRERAS";
        
        try{
            URL = "jdbc:mysql://localhost/funkos";
            //establecer manejaor del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL, userName, password);
            set = con.createStatement();
            System.out.println("Se conecto con la BD");
        }catch(Exception e){
            System.out.println("No se conecto a la BD");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
        }
    
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registrousuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registrousuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            System.out.println("<table border = 1>"
                    + "<tr>"
                    + "<th>Num_Empleado</th>"
                    + "<th>Nombre</th>"
                    + "<th>User</th>"
                    + "<th>Password</th>"
                    + "</tr>"
                    + "</table>");
            
            try{
                int num_empleado;
                String nombre, user, password;
                num_empleado = Integer.parseInt(request.getParameter("num_emp"));
                nombre = request.getParameter("nombre");
                user = request.getParameter("user");
                password = request.getParameter("password");
                
                // hay que insertarlos
                
                String q = "insert into empleado values("+num_empleado+",'"+nombre+"','"+user+"','"+password+"')";
                set.executeUpdate(q);
            }catch(SQLException ex){
                System.out.println("No se conecto con la tabla");
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
                
                out.println("<h1>Registro no exitoso</h1>");
                
            }
            
            out.println("<a href='registro.html'>Regresar al registro</a>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
