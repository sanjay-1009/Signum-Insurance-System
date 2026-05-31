package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DashboardDAO {

    public HashMap<String, Integer> getStatistics() {

        HashMap<String, Integer> stats =
                new HashMap<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            stats.put(
                    "totalPolicies",
                    getCount(con,
                    "SELECT COUNT(*) FROM policies"));

            stats.put(
                    "totalClaims",
                    getCount(con,
                    "SELECT COUNT(*) FROM claims"));

            stats.put(
                    "approvedClaims",
                    getCount(con,
                    "SELECT COUNT(*) FROM claims WHERE status='APPROVED'"));

            stats.put(
                    "rejectedClaims",
                    getCount(con,
                    "SELECT COUNT(*) FROM claims WHERE status='REJECTED'"));

            stats.put(
                    "pendingClaims",
                    getCount(con,
                    "SELECT COUNT(*) FROM claims WHERE status='PENDING'"));

        } catch(Exception e) {
            e.printStackTrace();
        }

        return stats;
    }

    private int getCount(
            Connection con,
            String query) throws Exception {

        PreparedStatement ps =
                con.prepareStatement(query);

        ResultSet rs =
                ps.executeQuery();

        if(rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }
}