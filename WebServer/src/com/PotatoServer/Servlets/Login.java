package com.PotatoServer.Servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.PotatoServer.Models.LoginModel;
import com.PotatoServer.Stores.SessionBean;
import com.PotatoServer.lib.Dbutils;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = { 
		"/Login", 
		"/Login/*"
}, 
initParams = { 
	@WebInitParam(name = "data-source", value = "jdbc/potatodb")
})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginModel lm = new LoginModel();
		lm.setDatasource(_ds);
		
		String password = request.getParameter("password");
		
		String username = request.getParameter("username");
		try {
			password = sha1(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SHA1 missing");
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		if(lm.checkLogin(username, password)) {
			SessionBean sb = new SessionBean();
			sb.setUsername(username);
			session.setAttribute("sessionBean", sb);
			response.sendRedirect("Management");
		} else {
			response.sendRedirect("index.jsp?r=incorrect");
		}
		
	}
	
	static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }

}
