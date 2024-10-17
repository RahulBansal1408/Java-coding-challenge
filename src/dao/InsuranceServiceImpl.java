package dao;

import entity.Policy;
import exception.PolicyNotFoundException;
import JDBC.DatabaseConnection;  // Updated import

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImpl implements IPolicyService {

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policy (policyId, policyName, policyDetails) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();  // Updated reference
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, policy.getPolicyId());
            pstmt.setString(2, policy.getPolicyName());
            pstmt.setString(3, policy.getPolicyDetails());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error creating policy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String query = "SELECT * FROM policy WHERE policyId = ?";
        try (Connection conn = DatabaseConnection.getConnection();  // Updated reference
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, policyId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policyId"));
                policy.setPolicyName(rs.getString("policyName"));
                policy.setPolicyDetails(rs.getString("policyDetails"));
                return policy;
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving policy: " + e.getMessage());
            throw new PolicyNotFoundException("Unable to retrieve policy due to database error.");
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM policy";

        try (Connection conn = DatabaseConnection.getConnection();  // Updated reference
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policyId"));
                policy.setPolicyName(rs.getString("policyName"));
                policy.setPolicyDetails(rs.getString("policyDetails"));
                policies.add(policy);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving all policies: " + e.getMessage());
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policy SET policyName = ?, policyDetails = ? WHERE policyId = ?";
        try (Connection conn = DatabaseConnection.getConnection();  // Updated reference
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyName());
            pstmt.setString(2, policy.getPolicyDetails());
            pstmt.setInt(3, policy.getPolicyId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating policy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
        String query = "DELETE FROM policy WHERE policyId = ?";
        try (Connection conn = DatabaseConnection.getConnection();  // Updated reference
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, policyId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting policy: " + e.getMessage());
            throw new PolicyNotFoundException("Unable to delete policy due to database error.");
        }
    }
}
