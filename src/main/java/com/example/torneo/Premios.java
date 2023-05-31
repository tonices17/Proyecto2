package com.example.torneo;

import java.io.IOException;
import java.sql.*;

public class Premios {

    public static void insertarpremiosA() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select nombre, fide, origen, hotel from optaA");
        while (rs.next()) {
            Statement stm2 = cnx.createStatement();
            ResultSet rs2 = stm2.executeQuery("select id, tipo_premio, cantidad from premios where torneo = 'A' order by cantidad desc, case when tipo_premio = 'General' THEN 1 WHEN tipo_premio = 'Comunidad Valenciana' THEN 2 WHEN tipo_premio = 'Sub2400' THEN 3 WHEN tipo_premio = 'Sub2200' THEN 4 WHEN tipo_premio = 'Alojamiento' THEN 5 end, tipo_premio");
            String nombreb = rs.getString(1);
            int fideb = rs.getInt(2);
            String origenb = rs.getString(3);
            String hotelb = rs.getString(4);
            while (rs2.next()) {
                int id = rs2.getInt(1);
                String tipo_premio = rs2.getString(2);
                int cantidad = rs2.getInt(3);
                if (!origenb.equals("Comunidad Valenciana") && tipo_premio.equals("Comunidad Valenciana")) {
                } else if (tipo_premio.equals("Sub2400")) {
                    if (fideb <= 2400) {
                        PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                        stm4.setInt(1, id);
                        stm4.setString(2, nombreb);
                        stm4.setString(3, tipo_premio);
                        stm4.setInt(4, cantidad);
                        stm4.execute();
                    }
                    else if (fideb > 2400){
                    }
                } else if (tipo_premio.equals("Sub2200")) {
                    if (fideb <= 2200) {
                        PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                        stm4.setInt(1, id);
                        stm4.setString(2, nombreb);
                        stm4.setString(3, tipo_premio);
                        stm4.setInt(4, cantidad);
                        stm4.execute();
                    }
                    else if (fideb > 2200){
                    }
                } else if (!hotelb.equals("Si") && tipo_premio.equals("Alojamiento")) {
                } else {
                    PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                    stm4.setInt(1, id);
                    stm4.setString(2, nombreb);
                    stm4.setString(3, tipo_premio);
                    stm4.setInt(4, cantidad);
                    stm4.execute();
                }
            }
        }
    }
    public static ResultSet mostrarPremiosA() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select distinct nombre from premiosfinales");
        while (rs.next()) {
            PreparedStatement smt2 = cnx.prepareStatement("select * from premiosfinales where nombre = ? order by cantidad desc, case when tipo_premio = 'General' THEN 1 WHEN tipo_premio = 'Comunidad Valenciana' THEN 2 WHEN tipo_premio = 'Sub1800' THEN 3 WHEN tipo_premio = 'Sub1600' THEN 4 WHEN tipo_premio = 'Sub1400' THEN 5 WHEN tipo_premio = 'Alojamiento' then 6 end, tipo_premio");
            String nombre = rs.getString(1);
            smt2.setString(1,nombre);
            ResultSet rs2 = smt2.executeQuery();
            if (rs2.next()) {
                int idf = rs2.getInt(1);
                String nombref = rs2.getString(2);
                //String tipof = rs2.getString(3);
                //int cantidadf = rs2.getInt(4);
                String query = "delete from premiosfinales where nombre = ?";
                PreparedStatement smt3 = cnx.prepareStatement(query);
                smt3.setString(1, nombref);
                smt3.execute();
                String query2 = "delete from premiosfinales where id = ?";
                PreparedStatement smt4 = cnx.prepareStatement(query2);
                smt4.setInt(1, idf);
                smt4.execute();
                PreparedStatement smt5 = cnx.prepareStatement("update premios set ganador = ? where id = ? and torneo = 'A'");
                smt5.setString(1, nombref);
                smt5.setInt(2, idf);
                smt5.executeUpdate();
                //System.out.println(nombref + " " + tipof + " " + cantidadf);
                mostrarPremiosA();
            }
        }
        return stm.executeQuery("select * from premios WHERE torneo='A' order by id, cantidad desc");
    }
    public static void insertarpremiosB() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select nombre, fide, origen, hotel from optaB");
        while (rs.next()) {
            Statement stm2 = cnx.createStatement();
            ResultSet rs2 = stm2.executeQuery("select id, tipo_premio, cantidad from premios where torneo = 'B' order by cantidad desc, case when tipo_premio = 'General' THEN 1 WHEN tipo_premio = 'Comunidad Valenciana' THEN 2 WHEN tipo_premio = 'Sub1800' THEN 3 WHEN tipo_premio = 'Sub1600' THEN 4 WHEN tipo_premio = 'Sub1400' THEN 5 WHEN tipo_premio = 'Alojamiento' then 6 end, tipo_premio");
            String nombreb = rs.getString(1);
            int fideb = rs.getInt(2);
            String origenb = rs.getString(3);
            String hotelb = rs.getString(4);
            while (rs2.next()) {
                int id = rs2.getInt(1);
                String tipo_premio = rs2.getString(2);
                int cantidad = rs2.getInt(3);
                if (!origenb.equals("Comunidad Valenciana") && tipo_premio.equals("Comunidad Valenciana")) {
                } else if (tipo_premio.equals("Sub1800")) {
                    if (fideb <= 1800) {
                        PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                        stm4.setInt(1, id);
                        stm4.setString(2, nombreb);
                        stm4.setString(3, tipo_premio);
                        stm4.setInt(4, cantidad);
                        stm4.execute();
                    }
                    else if (fideb > 1800){
                    }
                } else if (tipo_premio.equals("Sub1600")) {
                    if (fideb <= 1600) {
                        PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                        stm4.setInt(1, id);
                        stm4.setString(2, nombreb);
                        stm4.setString(3, tipo_premio);
                        stm4.setInt(4, cantidad);
                        stm4.execute();
                    }
                    else if (fideb > 1600){
                    }
                } else if (tipo_premio.equals("Sub1400")) {
                    if (fideb <= 1400) {
                        PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                        stm4.setInt(1, id);
                        stm4.setString(2, nombreb);
                        stm4.setString(3, tipo_premio);
                        stm4.setInt(4, cantidad);
                        stm4.execute();
                    } else if (fideb > 1400){
                    }
                } else if (!hotelb.equals("Si") && tipo_premio.equals("Alojamiento")) {
                } else {
                    PreparedStatement stm4 = cnx.prepareStatement("insert into premiosfinales (id, nombre, tipo_premio, cantidad) values (?, ?, ?, ?)");
                    stm4.setInt(1, id);
                    stm4.setString(2, nombreb);
                    stm4.setString(3, tipo_premio);
                    stm4.setInt(4, cantidad);
                    stm4.execute();
                }
            }
        }
    }
    public static ResultSet mostrarPremiosB() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select distinct nombre from premiosfinales");
        while (rs.next()) {
            PreparedStatement smt2 = cnx.prepareStatement("select * from premiosfinales where nombre = ? order by cantidad desc, case when tipo_premio = 'General' THEN 1 WHEN tipo_premio = 'Comunidad Valenciana' THEN 2 WHEN tipo_premio = 'Sub1800' THEN 3 WHEN tipo_premio = 'Sub1600' THEN 4 WHEN tipo_premio = 'Sub1400' THEN 5 WHEN tipo_premio = 'Alojamiento' then 6 end, tipo_premio");
            String nombre = rs.getString(1);
            smt2.setString(1,nombre);
            ResultSet rs2 = smt2.executeQuery();
            if (rs2.next()) {
                int idf = rs2.getInt(1);
                String nombref = rs2.getString(2);
                //String tipof = rs2.getString(3);
                //int cantidadf = rs2.getInt(4);
                String query = "delete from premiosfinales where nombre = ?";
                PreparedStatement smt3 = cnx.prepareStatement(query);
                smt3.setString(1, nombref);
                smt3.execute();
                String query2 = "delete from premiosfinales where id = ?";
                PreparedStatement smt4 = cnx.prepareStatement(query2);
                smt4.setInt(1, idf);
                smt4.execute();
                PreparedStatement smt5 = cnx.prepareStatement("update premios set ganador = ? where id = ? and torneo = 'B'");
                smt5.setString(1, nombref);
                smt5.setInt(2, idf);
                smt5.executeUpdate();
                //System.out.println(nombref + " " + tipof + " " + cantidadf);
                mostrarPremiosB();
            }
        }
        return stm.executeQuery("select * from premios WHERE torneo='B' order by id, cantidad desc");
    }
}