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

import com.PotatoServer.Models.ProblemModel;
import com.PotatoServer.Models.SymptomModel;
import com.PotatoServer.Stores.SymptomStore;
import com.PotatoServer.lib.Convertors;
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
	//private String saveLocation = "/Users/tombutterwith/Desktop";
	private String SAVE_DIR = "images";

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

		SymptomModel symptomModel = new SymptomModel();
		symptomModel.setDatasource(_ds);
		String args[] = Convertors.SplitRequestPath(request);
		if(args.length > 2){
			if(args[2].equals("delete")){
				String dl = request.getParameter("id");
				symptomModel.deleteSymptom(Integer.parseInt(dl));
				request.setAttribute("symptoms", symptomModel.getAllSymptoms());
				RequestDispatcher rd = request.getRequestDispatcher("/ShowAllSymptoms.jsp"); 
				rd.forward(request, response);

			} else if (args[2].equals("edit")){
				String edit = request.getParameter("id");
				//SymptomModel.editprob(edit);
				request.setAttribute("symptom", SymptomModel.getSymptomByID(Integer.parseInt(edit), _ds));
				request.setAttribute("symptoms", symptomModel.getAllSymptoms());
				RequestDispatcher rd = request.getRequestDispatcher("/EditSymptom.jsp"); 
				rd.forward(request, response);
				//do edit stuff
			} else if (args[2].equals("add")) {
				request.setAttribute("symptoms", symptomModel.getAllSymptoms());
				RequestDispatcher rd = request.getRequestDispatcher("/AddSymptom.jsp"); 
				rd.forward(request, response);
			}
		} else {

			LinkedList<SymptomStore> psl = symptomModel.getAllSymptoms();
			// Get a list of all faults

			/* If we want to forward to a jsp page do this */
			request.setAttribute("symptoms", psl); //Set a bean with the list in it
			RequestDispatcher rd = request.getRequestDispatcher("/ShowAllSymptoms.jsp"); 


			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
		String description = request.getParameter("symptomDescription");
		Integer parentSymptom = !request.getParameter("parentSymptom").equals("null") ? Integer.parseInt(request.getParameter("parentSymptom")) : null;
		String type = request.getParameter("type");
		boolean newSymptom = id == null ? true : false;
		
		SymptomStore symptom;
		if(newSymptom)
			symptom = new SymptomStore();
		else
			symptom = SymptomModel.getSymptomByID(id, _ds);

		symptom.setDescription(description);
		if(parentSymptom != null) { symptom.setParentSymptom(parentSymptom); }
		symptom.setImageLocation(request.getPart("file1") == null ? null : description);
		symptom.setType(type);

		SymptomModel symptomModel = new SymptomModel();
		symptomModel.setDatasource(_ds);
		if(newSymptom)
			symptomModel.updateSymptom(symptom, newSymptom);
		else
			symptomModel.updateSymptom(symptom, newSymptom);
		
		if (id == null)
			id = symptomModel.getSymptomID(description);

		// gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }


		Part part = request.getPart("file1");
		
		String url = null;

		String fileName = description;
		if(part != null) {
			try{
			part.write(savePath + File.separator + "symptom_" + fileName + ".png"); 
			url = "symptom_" +  fileName + ".png";
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}

		symptomModel.updateImageURLs(url, id);
		
		
		

		LinkedList<SymptomStore> psl = symptomModel.getAllSymptoms();
		// Get a list of all faults

		/* If we want to forward to a jsp page do this */
		request.setAttribute("symptoms", psl); //Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/ShowAllSymptoms.jsp"); 


		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
