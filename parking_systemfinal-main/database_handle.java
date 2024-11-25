import com.sun.source.tree.BreakTree;

import java.net.SocketTimeoutException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class database_handle {

    // JDBC URL, username, and password of SQL Server
    private static final String JDBC_URL = "jdbc:sqlserver://DESKTOP-FCEDQJG:1433;databaseName=parking_system;encrypt=false;trustServerCertificate=true";
    private static final String USER = "alialkady";
    private static final String PASSWORD = "Aa22540444";

    //assigne slot by GAzar
    public static int assignSlot(String plateNumber) {
        int availableSlot = 0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {

            String query = "SELECT spot FROM spots WHERE spot_free = 'free'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {


                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    availableSlot = resultSet.getInt("spot");
                }
                return availableSlot;
            }
        } catch (SQLException e) {
            return 0;
        }


    }

    //assign slot to customer
    public static String assignSlotToCustomer(int slot, String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "UPDATE customers set slot = ? where entry_id =?  Update spots set spot_free = 'notFree' where spot= ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, slot);
                preparedStatement.setString(2, id);
                preparedStatement.setInt(3, slot);

                preparedStatement.executeUpdate();

                return "slot is assigned";
            }
        } catch (SQLException e) {
            return "slot didn't assign";
        }
    }

    // insert methods
    public static String insertOperatorData(String name, String pass, int shift) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO operator (username, password,shift_time) VALUES (?, ?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);
                preparedStatement.setInt(3, shift);
                preparedStatement.executeUpdate();

                return "Data inserted successfully.";
            }
        } catch (SQLException e) {
            return "Data couldn't be inserted";
        }
    }

    public static String insertCustomerData(String entry_id, String plate_number) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {


            String insertQuery = "INSERT INTO customers (entry_id, plate_number, transaction_date) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, entry_id);
                preparedStatement.setString(2, plate_number);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.executeUpdate();

                return "Data inserted successfully.";
            }
        } catch (SQLException e) {
            return "Data couldn't be inserted.";
        }
    }

    public static String insertSpot(int spot, String spot_free) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO spots (spot, spot_free) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, spot);
                preparedStatement.setString(2, spot_free);
                preparedStatement.executeUpdate();

                return "spot inserted successfully.";
            }
        } catch (SQLException e) {
            return "spot couldn't be inserted";
        }
    }



    //retrieve method
    public static String retrieveData(String table) {
        StringBuilder resultBuilder = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM " + table;

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    if (table.equals("operator")) {
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        int shift = resultSet.getInt("shift_time");
                        resultBuilder.append("username: ").append(username).append('\n')
                                .append("password: ").append(password).append('\n')
                                .append("shift: ").append(shift).append('\n').append("====== \n");
                    } else if (table.equals("customers")) {
                        String entry_id = resultSet.getString("entry_id");
                        String plate_number = resultSet.getString("plate_number");
                        Timestamp timestamp = resultSet.getTimestamp("transaction_date");
                        int slot = resultSet.getInt("slot");
                        Timestamp exitTransaction = resultSet.getTimestamp("exit_transaction");
                        double payment = resultSet.getDouble("customer_payment");

                        resultBuilder.append("entry id: ").append(entry_id).append('\n')
                                .append("plate number: ").append(plate_number).append('\n')
                                .append("transaction dateTime: ").append(timestamp).append("\n")
                                .append("slot: ").append(slot).append("\n")
                                .append("Exit transaction dateTime: ").append(exitTransaction).append("\n")
                                .append("payment: ").append(payment).append('\n').append("====================================").append("\n");
                    } else if (table.equals("spots")) {
                        int spots = resultSet.getInt("spot");
                        String spot_free = resultSet.getString("spot_free");
                        resultBuilder.append("spot number: ").append(spots).append(" is ").append(spot_free).append('\n');
                    } else if (table.equals("payment")) {
                        int shift = resultSet.getInt("shift_order");
                        double shift_payment = resultSet.getDouble("shifts_payment");
                        resultBuilder.append("shift ").append(shift).append(" has payment: ").append(shift_payment).append('\n').append("=====\n");
                    }
                }
            }
        } catch (SQLException e) {
            return "data couldn't be retrieved";
        }

        return resultBuilder.toString();
    }


    public static String updateCustomerId(String entry_id, String new_id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE customers SET entry_id = ? WHERE entry_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, new_id);
                preparedStatement.setString(2, entry_id);
                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0) {
                    return "Data updated successfully.";
                }
                else{
                    return "No matched data";
                }
            }
        } catch (SQLException e) {
            return "Data couldn't update.";
        }
    }

    public static String updateOperatorUser(String username, String newUser) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE operator SET username = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newUser);
                preparedStatement.setString(2, username);
                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0) {
                    return "Data updated successfully.";
                }
                else{
                    return "data couldn't be updated";
                }
            }
        } catch (SQLException e) {
            return "Data couldn't be updated.";
        }
    }

    public static String updateOperatorPass(String password, String newPass) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE operator SET password = ? WHERE password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, password);
                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0) {
                    return "Data updated successfully.";
                }
                else{
                    return "No matched data";
                }
            }
        } catch (SQLException e) {
            return "Data couldn't be updated.";
        }
    }



    public static String deleteCustomerData(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String deleteQuery = "DELETE FROM customers WHERE entry_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, id);
                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0) {
                    return "Customer deleted successfully.";
                }
                else{
                    return "No matched data";
                }
            }
        } catch (SQLException e) {
            return "Customer couldn't delete.";
        }
    }

    public static String deleteOperatorData(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String deleteQuery = "DELETE FROM operator WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, username);
                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected>0) {
                    return "Operator deleted successfully.";
                }
                else{
                    return "No matched data";
                }
            }
        } catch (SQLException e) {
            return "Operator couldn't be deleted.";
        }
    }

    public static Timestamp getDate(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT transaction_date FROM customers WHERE entry_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Timestamp date = resultSet.getTimestamp("transaction_date");
                    return date;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            return Timestamp.valueOf(LocalDateTime.now());
        }
    }

    public static String setFees(String id, double fees) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "update customers set customer_payment = ? where entry_id = ? " +
                    "update payment set shifts_payment +=? where FORMAT(GETDATE(), 'HH:mm:ss') between '00:00:00' and '12:00:00' and shift_order = 1" +
                    " update payment set shifts_payment +=? where FORMAT(GETDATE(), 'HH:mm:ss') between'12:00:01' and '23:59:59'  and shift_order = 2";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setDouble(1, fees);
                preparedStatement.setString(2, id);
                preparedStatement.setDouble(3, fees);
                preparedStatement.setDouble(4, fees);
                preparedStatement.executeUpdate();

                return "Data inserted successfully.";
            }
        } catch (SQLException e) {
            return "Data couldn't be inserted.";
        }
    }


    public static String setExitDate(String id, Timestamp date) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "update customers set exit_transaction = ? where entry_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setTimestamp(1, date);
                preparedStatement.setString(2, id);
                preparedStatement.executeUpdate();

                return "Data inserted successfully.";
            }
        } catch (SQLException e) {
            return "Data couldn't be inserted.";
        }
    }

    public static String getCustomerData(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM customers WHERE entry_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String ID = resultSet.getString("entry_id");
                    String plate_number = resultSet.getString("plate_number");
                    Timestamp transaction_date = resultSet.getTimestamp("transaction_date");
                    int slot = resultSet.getInt("slot");
                    Timestamp exitTransactionDate = resultSet.getTimestamp("exit_transaction");
                    double payment = resultSet.getDouble("customer_payment");

                    return "entry_id: " + ID + "\n PlateNumber " + plate_number + "\n transactionDate: " + transaction_date + "\nSlot: " + slot + "\n exitDate: " + exitTransactionDate + "\n Payment: " + payment;
                } else {
                    freeSpot(id);
                    return "No data found for entry_id: " + id;
                }
            }
        } catch (SQLException e) {

            return "Data couldn't be retrieved.";
        }

    }

        public static String setEntryTicket(String id) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                String selectQuery = "SELECT entry_id,transaction_date,slot,plate_number FROM customers WHERE entry_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String ID = resultSet.getString("entry_id");
                        String plate_number = resultSet.getString("plate_number");
                        Timestamp transaction_date = resultSet.getTimestamp("transaction_date");
                        int slot = resultSet.getInt("slot");
                        if (slot ==0){
                            deleteCustomerData(id);
                            return "no freeSpots";
                        }


                        return "entry_id: " + ID + "\ntransactionDate: " + transaction_date + "\nSlot: " + slot + "\nPlateNumber: " + plate_number;
                    } else {

                        return "No data found for entry_id: " + id;
                    }
                }
            } catch (SQLException e) {
                return "no freeSpots";
            }


        }
    public static int checkOperator(String username,String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "select username,password from operator where username =? and password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            return -1;
        }
    }


    public static List<Integer> getFreeSpots() {
        List<Integer> freeSpots = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT spot FROM spots WHERE spot_free = 'free'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int spot = resultSet.getInt("spot");
                    freeSpots.add(spot);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return freeSpots;
    }
    public static int freeSpot(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "UPDATE spots SET spot_free = 'free' WHERE spot IN (SELECT slot FROM customers where entry_id = ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();

                return 1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }
    public static int fullSpots() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT spot FROM spots WHERE spot_free = 'free'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();


                if ( resultSet.next()) {
                    // There are spots free
                    return 0;
                } else {
                    // No spots free
                    return 1;
                }
            }

        } catch (SQLException e) {

            return 3;
        }
    }
    public static int cancelSlot(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT entry_id FROM customers WHERE entry_id =?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return 0;  // Return 0 if there is a row affected
                } else {
                    return 1;  // Return 1 if there is no row affected
                }
            }
        } catch (SQLException e) {
            return -1;  // Return -1 for any SQL exception
        }
    }
    public static String updateShift(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "UPDATE operator SET shift_time =  CASE WHEN shift_time = 1 THEN 2 WHEN shift_time = 2 THEN 1 ELSE shift_time END WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();

                return "shift updated successfully";
            }
        } catch (SQLException e) {
            return "couldn't update shift";
        }
    }


    }













