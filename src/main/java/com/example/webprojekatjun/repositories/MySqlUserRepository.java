package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository{
    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO korisnik (email, sifra, ime, prezime, tip, aktivan) VALUES(?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3, user.getIme());
            preparedStatement.setString(4, user.getPrezime());
            preparedStatement.setString(5, user.getTip());
            preparedStatement.setBoolean(6, user.isAktivan());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public User findUser(String username) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnik where email = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String email = resultSet.getString("email");
                String sifra = resultSet.getString("sifra");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String tip = resultSet.getString("tip");
                boolean aktivan = resultSet.getBoolean("aktivan");
                user = new User(email, sifra, ime, prezime, tip, aktivan);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnik");
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("email"), resultSet.getString("sifra"), resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getString("tip"),resultSet.getBoolean("aktivan")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public List<User> allUsersWithPagination(Integer page) {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from korisnik LIMIT 3 OFFSET ?");
            statement.setInt(1,(page-1)*3);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("email"), resultSet.getString("sifra"), resultSet.getString("ime"),resultSet.getString("prezime"),resultSet.getString("tip"),resultSet.getBoolean("aktivan")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public boolean updateUser(String username, String newUsername, String ime, String prezime, String tip) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnik SET email = ?, ime = ?, prezime= ?, tip = ? where email = ?");
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, ime);
            preparedStatement.setString(3, prezime);
            preparedStatement.setString(4, tip);
            preparedStatement.setString(5, username);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean setStatus(String username, boolean active) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnik SET aktivan = ? where email = ?");
            preparedStatement.setBoolean(1, active);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return false;
    }
}
