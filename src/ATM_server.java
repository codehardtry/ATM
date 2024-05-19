import java.io.*;
import java.net.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATM_server {
    private static final String LOG_FILE_PATH = "ATM_record.txt";
    public static void main(String[] args) {
        try {
            // 连接到数据库
            String url = "jdbc:mysql://localhost:3306/atm? useSSL=true";
            String user = "root";
            String pass = "scj18360290868";

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                // 创建表格
                String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "username VARCHAR(50),"
                        + "password VARCHAR(50),"
                        + "balance INT"
                        + ")";
                try (Statement statement = conn.createStatement()) {
                    statement.execute(createTableSQL);
                }
            }

            // 启动服务器
            ServerSocket serverSocket = new ServerSocket(2525);
            System.out.println("ATM Server started. Listening on port 2525...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream())
            ) {
                String response;
                String username = null;
                String password = null;

                while (true) {
                    String request = inFromClient.readLine();
                    System.out.println(request);

                    if (request.equals("BYE")) {
                        outToClient.writeBytes("BYE" + '\n');
                        writeToLog("[" + username + "] BYE command received at " + getCurrentTime());
                    }

                    if (request.startsWith("HELO")) {
                        username = request.substring(5);
                        System.out.println(username);
                        // 检查用户ID是否存在
                        if (checkAccountExists(username)) {
                            response = "500 AUTH REQUIRE";
                        } else {
                            response = "401 ERROR!";
                        }
                        outToClient.writeBytes(response + '\n');
                        writeToLog("[" + username + "] HELO command received at " + getCurrentTime());
                    } else if (request.startsWith("PASS")) {
                        password = request.substring(5);
                        System.out.println(password);
                        // 检查密码是否正确
                        if (authenticate(username, password)) {
                            response = "525 OK!";
                        } else {
                            response = "401 ERROR!";
                        }
                        outToClient.writeBytes(response + '\n');
                        writeToLog("[" + username + "] PASS command received at " + getCurrentTime() + ". Authentication status: " + (response.equals("525") ? "Successful" : "Failed"));
                    } else if (request.startsWith("WDRA")) {
                        int amount = Integer.parseInt(request.substring(5));
                        boolean success = withdraw(username, amount);
                        if (success) {
                            response = "525 OK!"; // 取款成功
                        } else {
                            response = "401 ERROR!"; // 取款失败
                        }
                        outToClient.writeBytes(response + '\n');
                        writeToLog("[" + username + "] WDRA command received at " + getCurrentTime() + ". Withdrawal amount: " + amount + ". Status: " + (success ? "Successful" : "Failed"));
                    } else if (request.equals("BALA")) {
                        int balance = getBalance(username); // 传递正确的用户名参数
                        outToClient.writeBytes("AMNT:" +  balance +'\n');
                        writeToLog("[" + username + "] BALA command received at " + getCurrentTime() + ". Balance inquiry result: " + balance);
                    }
                }
            } catch (IOException | SQLException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        // 验证账号是否存在
        private boolean checkAccountExists(String username) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/atm? useSSL=true";
            String user = "root";
            String pass = "scj18360290868";


            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts WHERE username=?")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }

        // 验证账号密码
        private boolean authenticate(String username, String password) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/atm? useSSL=true";
            String user = "root";
            String pass = "scj18360290868";


            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts WHERE username=? AND password=?")) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }

        // 查询余额
        private int getBalance(String username) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/atm? useSSL=true";
            String user = "root";
            String pass = "scj18360290868";


            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement statement = conn.prepareStatement("SELECT balance FROM accounts WHERE username=?")) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("balance");
                    }
                }
            }
            return 0; // 如果找不到账户，则返回0余额
        }

        // 取款操作
        private boolean withdraw(String username, int amount) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/atm? useSSL=true";
            String user = "root";
            String pass = "scj18360290868";


            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                // 查询当前余额
                int balance = getBalance(username);
                if (balance >= amount) {
                    // 如果余额充足，执行取款操作
                    balance -= amount;
                    // 更新数据库中的余额信息
                    try (PreparedStatement statement = conn.prepareStatement("UPDATE accounts SET balance=? WHERE username=?")) {
                        statement.setInt(1, balance);
                        statement.setString(2, username);
                        statement.executeUpdate();
                    }
                    return true; // 取款成功
                } else {
                    return false; // 余额不足，取款失败
                }
            }
        }
        // 写入日志
        // 写入日志
        private void writeToLog(String logMessage) {
            try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
                writer.write(logMessage + "\n");
            } catch (IOException e) {
                System.err.println("Error writing to log file: " + e.getMessage());
            }
        }

        // 获取当前时间的字符串表示
        private String getCurrentTime() {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return currentTime.format(formatter);
        }
    }
}



