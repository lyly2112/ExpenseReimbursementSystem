package service;

import java.util.ArrayList;

import model.Reimbursement;
import model.User;

public interface ReimbursementService {

	public boolean createReimbRequestS(Reimbursement reimbursement);
	
	public ArrayList<Reimbursement> viewPastTicketsByEmployeeS(int reimbAuthor);
	
	public ArrayList<Reimbursement> viewAllTicketsByManagerS();

	public boolean denyOrApproveTicketByManagerS(int reimbId, int reimbResolver, int reimbStatusId);
	
	public User selectUserByNameS(String userName);
}
