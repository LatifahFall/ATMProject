package packagee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection conn;
    public DatabaseManager(){
        try {
            // Register the driver (not usually necessary for recent JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ATM";
            String username = "root";
            String password = "";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
    public boolean transferExists(String iden,String pincode) throws Exception{
        //true if it exists and state is up(true), and killtime is yet to come
        //change state to false
        String query = "SELECT * FROM transactions WHERE type='transfer' and iden = '"+iden+"' and pincode = '"+pincode+"' AND state=1";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            //We found a match with state up , il reste a verifier si le killtime n'est pas encore atteint
            if (resultSet.next()) {
                if(Timestamp.valueOf(resultSet.getString("killtime")).getTime()<=System.currentTimeMillis()) {
                    return false;
                }
                else{
                    conn.createStatement().executeUpdate("UPDATE Transactions SET state=0 WHERE type='transfer' and iden = '" + iden + "' and pincode = '" + pincode + "'");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence et validite  du transfer: " + e.getMessage());
        }
        return false;
    }
    public boolean authenticate(int id) throws Exception{
        String query = "SELECT COUNT(*) FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'user ID: " + e.getMessage());
        }
        return false;
    }
    public boolean authenticate(int id,String password) throws Exception{
        String query = "SELECT * FROM Accounts WHERE id = ? AND password = '"+password+"'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getFname(int id) throws Exception{
        //return "Fatima";
        String query = "SELECT fname FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("fname");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du prenom : " + e.getMessage());
        }
        return null;
    }
    public String getLname(int id) throws Exception{
        //return "EL KSAKASS";
        String query = "SELECT lname FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("lname");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nom : " + e.getMessage());
        }
        return null;
    }
    public String getPhone(int id) throws Exception{
        //return "06294134";
        String query = "SELECT phone FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("phone");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du phone : " + e.getMessage());
        }
        return null;
    }
    public String getEmail(int id) throws Exception{
        //return "fatimaelksa@gmail.com";
        String query = "SELECT email FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'email : " + e.getMessage());
        }
        return null;
    }
    public String getCIN(int id) throws Exception{
        //return "EE977665";
        String query = "SELECT cin FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("cin");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de numero de la CIN: " + e.getMessage());
        }
        return null;
    }
    public String getPassword(int id) throws Exception{
        //return "BDHDYEEOEJ";
        String query = "SELECT password FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du password: " + e.getMessage());
        }
        return null;
    }
    public double getBalance(int id) throws Exception{
        //return 142.22;
        String query = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du nom complet: " + e.getMessage());
        }
        return 0.00;
    }
    //Only load last 8 transactions or smth
    public Transaction[] getRecentTransactions(int id) throws Exception{
        Transaction [] trs=new Transaction[0];
        String query = "SELECT * FROM transactions WHERE account=? ORDER BY ttimestamp DESC LIMIT 8";
        Transaction tr;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            int tid,account;
            double amount;
            String type;
            String ttimestamp;
            while (res.next()) {
                System.out.println("HEERE");
                tid=res.getInt("id");
                account = res.getInt("account");
                amount = res.getDouble("amount");
                type=res.getString("type");
                ttimestamp=res.getString("ttimestamp");
                System.out.println("HEERE");
                switch(type){
                    case "deposit":
                        tr=new Deposit(tid,account,amount,Timestamp.valueOf(ttimestamp).getTime());
                        System.out.println("deposit");
                        break;
                    case "withdrawal":
                        tr=new Withdrawal(tid,account,amount,Timestamp.valueOf(ttimestamp).getTime());
                        System.out.println("withdrawal");
                        break;
                    case "transfer":
                        tr=new Transfer(tid,account,amount,Timestamp.valueOf(ttimestamp).getTime(),res.getString("phone"),res.getString("iden"),res.getString("pincode"),Timestamp.valueOf(res.getString("killtime")).getTime());
                        System.out.println("transfer");
                        break;
                    case "topup":
                        tr=new TopUp(tid,account,amount,Timestamp.valueOf(ttimestamp).getTime(),res.getString("operator"),res.getString("phone"));
                        System.out.println("topup");
                        break;
                    default:
                        tr=null;
                        break;
                }
                System.out.println(trs.length);
                Transaction[] temp=new Transaction[(trs.length+1)];
                System.arraycopy(trs,0,temp,0,trs.length);
                temp[trs.length]=tr;
                trs=temp;
            }
        }
        return trs;
    }
    public boolean withdraw(Withdrawal wt) throws Exception{
        //returns a boolean reflecting the sucess or failure of withdrawing,  and does it
        //saves the withdrawal transaction in db and update balance
        if (wt.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        String updateBalanceQuery = "UPDATE Accounts SET balance = balance - ? WHERE id = ?";
        String insertTransactionQuery = "INSERT INTO Transactions VALUES (null,CURRENT_TIMESTAMP,?,?,'withdrawal',NULL,NULL,NULL,NULL,NULL,NULL)";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateBalanceQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionQuery)) {

            conn.setAutoCommit(false);

            updateStmt.setDouble(1, wt.getAmount());
            updateStmt.setInt(2, wt.getAccount());
            if (updateStmt.executeUpdate() <= 0) {
                conn.rollback();
                return false;
            }

            insertStmt.setDouble(1, wt.getAmount());
            insertStmt.setInt(2, wt.getAccount());
            insertStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deposit(Deposit dp) throws Exception{
        //returns a boolean reflecting the sucess or failure of depositing,  and does it
        //saves the deposit transaction in db and update balance
        if (dp.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        String updateBalanceQuery = "UPDATE Accounts SET balance = balance + ? WHERE id = ?";
        String insertTransactionQuery = "INSERT INTO Transactions VALUES (null,CURRENT_TIMESTAMP,?,?,'deposit',NULL,NULL,NULL,NULL,NULL,NULL)";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateBalanceQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionQuery)) {

            conn.setAutoCommit(false);

            updateStmt.setDouble(1, dp.getAmount());
            updateStmt.setInt(2, dp.getAccount());
            if (updateStmt.executeUpdate() <= 0) {
                conn.rollback();
                return false;
            }

            insertStmt.setDouble(1, dp.getAmount());
            insertStmt.setInt(2, dp.getAccount());
            insertStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean transfer(Transfer tf) throws Exception{
        //returns a boolean reflecting the sucess or failure of transfering,  and does it
        //saves the transfer transaction in db and update balance
        if (tf.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant du transfer doit être supérieur à 0.");
        }
        String updateBalanceQuery = "UPDATE Accounts SET balance = balance - ? WHERE id = ?";
        String insertTransactionQuery = "INSERT INTO Transactions VALUES (null,CURRENT_TIMESTAMP,?,?,'transfer',null,?,?,?,?,1)";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateBalanceQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionQuery)) {

            //conn.setAutoCommit(false);

            updateStmt.setDouble(1, tf.getAmount());
            updateStmt.setInt(2, tf.getAccount());
            if (updateStmt.executeUpdate() <= 0) {
                //conn.rollback();
                return false;
            }
            insertStmt.setDouble(1, tf.getAmount());
            insertStmt.setInt(2, tf.getAccount());
            insertStmt.setString(3, tf.getPhone());
            insertStmt.setString(4, tf.getIden());
            insertStmt.setString(5, tf.getPincode());
            insertStmt.setString(6, tf.getSQLKilltime());
            insertStmt.executeUpdate();

            //conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean topup(TopUp tp) throws Exception{
        //returns a boolean reflecting the sucess or failure of topup,  and does it
        //saves the topup transaction in db and update balance
        if (tp.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant de la recharge doit être supérieur à 0.");
        }
        String updateBalanceQuery = "UPDATE Accounts SET balance = balance - ? WHERE id = ?";
        String insertTransactionQuery = "INSERT INTO Transactions VALUES (null,CURRENT_TIMESTAMP,?,?,'topup',?,?,NULL,NULL,NULL,NULL)";

        try (PreparedStatement updateStmt = conn.prepareStatement(updateBalanceQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionQuery)) {

            conn.setAutoCommit(false);

            updateStmt.setDouble(1, tp.getAmount());
            updateStmt.setInt(2, tp.getAccount());
            if (updateStmt.executeUpdate() <= 0) {
                conn.rollback();
                return false;
            }

            insertStmt.setDouble(1, tp.getAmount());
            insertStmt.setInt(2, tp.getAccount());
            insertStmt.setString(3, tp.getOperator());
            insertStmt.setString(4, tp.getPhone());
            insertStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean changePassword(int id,String password) throws Exception{
        String query = "UPDATE Accounts SET password = '"+password+"' WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
