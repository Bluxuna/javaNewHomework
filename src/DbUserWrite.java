import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUserWrite implements UserWriteRepository {
    private final DBconnection connection;

    public DbUserWrite(String jdbcUrl, String username, String password) {
        this.connection = new DBconnection(jdbcUrl, username, password);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("მომხმარებელი დამატებულია: " + user);
            } else {
                System.out.println("ჩანაწერის დამატება ვერ მოხერხდა.");
            }

        } catch (SQLException e) {
            System.err.println("SQL შეცდომა (save): " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3, user.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("მომხმარებელი განახლდა: " + user);
            } else {
                System.out.println("მომხმარებელი ვერ მოიძებნა ID: " + user.getId());
            }

        } catch (SQLException e) {
            System.err.println("SQL შეცდომა (update): " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("მომხმარებელი წაიშალა ID: " + id);
            } else {
                System.out.println("მომხმარებელი ვერ მოიძებნა ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("SQL შეცდომა (delete): " + e.getMessage());
        }
    }
}
