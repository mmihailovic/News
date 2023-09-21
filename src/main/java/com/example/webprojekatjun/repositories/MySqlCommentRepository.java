package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository{
    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO komentar (autor, tekst, datum_kreiranja, vest_id) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAutor());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setLong(3, comment.getDate());
            preparedStatement.setInt(4, comment.getVest_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from komentar ORDER BY datum_kreiranja DESC");
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getInt("vest_id"),resultSet.getString("tekst"), resultSet.getString("autor"), resultSet.getLong("datum_kreiranja")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public List<Comment> allCommentsForNews(Integer vest_id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from komentar where vest_id = ? ORDER BY datum_kreiranja DESC");
            statement.setInt(1,vest_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getInt("vest_id"),resultSet.getString("tekst"), resultSet.getString("autor"), resultSet.getLong("datum_kreiranja")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public Comment findComment(Integer id) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM komentar where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int commentId = resultSet.getInt("id");
                int vest_id = resultSet.getInt("vest_id");
                String author = resultSet.getString("autor");
                String text = resultSet.getString("tekst");
                long date = resultSet.getLong("datum_kreiranja");
                comment = new Comment(commentId,vest_id, text, author, date);
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

        return comment;
    }

    @Override
    public void deleteComment(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM komentar where id = ?");
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
