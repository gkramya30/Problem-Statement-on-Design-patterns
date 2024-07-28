import java.util.ArrayList;
import java.util.List;

// DatabaseConnection class
class DatabaseConnection {
    private String connectionString;
    private boolean inUse;

    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        this.inUse = false;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public void connect() {
        System.out.println("Connecting to database: " + connectionString);
    }

    public void disconnect() {
        System.out.println("Disconnecting from database: " + connectionString);
    }
}

// DatabaseConnectionPool class
class DatabaseConnectionPool {
    private List<DatabaseConnection> availableConnections = new ArrayList<>();
    private List<DatabaseConnection> usedConnections = new ArrayList<>();
    private String connectionString;

    public DatabaseConnectionPool(String connectionString, int initialSize) {
        this.connectionString = connectionString;
        for (int i = 0; i < initialSize; i++) {
            availableConnections.add(new DatabaseConnection(connectionString));
        }
    }

    public synchronized DatabaseConnection getConnection() {
        if (availableConnections.isEmpty()) {
            System.out.println("No available connections. Creating a new one.");
            availableConnections.add(new DatabaseConnection(connectionString));
        }

        DatabaseConnection connection = availableConnections.remove(availableConnections.size() - 1);
        connection.setInUse(true);
        usedConnections.add(connection);
        connection.connect();
        return connection;
    }

    public synchronized void releaseConnection(DatabaseConnection connection) {
        connection.disconnect();
        connection.setInUse(false);
        usedConnections.remove(connection);
        availableConnections.add(connection);
    }
}

// ObjectPoolPatternDemo class
public class ObjectPoolPatternDemo {
    public static void main(String[] args) {
        DatabaseConnectionPool pool = new DatabaseConnectionPool("jdbc:mysql://localhost:3306/education", 2);

        DatabaseConnection connection1 = pool.getConnection();
        DatabaseConnection connection2 = pool.getConnection();

        pool.releaseConnection(connection1);
        pool.releaseConnection(connection2);

        DatabaseConnection connection3 = pool.getConnection();
        pool.releaseConnection(connection3);
    }
}
