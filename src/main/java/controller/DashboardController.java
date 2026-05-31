package controller;

import dao.DashboardDAO;

import java.util.HashMap;

public class DashboardController {

    DashboardDAO dao =
            new DashboardDAO();

    public HashMap<String, Integer>
    getDashboardData() {

        return dao.getStatistics();
    }
}