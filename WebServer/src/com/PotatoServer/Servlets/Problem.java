package com.PotatoServer.Servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import org.joda.time.DateTime;

import com.PotatoServer.Models.ProblemModel;
import com.PotatoServer.Models.SymptomModel;
import com.PotatoServer.Stores.ProblemStore;
import com.PotatoServer.Stores.SymptomStore;
import com.PotatoServer.lib.Convertors;
import com.PotatoServer.lib.Dbutils;



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
@MultipartConfig
public class Problem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
	//private String saveDirectory = "/Users/tombutterwith/Desktop"; 
	private String SAVE_DIR = "problemImages";
	private int allowedImages = 5;

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

		ProblemModel Problem = new ProblemModel();
		Problem.setDatasource(_ds);
		String args[] = Convertors.SplitRequestPath(request);
		if(args.length > 2){
			if(args[2].equals("delete")){
				String dl = request.getParameter("id");
				Problem.deleteprob(dl);

				response.sendRedirect("/PotatoServer/Problem");

			} else if (args[2].equals("edit")){
				String edit = request.getParameter("id");
				request.setAttribute("problem", ProblemModel.getProblemByID(Integer.parseInt(edit), _ds));

				RequestDispatcher rd = request.getRequestDispatcher("/EditProblem.jsp");
				rd.forward(request, response);
				//do edit stuff
			} 
		} else {
			LinkedList<ProblemStore> psl = Problem.getDES();
			// Get a list of all faults
			/* If we want to forward to a jsp page do this */
			request.setAttribute("Problems", psl); //Set a bean with the list in it
			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowAllProblems.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProblemModel problemModel = new ProblemModel();
		problemModel.setDatasource(_ds);
		String args[] = Convertors.SplitRequestPath(request);
		if(args[2].equals("addSymptoms")){
			String problemId = request.getParameter("id");
			String problemName = request.getParameter("problemName");
			String problemType = request.getParameter("problemType");
			String problemDescription = request.getParameter("problemDescription");

			ProblemStore problem;
			boolean isNew = false;
			if(problemId == null) {
				problem = new ProblemStore();
				isNew = true;
			}
			else 
				problem = ProblemModel.getProblemByID(Integer.parseInt(problemId), _ds);

			problem.setDescription(problemDescription);
			problem.setName(problemName);
			problem.setType(problemType);
			problem.setUpdateDate(new DateTime());

			// gets absolute path of the web application
			String appPath = request.getServletContext().getRealPath("");
			// constructs path of the directory to save uploaded file
			String savePath = appPath + File.separator + SAVE_DIR;

			savePath = "/Users/tombutterwith/Desktop";

			// creates the save directory if it does not exists
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			ArrayList <Part> imagesToUpload = new ArrayList<Part>();
			for(int i = 1; i <= allowedImages; i++) {
				Part part = request.getPart("file" + i);
				try {
					if (part.getSize() > 0)
						imagesToUpload.add(part);
				} catch (NullPointerException e) {
					System.out.println("no file to upload");
				}
			}

			ArrayList<String> fileURLs = new ArrayList<String>();
			int fileCount = 1;
			for (Part part : imagesToUpload) {
				String fileName = problemName.replace(" ", "_") + '_' + problemType + fileCount;

				try {
					part.write(savePath + File.separator + fileName + ".png");
					fileURLs.add(SAVE_DIR + File.separator + fileName + ".png");
				} catch (IOException e) {
					System.out.println("File not found exception");
				}
				fileCount++;
			}


			problemModel.updateProblem(problem, isNew);
			problemModel.updateImageURLs(fileURLs, problemId, problemName);

			SymptomModel symModel = new SymptomModel();
			symModel.setDatasource(_ds);
			LinkedList<SymptomStore> ssl = symModel.getAllSymptoms();
			// Get a list of all faults
			/* If we want to forward to a jsp page do this */
			request.setAttribute("problemName", problemName);
			request.setAttribute("symptoms", ssl); //Set a bean with the list in it
			if(!isNew)
				request.setAttribute("selected", symModel.getAllSymptomsForProblem(Integer.parseInt(problemId)));
			RequestDispatcher rd = request
					.getRequestDispatcher("/AddSymptoms.jsp");
			rd.forward(request, response);
		} else if (args[2].equals("saveSymptoms")) {
			String[] checkedIds = request.getParameterValues("symptom");
			String problemName = request.getParameter("problemName");
			problemModel.addSymptoms(problemName, checkedIds);
			
			LinkedList<ProblemStore> psl = problemModel.getDES();
			// Get a list of all faults
			/* If we want to forward to a jsp page do this */
			request.setAttribute("Problems", psl); //Set a bean with the list in it
			RequestDispatcher rd = request
					.getRequestDispatcher("/ShowAllProblems.jsp");
			rd.forward(request, response);
			
			
		}

	}

}
