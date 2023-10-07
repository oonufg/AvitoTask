package ru.Pavel.Domain.Persistance.Tables;

import ru.Pavel.Domain.Persistance.Tables.DataSource.SegmentServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegmentTable extends SegmentServiceTable {
    public void createSegment(String slug){
        try{
            PreparedStatement query = getCreateSegmentStatement(slug);
            executeUpdate(query);
        }catch(SQLException exception){
            System.out.println("-> " + exception.getMessage());
        }
    }

    public void deleteSegment(String slug){
        try{
            PreparedStatement query = getDeleteSegmentStatement(slug);
            executeUpdate(query);
        }catch(SQLException exception){
            System.out.println("-> " + exception.getMessage());
        }
    }

    public List<Map<String,Object>> getAllSegments(){
        List<Map<String,Object>> queryResult = new ArrayList<>();
        try {
            PreparedStatement query = getAllSegmentsStatement();
            ResultSet queryResultSet = executeQuery(query);
            queryResult.addAll(resutlSetToList(queryResultSet));
        }catch(SQLException e){
            System.out.println("-> "+e.getMessage());
        }
        return queryResult;
    }

    public Map<String,Object> getCertainSegment(String slug){
        Map<String,Object> result = new HashMap<>();
        try{
            PreparedStatement query = getCertainSegmentStatement(slug);
            ResultSet queryResult = executeQuery(query);
            queryResult.next();
            result.putAll(resultSetToMap(queryResult));
        }catch (SQLException e){
            System.out.println("-> "+e.getMessage());
        }
        return result;
    }

    public boolean isSegmentExist(long id, String slug){
        boolean result = false;
        try{
            PreparedStatement query = getIsSegmentExistSegment(id,slug);
            ResultSet queryResult =  executeQuery(query);
            result = !isResultSetEmpty(queryResult);
        }catch(SQLException exception){
            System.out.println("-> "+exception.getMessage());
        }
        return result;
    }

    private PreparedStatement getIsSegmentExistSegment(long id, String slug) throws SQLException{
        String query =
                "SELECT * FROM segments " +
                "WHERE id =? AND slug=?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,id);
        statement.setString(2,slug);
        return statement;
    }

    private PreparedStatement getCreateSegmentStatement(String slug) throws SQLException{
        String query = "INSERT INTO segments(slug) VALUES(?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,slug);
        return statement;
    }

    private PreparedStatement getDeleteSegmentStatement(String slug) throws SQLException{
        String query =
                "DELETE FROM segments " +
                "WHERE slug= ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,slug);
        return statement;
    }

    private PreparedStatement getAllSegmentsStatement() throws SQLException{
        String query = "SELECT * FROM segments";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

    private PreparedStatement getCertainSegmentStatement(String slug) throws SQLException{
        String query =
                "SELECT * FROM segments " +
                "WHERE slug = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, slug);
        return statement;
    }
}
