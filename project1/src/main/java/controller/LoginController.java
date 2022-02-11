package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReimbursementService;
import service.ReimbursementServiceImpl;

public class LoginController {

	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("in login controller");
		String path = null;
		// check if user using correct method
		if (!req.getMethod().equals("POST")) {
			path = "/login.html";
			req.getRequestDispatcher(path).forward(req, resp);
			return;
		}

		// getting data from input form
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		
		ReimbursementService rs = new ReimbursementServiceImpl();
		req.getSession().setAttribute("currentUser", rs.selectUserByNameS(username));
//		System.out.println( rs.selectUserByNameS(username));
		
		
		// check credentials
		if (!( username.equals(rs.selectUserByNameS(username).getUserName()) && password.equals(rs.selectUserByNameS(username).getUserPassword()))) {
			System.out.println("wrong credentials");
			path = "/forwarding/incorrectcredentials";
			req.getRequestDispatcher(path).forward(req, resp);
			return;
		} else {
			System.out.println("correct credentials");

//			ReimbursementService rs = new ReimbursementServiceImpl();
//			req.getSession().setAttribute("currentUser", rs.selectUserByNameS(username));
//			System.out.println( rs.selectUserByNameS(username));
			System.out.println( req.getSession().getAttribute("currentUser"));

			System.out.println(username);
			path = "/forwarding/home";
			req.getRequestDispatcher(path).forward(req, resp);
			return;
		}

	}

}
