package com.PotatoServer.Servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.lib.Dbutils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;

/**
 * Servlet implementation class Manament
 */
@WebServlet(urlPatterns = { 
		"/Management", 
		"/Management/*"
}, 
initParams = { 
	@WebInitParam(name = "data-source", value = "jdbc/potatodb")
})
public class Manament extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manament() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Dbutils db = new Dbutils();
		db.createSchema();
		
        _ds=db.assemble(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs= null;

		String PID = request.getParameter("P_ID");
    	
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection("");
            ps = con.prepareStatement(stmt);
        	ps.setString(1, faultId);
        	
        	System.out.println(faultId);
        	
        	
            rs=ps.executeQuery();
            
            if (rs.next()) {
                // redirect to error page
            	String PName = rs.getString("Name");
            	String PDescription = rs.getString("Description");
                
            	
            	request.setAttribute("faultDetails", faultDetails);
            	request.setAttribute("faultSummary", faultSummary);
            	
            	RequestDispatcher rd = request.getRequestDispatcher("/FaultSummary.jsp"); 

        		rd.forward(request, response);
                //response.sendRedirect("/FaultDetails.jsp"); 
                
               
            } 
        } catch (Exception e)
        {
        System.out.println(e);
        }
        
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
