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

import com.PotatoServer.Models.ProblemModel;
import com.PotatoServer.Stores.ProblemDes;
import com.PotatoServer.lib.Dbutils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.util.Iterator;
import java.util.LinkedList;

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
		// TODO Auto-generated method stub
				System.out.println("Starting GET");
				//String args[]=Convertors.SplitRequestPath(request);
				Iterator<ProblemDes> iterator;
				ProblemModel Problem = new ProblemModel(); //Create a new instance of the model

				Problem.setDatasource(_ds);
				LinkedList<ProblemDes> psl = Problem.getDES();
				// Get a list of all faults

				/* If we want to forward to a jsp page do this */
				request.setAttribute("Faults", psl); //Set a bean with the list in it
				RequestDispatcher rd = request.getRequestDispatcher("/SelectEditFault.jsp"); 
				 
				
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
