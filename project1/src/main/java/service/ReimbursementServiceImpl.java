package service;

import java.util.ArrayList;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import model.Reimbursement;
import model.User;

public class ReimbursementServiceImpl implements ReimbursementService {

	ReimbursementDao rd = new ReimbursementDaoImpl();

	@Override
	public boolean createReimbRequestS(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return rd.createReimbRequest(reimbursement);
	}

	@Override
	public ArrayList<Reimbursement> viewPastTicketsByEmployeeS(int reimbAuthor) {
		// TODO Auto-generated method stub
		return rd.viewPastTicketsByEmployee(reimbAuthor);
	}

	@Override
	public ArrayList<Reimbursement> viewAllTicketsByManagerS() {
		return rd.viewAllTicketsByManager();
	}

	@Override
	public boolean denyOrApproveTicketByManagerS(int reimbId, int reimbResolver,  int reimbStatusId) {
		return rd.denyOrApproveTicketByManager(reimbId, reimbResolver, reimbStatusId);
	}

	@Override
	public User selectUserByNameS(String userName) {
		return rd.selectUserByName(userName);
	}

}
