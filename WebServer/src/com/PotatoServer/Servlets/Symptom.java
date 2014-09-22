package com.PotatoServer.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.Models.SymptomModel;
import com.PotatoServer.Stores.SymptomStore;
import com.PotatoServer.lib.Dbutils;

/**
 * Servlet implementation class Problem
 */
@WebServlet(urlPatterns = { 
		"/Symptom", 
		"/Symptom/*"
}, 
initParams = { 
	@WebInitParam(name = "data-source", value = "jdbc/potatodb")
})
@MultipartConfig
public class Symptom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Symptom() {
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
		Iterator<SymptomStore> iterator;
		SymptomModel symptomModel = new SymptomModel(); //Create a new instance of the model

		symptomModel.setDatasource(_ds);
		LinkedList<SymptomStore> psl = symptomModel.getAllSymptoms();
		// Get a list of all faults

		/* If we want to forward to a jsp page do this */
		request.setAttribute("symptom", psl); //Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/ShowAllSymptoms.jsp"); 
		 
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
