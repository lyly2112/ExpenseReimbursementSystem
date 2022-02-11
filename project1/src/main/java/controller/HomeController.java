package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;

public class HomeController {

	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/resources/html/home.html";

		req.getRequestDispatcher(path).forward(req, resp);
	}

	public static void getCurrentUserFromSession(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("HomeController:getCurrentUserFromSession");
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		System.out.println("currentUser " + currentUser);
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(currentUser));
	}

}
