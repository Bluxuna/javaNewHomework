public class Main {
    public static void main(String[] args) {
        // Database credentials and URL (modify as needed)
        String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
        String username = "test";
        String password = "1234";

        // Create repository instance
        UserWriteRepository userRepo = new DbUserWrite(jdbcUrl, username, password);

        // Create test users
        User user1 = new User(1L, "giorgi", "giorgi@example.com");
        User user2 = new User(2L, "gio", "gio@example.com");

        // Save
        userRepo.save(user1);
        userRepo.save(user2);

        // Update
        user1.setName("giorgi gabunia");
        user1.setEmail("gabunia@example.com");
        userRepo.update(user1);

        // Delete
        userRepo.delete(2L);
    }
}
