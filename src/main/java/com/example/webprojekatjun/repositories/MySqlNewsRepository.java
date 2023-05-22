package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository{
    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vest (naslov, tekst, vreme_kreiranja, broj_poseta, autor, kategorija_ime) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getText());
            preparedStatement.setLong(3, news.getDate());
            preparedStatement.setInt(4, news.getBrojPoseta());
            preparedStatement.setString(5, news.getAuthor());
            preparedStatement.setString(6, news.getCategory());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> allNews() {
        List<News> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest");
            while (resultSet.next()) {
                posts.add(new News(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"),resultSet.getLong("vreme_kreiranja"),resultSet.getInt("broj_poseta"),resultSet.getString("autor"),resultSet.getString("kategorija_ime")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public List<News> allNewsForCategory(String ime) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from vest where kategorija_ime = ?");
            statement.setString(1,ime);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getLong("vreme_kreiranja"),resultSet.getInt("broj_poseta"),resultSet.getString("autor"), resultSet.getString("kategorija_ime")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> allNewsForTag(String kljucna_rec) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select v.* from tag t join vest v on(t.vest_id = v.id) where lower(t.kljucna_rec) = ?");
            statement.setString(1,kljucna_rec.toLowerCase());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"), resultSet.getLong("vreme_kreiranja"),resultSet.getInt("broj_poseta"),resultSet.getString("autor"), resultSet.getString("kategorija_ime")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News findNews(Integer id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int newsId = resultSet.getInt("id");
                String title = resultSet.getString("naslov");
                String text = resultSet.getString("tekst");
                long date = resultSet.getLong("vreme_kreiranja");
                int brojPoseta = resultSet.getInt("broj_poseta");
                String author = resultSet.getString("autor");
                String category = resultSet.getString("kategorija_ime");
                news = new News(newsId, title, text, date, brojPoseta, author, category);
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

        return news;
    }

    @Override
    public void deleteNews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vest where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
