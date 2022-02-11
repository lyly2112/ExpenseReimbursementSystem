package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Reimbursement;
import model.User;
import service.ReimbursementService;
import service.ReimbursementServiceImpl;

public class ErsController {

	private static ReimbursementService rs = new ReimbursementServiceImpl();

	public static void viewAllTicketsByManagerC(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		System.out.println("reading all tickets");

		ArrayList<Reimbursement> tickets = rs.viewAllTicketsByManagerS();

		resp.getWriter().write(new ObjectMapper().writeValueAsString(tickets));
	}

	public static void viewPastTicketsByEmployeeC(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		System.out.println("reading tickets by employee");

		User currentUser = (User) req.getSession().getAttribute("currentUser");

		ArrayList<Reimbursement> employeeTickets = rs.viewPastTicketsByEmployeeS(currentUser.getUserId());
		System.out.println("erscontroller employeeTickets: " + employeeTickets);
		resp.getWriter().write(new ObjectMapper().writeValueAsString(employeeTickets));
	}

	public static void createReimbRequestC(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		System.out.println("creating tickets by employee");

		User currentUser = (User) req.getSession().getAttribute("currentUser");
		System.out.println("currentUser "+ currentUser.toString());
		Reimbursement reimbursement = new Reimbursement(Float.parseFloat(req.getParameter("reimbAmount")),
				req.getParameter("reimbDescription"), currentUser.getUserId(),
				Integer.parseInt(req.getParameter("reimbTypeId")));
		rs.createReimbRequestS(reimbursement);
		resp.getWriter().println("your reimbursement ticket has been created");
	}

	public static void denyOrApproveTicketByManagerC(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("ers controller: deny or approve method");

		User currentUser = (User) req.getSession().getAttribute("currentUser");

		rs.denyOrApproveTicketByManagerS(Integer.parseInt(req.getParameter("reimbId")), currentUser.getUserRoleId(), 
				Integer.parseInt(req.getParameter("reimbStatusId")));

		// need to fix this to validate the updated status
//		if (Integer.parseInt(req.getParameter("reimbStatusId")) == 2) {
//			resp.getWriter().println("your reimbursement ticket has been approved");
//		} else {
//			resp.getWriter().println("your reimbursement ticket has been denied");
//		}
	}
}
