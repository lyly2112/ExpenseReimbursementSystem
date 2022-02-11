package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import controller.ErsController;
import controller.HomeController;
import controller.LoginController;

public class Dispatcher {

	public static void router(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException, ServletException {
		System.out.println(" Dispatcher router");
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());

		switch (req.getRequestURI()) {

		case "/ersapp/forwarding/login":
			System.out.println("login");
			LoginController.login(req, resp);
			break;

		case "/ersapp/forwarding/home":
			System.out.println("home");
			HomeController.home(req, resp);
			break;

		case "/ersapp/json/alltickets":
			ErsController.viewAllTicketsByManagerC(req, resp);
			break;

		case "/ersapp/json/employeetickets":
			ErsController.viewPastTicketsByEmployeeC(req, resp);
			break;
			
		case "/ersapp/json/create":
			System.out.println("dispatcher:create");
			ErsController.createReimbRequestC(req, resp);
			break;
		
		case "/ersapp/json/denyorapprove":
			System.out.println("denyorapprove");
			ErsController.denyOrApproveTicketByManagerC(req, resp);
			break;
			
		case "/ersapp/json/getcurrentuser":
			System.out.println("dispatcher:getcurrentuser");
			HomeController.getCurrentUserFromSession(req, resp);
			break;
			
		default:
			System.out.println("wrong url");
			req.getRequestDispatcher("/resources/html/badlogin.html").forward(req, resp);
			return;
		}
	}
}
