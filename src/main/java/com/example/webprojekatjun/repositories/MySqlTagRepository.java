package com.example.webprojekatjun.repositories;

import com.example.webprojekatjun.entities.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository{
    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tag (kljucna_rec, vest_id) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, tag.getKljucnaRec());
            preparedStatement.setInt(2, tag.getVest_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return tag;
    }

    @Override
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("kljucna_rec"),resultSet.getInt("vest_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public Tag findTag(Integer id) {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tag where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer tagId = resultSet.getInt("id");
                String rec = resultSet.getString("kljucna_Rec");
                Integer vest_id = resultSet.getInt("vest_id");
                tag = new Tag(tagId, rec,vest_id);
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

        return tag;
    }

    @Override
    public List<Tag> allTagsForNews(Integer vest_id) {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from tag where vest_id = ?");
            statement.setInt(1, vest_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("kljucna_rec"),resultSet.getInt("vest_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public List<Tag> updateTags(Integer vest_id, List<Tag> tags) {
        List<Tag> currentTags = allTagsForNews(vest_id);
        Map<Tag,Integer> mapa = new HashMap<>();
        List<Tag> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.newConnection();

            for(Tag t: tags) {
                mapa.put(t,1);
            }
            for(Tag t: currentTags) {
                if(mapa.containsKey(t)) {
                    mapa.replace(t,mapa.get(t) - 1);
                }
                else mapa.put(t,-1);
            }

            for(Map.Entry<Tag,Integer> m: mapa.entrySet()) {
                if(m.getValue() == 0) {
                    result.add(m.getKey());
                    continue;
                }
                if(m.getValue() == -1) {
                    PreparedStatement preparedStatement = null;
                    preparedStatement = connection.prepareStatement("DELETE FROM tag WHERE id = ?");
                    preparedStatement.setInt(1, m.getKey().getId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                else {
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    String[] generatedColumns = {"id"};

                    preparedStatement = connection.prepareStatement("INSERT INTO tag (kljucna_rec, vest_id) VALUES(?, ?)", generatedColumns);
                    preparedStatement.setString(1, m.getKey().getKljucnaRec());
                    preparedStatement.setInt(2, m.getKey().getVest_id());
                    preparedStatement.executeUpdate();
                    resultSet = preparedStatement.getGeneratedKeys();
                    if(resultSet.next()) {
                        m.getKey().setId(resultSet.getInt(1));
                    }

                    result.add(m.getKey());
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection(connection);
        }
        return tags;
    }

    @Override
    public void deleteTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tag where id = ?");
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
