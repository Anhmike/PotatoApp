package com.PotatoServer.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.lib.Convertors;
import com.PotatoServer.lib.Dbutils;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet(urlPatterns = { 
		"/Dashboard", 
		"/Dashboard/*"
}, 
initParams = { 
		@WebInitParam(name = "data-source", value = "jdbc/potatodb")
})
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
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
		String args[]=Convertors.SplitRequestPath(request);
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String args[]=Convertors.SplitRequestPath(request);
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
	
	protected void addProblem(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void addSymptom(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void editProblem(HttpServletRequest request, HttpServletResponse response) {
		
	}

	protected void editSymptom(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
