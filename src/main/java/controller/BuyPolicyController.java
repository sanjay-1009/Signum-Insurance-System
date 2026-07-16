package controller;

import dao.BuyPolicyDAO;

public class BuyPolicyController {

    BuyPolicyDAO dao = new BuyPolicyDAO();

    public boolean buyPolicy(
            int userId,
            int policyId,
            double premium,
            double maturityAmount) {

        return dao.buyPolicy(
                userId,
                policyId,
                premium,
                maturityAmount);

    }

}