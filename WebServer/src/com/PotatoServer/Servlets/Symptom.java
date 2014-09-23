package com.PotatoServer.Servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import javax.servlet.http.Part;
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
	private String saveLocation = "/Users/tombutterwith/Desktop";
	private String saveDirectory = "symptomImages";

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
		request.setAttribute("symptoms", psl); //Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/ShowAllSymptoms.jsp"); 


		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("symptomName");
		String description = request.getParameter("symptomDescription");
		Integer parentSymptom = !request.getParameter("parentSymptom").equals("null") ? Integer.parseInt(request.getParameter("parentSymptom")) : null;

		// constructs path of the directory to save uploaded file
		String savePath = saveLocation + File.separator + saveDirectory;

		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}


		Part part = request.getPart("file1");

		String fileName = name;
		if(part != null) { part.write(savePath + File.separator + fileName + ".png"); }
		
		SymptomStore symptom = new SymptomStore();
		symptom.setDescription(description);
		if(parentSymptom != null) { symptom.setParentSymptom(parentSymptom); }
		symptom.setImageLocation(request.getPart("file1") == null ? null : name);
		
		SymptomModel symptomModel = new SymptomModel();
		symptomModel.setDatasource(_ds);
		symptomModel.addSymptom(symptom);
		
		doGet(request, response);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
