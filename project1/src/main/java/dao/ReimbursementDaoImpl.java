package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Reimbursement;
import model.User;

public class ReimbursementDaoImpl implements ReimbursementDao {

//	public static void main(String[] args) {
//		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
//		System.out.println(rd.selectUserByName("emp1"));
		
//		System.out.println(rd.viewAllTicketsByManager());
//		
//		System.out.println(rd.viewPastTicketsByEmployee(2));
//		System.out.println(rd.createReimbRequest(new Reimbursement((float) 500, "desc", 1, 1)));
//		System.out.println(rd.createReimbRequest(new Reimbursement()));

//		System.out.println(rd.viewPastTicketsByEmployee(2));
//		}

	@Override
	public boolean createReimbRequest(Reimbursement reimbursement) {
		System.out.println("in createReimbRequest dao");
		try (Connection conn = CustomConnection.getConnection()) {
			String sql = "INSERT INTO ers_reimbursment VALUES (DEFAULT, ?, CURRENT_TIMESTAMP, DEFAULT, ?, DEFAULT, ?, DEFAULT, 1, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setFloat(1, reimbursement.getReimbAmount());
			ps.setString(2, reimbursement.getReimbDescription());
			ps.setInt(3, reimbursement.getReimbAuthor());
			ps.setInt(4, reimbursement.getReimbTypeId());

			int numOfRowsChange = ps.executeUpdate();
			System.out.println("numOfRowsChange" + numOfRowsChange);
			// to the DB
			if (numOfRowsChange == 0)
				return false;
			else {
				System.out.println("Reimbursment has been registered. ");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ArrayList<Reimbursement> viewPastTicketsByEmployee(int reimbAuthor) {

		ArrayList<Reimbursement> pastEmployeeTickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String sql = "SELECT * FROM ers_reimbursment WHERE reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbAuthor);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pastEmployeeTickets.add(new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pastEmployeeTickets;
	}

	@Override
	public ArrayList<Reimbursement> viewAllTicketsByManager() {

		ArrayList<Reimbursement> allTickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String sql = "SELECT * FROM ers_reimbursment";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getString("reimb_submitted"), rs.getString("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));

				allTickets.add(reimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allTickets;
	}

	public boolean denyOrApproveTicketByManager(int reimbId, int reimbResolver, int reimbStatusId) {

		try (Connection conn = CustomConnection.getConnection()) {

			String sql = "UPDATE ers_reimbursment SET reimb_status_id = ?, reimb_resolved=CURRENT_TIMESTAMP, reimb_resolver=? WHERE reimb_id = ? and reimb_status_id=1";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbStatusId);
			ps.setInt(2, reimbResolver);
			ps.setInt(3, reimbId);

			int numOfRowsChange = ps.executeUpdate();

			System.out.println("numOfRowsChange " + numOfRowsChange);
			// to the DB
			if (numOfRowsChange == 0)
				return false;
			else if (reimbStatusId == 2) {
				System.out.println("Reimbursment has been approved. ");
				return true;
			} else {
				System.out.println("Reimbursment has been denied. ");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User selectUserByName(String userName) {
		try (Connection conn = CustomConnection.getConnection()) {

			String sql = "SELECT * FROM ers_users WHERE ers_username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);

			ResultSet rs = ps.executeQuery();

			User user = null;
			
			if (rs.next()) 
			
			{
					user = new User(rs.getInt("ers_users_id"), rs.getString("ers_username"), rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), rs.getInt("user_role_id"));
					System.out.println("user "+ user);
				} 
			
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}