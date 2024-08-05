package com.danilkompaniets.demo3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
    Connection con = null;

    public AlienRepository() {
        String url = "jdbc:mysql://localhost:3306/restDb";
        String user = "root";
        String password = "Danone0502603734!";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw new RuntimeException("Failed to connect to the database", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();
        String sql = "SELECT * FROM aliens";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Alien a = new Alien();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setPoints(rs.getInt("points"));
                aliens.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching aliens", e);
        }

        return aliens;
    }

    public Alien getAlien(int id) {
        Alien a = new Alien();
        String sql = "SELECT * FROM aliens WHERE id=?";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    a.setId(rs.getInt("id"));
                    a.setName(rs.getString("name"));
                    a.setPoints(rs.getInt("points"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching alien with id: " + id, e);
        }

        return a;
    }

    public void deleteAlien(int id) {
        String sql = "DELETE FROM aliens WHERE id=?";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting alien with id: " + id, e);
        }
    }

    public void addAlien(Alien alien) {
        String sql = "INSERT INTO aliens (id, name, points) VALUES (?, ?, ?)";

        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, alien.getId());
            st.setString(2, alien.getName());
            st.setInt(3, alien.getPoints());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding alien", e);
        }
    }
    public void updateAlien(Alien alien, int id) {
        String sql = "update aliens set name=?, points=?, where id=" + id;

        try  {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, alien.getName());
            st.setInt(2, alien.getPoints());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding alien", e);
        }
    }
}
