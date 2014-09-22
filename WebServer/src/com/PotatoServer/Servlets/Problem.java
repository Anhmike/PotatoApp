package com.PotatoServer.Servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.Models.ProblemModel;
import com.PotatoServer.Stores.ProblemStore;
import com.PotatoServer.lib.Dbutils;

import java.sql.Connection;



/**
 * Servlet implementation class Problem
 */
@WebServlet(urlPatterns = { 
		"/Problem", 
		"/Problem/*"
}, 
initParams = { 
	@WebInitParam(name = "data-source", value = "jdbc/potatodb")
})
public class Problem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Problem() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Dbutils db = new Dbutils();
		//db.createSchema();
		
        _ds=db.assemble(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				Connection con = null;
				
			    
				System.out.println("Starting GET");
				//String args[]=Convertors.SplitRequestPath(request);
				Iterator<ProblemStore> iterator;
				ProblemModel Problem = new ProblemModel(); //Create a new instance of the model

				Problem.setDatasource(_ds);
				LinkedList<ProblemStore> psl = Problem.getDES();
				// Get a list of all faults

				/* If we want to forward to a jsp page do this */
				request.setAttribute("Problems", psl); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("/Problem.jsp"); 
				 
				
				rd.forward(request, response);
				
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				
			    con = DriverManager.getConnection("jdbc:mysql://SILVA:3306/14indt3db","14indt3","312.bac;");
			    
			    PreparedStatement doSub = null;
			    
			    String del = request.getParameter("Delete");
			    
			    String sql = ("{call delete_problem (?)}");
			    
				
				}
				catch(Exception e)
				{
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
