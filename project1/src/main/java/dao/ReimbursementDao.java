package dao;

import java.util.ArrayList;

import model.Reimbursement;
import model.User;

public interface ReimbursementDao {
	
	public ArrayList<Reimbursement> viewPastTicketsByEmployee(int reimbAuthor);
	
	public ArrayList<Reimbursement> viewAllTicketsByManager();

	public boolean createReimbRequest(Reimbursement reimbursement);

	public boolean denyOrApproveTicketByManager(int reimbId, int reimbResolver, int reimbStatusId);

	public User selectUserByName(String userName);
}
