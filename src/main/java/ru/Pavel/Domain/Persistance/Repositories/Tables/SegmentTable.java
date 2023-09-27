package ru.Pavel.Domain.Persistance.Repositories.Tables;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.Pavel.Domain.Persistance.Repositories.Tables.DataSource.PostgresqlTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class SegmentTable extends PostgresqlTable {
    @Value("${pgsql.url}")
    private String url;
    @Value("${pgsql.username}")
    private String username;
    @Value("${pgsql.password}")
    private String password;

    public SegmentTable(){
        init();
    }
    @PostConstruct
    private void init(){
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

    public boolean isSegmentExist(long id, String slug){
        boolean result = false;
        try{
            PreparedStatement query = getIsSegmentExistSegment(id,slug);
            ResultSet queryResult =  executeQuery(query);
            result = isResultSetEmpty(queryResult);
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    private PreparedStatement getIsSegmentExistSegment(long id, String slug) throws SQLException{
        String query = "SELECT * FROM segments WHERE id =? AND slug=?";
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
