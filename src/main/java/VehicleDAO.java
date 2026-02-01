package kz.alnur.vehicle_rental_api;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public List<Vehicle> findAll() throws SQLException {
        String sql = "SELECT id, model, price_per_day FROM vehicle ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Vehicle> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Vehicle(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getDouble("price_per_day")
                ));
            }
            return list;
        }
    }

    public Vehicle create(Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicle(model, price_per_day) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getModel());
            stmt.setDouble(2, v.getPricePerDay());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) v.setId(rs.getInt(1));
            }
            return v;
        }
    }

    public Vehicle findById(int id) throws SQLException {
        String sql = "SELECT id, model, price_per_day FROM vehicle WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                return new Vehicle(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getDouble("price_per_day")
                );
            }
        }
    }

    public boolean updatePrice(int id, double newPrice) throws SQLException {
        String sql = "UPDATE vehicle SET price_per_day = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
