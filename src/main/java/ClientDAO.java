package kz.alnur.vehicle_rental_api;
import kz.alnur.vehicle_rental_api.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public List<Client> findAll() throws SQLException {
        String sql = "SELECT id, name FROM client ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Client> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
            return list;
        }
    }

    public Client create(Client c) throws SQLException {
        String sql = "INSERT INTO client(name) VALUES (?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getName());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
            return c;
        }
    }

    public Client findById(int id) throws SQLException {
        String sql = "SELECT id, name FROM client WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                return new Client(rs.getInt("id"), rs.getString("name"));
            }
        }
    }

    public boolean updateName(int id, String newName) throws SQLException {
        String sql = "UPDATE client SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
