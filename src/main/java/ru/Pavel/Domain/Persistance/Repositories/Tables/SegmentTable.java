package ru.Pavel.Domain.Persistance.Repositories.Tables;

import ru.Pavel.Domain.Persistance.Repositories.Tables.DataSource.PostgresqlTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SegmentTable extends PostgresqlTable {
    private String url;
    private String username;
    private String password;

    public SegmentTable(){
        this.url = "jdbc:postgresql://localhost:5432/segments_service";
        this.username = "postgres";
        this.password = "177013";
        setupConnection(url,username,password);
    }

    public void createSegment(String slug){
        try{
            PreparedStatement query = getCreateSegmentStatement(slug);
            executeQuery(query);
        }catch(SQLException exception){
            System.out.println("-> " + exception.getMessage());
        }
    }

    public void deleteSegment(String slug){
        try{
            PreparedStatement query = getDeleteSegmentStatement(slug);
            executeQuery(query);
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
            System.out.println(e.getMessage());
        }
        return queryResult;
    }


    private PreparedStatement getCreateSegmentStatement(String slug) throws SQLException{
        String query = "INSERT INTO segments(slug) VALUES(?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,slug);
        return statement;
    }

    private PreparedStatement getDeleteSegmentStatement(String slug) throws SQLException{
        String query = "DELETE FROM segments WHERE slug= ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,slug);
        return statement;
    }

    private PreparedStatement getAllSegmentsStatement() throws SQLException{
        String query = "SELECT * FROM segments";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

}
