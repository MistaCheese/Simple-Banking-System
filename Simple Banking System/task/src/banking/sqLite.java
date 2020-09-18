package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqLite {

    static String dbName; // Имя базы

    public static void createDB() { // Проверка, что база есть, если нет, то создаем

        SQLiteDataSource dataSource = connectToDB();

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("CREATE TABLE card (" + "id INTEGER PRIMARY KEY ," + "number TEXT NOT NULL, " + "pin TEXT NOT NULL," + "balance INTEGER DEFAULT 0);");
                System.out.println("test1");

            } catch (SQLException ignored) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static SQLiteDataSource connectToDB() { // Подключение к базе

        String url = "jdbc:sqlite:" + dbName;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }

    public static void insertToBase(String cardNumber, String cardPin) { // Вставка в базу по номеру карты и пина

        SQLiteDataSource dataSource = connectToDB();


        try (Connection con = dataSource.getConnection()) { // Вставка в базу по номеру карты и пина
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("INSERT INTO card " + "( number, pin)" + "VALUES" + "('" + cardNumber + "','" + cardPin + "')");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateBalance(String cardNumber, int cardBalance) { // Вставка в базу по номеру карты + баланс

        SQLiteDataSource dataSource = connectToDB();


        try (Connection con = dataSource.getConnection()) { // Обновление баланса
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("UPDATE card " + "SET balance = balance + " + cardBalance + " WHERE " + "number = " + cardNumber + ";");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void transferBalance (String cardNumber, int cardBalance) { // Вставка в базу по номеру карты + баланс

        SQLiteDataSource dataSource = connectToDB();


        try (Connection con = dataSource.getConnection()) { // Обновление баланса
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("UPDATE card " + "SET balance = balance - " + cardBalance + " WHERE " + "number = " + cardNumber + ";");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkNumberAndPin(String cardNumber, String cardPin) { // Запрос к базе пары: Номер карты и пин код и ответ - true или false
        String cardNumFromBase = null;
        String cardPinFromBase = null;
        SQLiteDataSource dataSource = connectToDB();
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet k = statement.executeQuery("SELECT " + "number, pin, balance FROM  card WHERE number =" + cardNumber + " AND pin =" + cardPin + " ;");
                while (k.next()) {
                    cardNumFromBase = k.getString(1); // Запрос номера карты
                    cardPinFromBase = k.getString(2); // Запрос её пинкода
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (cardNumFromBase != null && cardPinFromBase != null);  // Ответ на совпадение по паре Номер + пин
    }

    public static void SetNameDB(String nameDB) {
        dbName = nameDB;
    }

    public static String[] getAccInfo(String cardNumber, String cardPin) { // Данные об аккаунте: 1. Номер карты 2. Пин код 3. Баланс
        String cardNumFromBase = null;
        String cardPinFromBase = null;
        String cardBalanceFromBase = null;
        SQLiteDataSource dataSource = connectToDB();
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet k = statement.executeQuery("SELECT " + "number, pin, balance FROM  card WHERE number =" + cardNumber + " AND pin =" + cardPin + " ;");
                while (k.next()) {
                    cardNumFromBase = k.getString(1); // Запрос номера карты
                    cardPinFromBase = k.getString(2); // Запрос её пинкода
                    cardBalanceFromBase = k.getString(3); // Запрос баланса
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[]{cardNumFromBase, cardPinFromBase, cardBalanceFromBase}; // 1. Номер карты

    }
    public static String getAccInfo(String cardNumber) { // Данные об аккаунте: 1. Номер карты
        String cardNumFromBase = null;

        SQLiteDataSource dataSource = connectToDB();
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet k = statement.executeQuery("SELECT " + "number FROM  card WHERE number =" + cardNumber + " ;");
                while (k.next()) {
                    cardNumFromBase = k.getString(1); // Запрос номера карты

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardNumFromBase; //  1. Номер карты

    }
    public static void deleteAccount (String cardNumber) {
        SQLiteDataSource dataSource = connectToDB();
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet k = statement.executeQuery("DELETE " + "FROM  card WHERE number =" + cardNumber + " ;");

            } catch (Exception ignored) {
//                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
