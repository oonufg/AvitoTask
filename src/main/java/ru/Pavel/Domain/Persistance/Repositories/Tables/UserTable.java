package ru.Pavel.Domain.Persistance.Repositories.Tables;

import ru.Pavel.Domain.Persistance.Repositories.Tables.DataSource.PostgresqlTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserTable extends PostgresqlTable {

    private String url;
    private String username;
    private String password;

    public UserTable(){
        this.url = "jdbc:postgresql://localhost:5432/segments_service";
        this.username = "postgres";
        this.password = "177013";
        setupConnection(url,username,password);
    }

    public void createUser(){
        try{
            PreparedStatement query = getCreateUserStatement();
            executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(long user_id){
        try{
            PreparedStatement query = getDeleteUserStatement(user_id);
            executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Map<String, Object> getUser(long user_id){
        Map<String,Object> result = new HashMap<>();
        try{
            PreparedStatement query = getUserStatement(user_id);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }



    private PreparedStatement getCreateUserStatement() throws SQLException {
        String query = "INSERT INTO users(id) VALUES(DEFAULT)";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

    private PreparedStatement getDeleteUserStatement(long user_id) throws SQLException{
        String query = "DELETE FROM users WHERE id=?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        return statement;
    }

    private PreparedStatement getUserStatement(long user_id) throws SQLException{
        String query = "SELECT id from users WHERE id= ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, user_id);
        return statement;
    }





}
