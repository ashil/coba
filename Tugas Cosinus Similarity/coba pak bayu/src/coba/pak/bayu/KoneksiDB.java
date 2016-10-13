/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coba.pak.bayu;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arrival
 */
public class KoneksiDB {
    public static java.sql.Connection conn;
    public static Statement cn;
    public static ResultSet rs;

    public static void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coba", "root", "");
            cn = conn.createStatement();
            System.out.println("Koneksi Sukses");

        } catch (Exception e) {
            System.out.println("Koneksi Gagal");
            System.out.println(e.getMessage());
        }
    }
    
    public static Connection setKoneksi() {
        String konString = "jdbc:mysql://localhost:3306/coba";
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(konString,"root","");
            //System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Koneksi Gagal");
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Koneksi Gagal");
        }
        return koneksi;
    }
    
    public static int execute(String SQL) {
        int status = 0;
        Connection koneksi = setKoneksi();
        try {
            Statement st = koneksi.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static ResultSet executeQuery(String SQL) {
        ResultSet rs = null;
        Connection koneksi = setKoneksi();
        try {
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
