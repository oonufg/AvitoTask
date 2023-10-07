package ru.Pavel.Domain.Persistance.Tables;

import ru.Pavel.Domain.Persistance.Tables.DataSource.SegmentServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserTable extends SegmentServiceTable {

    public void createUser(){
        try{
            PreparedStatement query = getCreateUserStatement();
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println("-> "+e.getMessage());
        }
    }

    public void deleteUser(long user_id){
        try{
            PreparedStatement query = getDeleteUserStatement(user_id);
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println("-> "+e.getMessage());
        }
    }

    public Map<String, Object> getUser(long user_id){
        Map<String,Object> result = new HashMap<>();
        try{
            PreparedStatement query = getUserStatement(user_id);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println("-> "+e.getMessage());
        }
        return result;
    }

    public List<Map<String, Object>> getAllUsers(){
        List<Map<String, Object>> result = new ArrayList<>();
        try{
            PreparedStatement query = getAllUsersStatement();
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch(SQLException exception){
            System.out.println("-> "+exception.getMessage());
        }
        return result;
    }

    private PreparedStatement getCreateUserStatement() throws SQLException {
        String query = "INSERT INTO users(id) VALUES(DEFAULT)";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

    private PreparedStatement getDeleteUserStatement(long user_id) throws SQLException{
        String query =
                "DELETE FROM users " +
                "WHERE id= ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        return statement;
    }

    private PreparedStatement getUserStatement(long user_id) throws SQLException{
        String query =
                "SELECT * from users " +
                "WHERE id= ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, user_id);
        return statement;
    }

    private PreparedStatement getAllUsersStatement() throws SQLException{
        String query = "SELECT * FROM users";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

}
