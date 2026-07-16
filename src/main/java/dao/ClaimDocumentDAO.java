package dao;

import database.DBConnection;
import model.ClaimDocument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClaimDocumentDAO {

    public boolean saveDocument(
            ClaimDocument document) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO claim_documents(claim_id,document_name,document_url) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    document.getClaimId());

            ps.setString(
                    2,
                    document.getDocumentName());

            ps.setString(
                    3,
                    document.getDocumentUrl());

            return ps.executeUpdate() > 0;

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }
    
 // ===================================================
 // NEW METHOD
 // Get Documents by Claim ID
 // ===================================================

 public ResultSet getDocumentsByClaimId(int claimId) {

     try {

         Connection con =
                 DBConnection.getConnection();

         String sql =
                 "SELECT * FROM claim_documents WHERE claim_id=?";

         PreparedStatement ps =
                 con.prepareStatement(sql);

         ps.setInt(1, claimId);

         return ps.executeQuery();

     }

     catch(Exception e){

         e.printStackTrace();

     }

     return null;

 }

}