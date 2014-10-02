package com.PotatoServer.Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.Models.UpdatePhoneModel;
import com.PotatoServer.lib.Convertors;
import com.PotatoServer.lib.Dbutils;

/**
 * Servlet implementation class UpdatePhone
 */
@WebServlet(urlPatterns = { 
				"/UpdatePhone", 
				"/UpdatePhone/*"
		}, 
		initParams = { 
			@WebInitParam(name = "data-source", value = "jdbc/potatodb")
		})
public class UpdatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePhone() {
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
		UpdatePhoneModel uPM = new UpdatePhoneModel();
		uPM.setDatasource(_ds);
		
		String args[] = Convertors.SplitRequestPath(request);
		if(args.length > 2){
			if(args[2].equals("image"))
			{
				String appPath = request.getServletContext().getRealPath("");
				String location = args[3] + File.separator + args[4];
				// reads input file from an absolute path
		        String filePath = appPath + File.separator + location;
		        File downloadFile = new File(filePath);
		        FileInputStream inStream = new FileInputStream(downloadFile);
		         
		        // obtains ServletContext
		        ServletContext context = request.getServletContext();
		         
		        // gets MIME type of the file
		        String mimeType = context.getMimeType(filePath);
		        if (mimeType == null) {        
		            // set to binary type if MIME mapping not found
		            mimeType = "application/octet-stream";
		        }
		        System.out.println("MIME type: " + mimeType);
		         
		        // modifies response
		        response.setContentType(mimeType);
		        response.setContentLength((int) downloadFile.length());
		         
		        // forces download
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		        response.setHeader(headerKey, headerValue);
		         
		        // obtains response's output stream
		        OutputStream outStream = response.getOutputStream();
		         
		        byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		         
		        inStream.close();
		        outStream.close(); 
			}
		} else {
			String lastUpdate = request.getParameter("t");

			ServletOutputStream out = response.getOutputStream();

			if(lastUpdate == null) { lastUpdate = "00000000000000";}

			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<data>");
			uPM.getUpdatedProblems(lastUpdate, out);
			uPM.getUpdatedSymptoms(lastUpdate, out);
			uPM.getUpdatedImages(lastUpdate, out);
			out.println("</data>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
