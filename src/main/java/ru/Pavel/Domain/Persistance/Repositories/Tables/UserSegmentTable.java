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
public class UserSegmentTable extends PostgresqlTable {
    @Value("${pgsql.url}")
    private String url;
    @Value("${pgsql.username}")
    private String username;
    @Value("${pgsql.password}")
    private String password;

    public UserSegmentTable(){
        init();
    }

    @PostConstruct
    private void init(){
        setupConnection(url,username,password);
    }

    public List<Map<String,Object>> getUserSegments(long user_id){
        List<Map<String,Object>> result = new ArrayList<>();
        try{
            PreparedStatement query = getUserSegmentsStatement(user_id);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addSegmentToUser(long user_id, long segment_id){
        try{
            PreparedStatement query = getAddSegmentsToUserStatement(user_id,segment_id);
            executeQuery(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isUserHaveSegment(long userId, long segmentId){
        boolean result = true;
        try{
            PreparedStatement query = getIsUserHaveSegmentStatement(userId, segmentId);
            ResultSet queryResult = executeQuery(query);
            if(isResultSetEmpty(queryResult)){
                result = false;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private PreparedStatement getIsUserHaveSegmentStatement(long userId, long segmentId) throws SQLException{
        String query = "SELECT * FROM users_segments WHERE user_id =? AND segment_id =?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, segmentId);
        return statement;
    }

    private PreparedStatement getAddSegmentsToUserStatement(long user_id, long segment_id) throws SQLException{
        String query = "INSERT INTO users_segments(user_id,segments_id) VALUES(?,?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        statement.setLong(2,segment_id);
        return statement;
    }

    private PreparedStatement getUserSegmentsStatement(long user_id) throws SQLException {
        String query = "SELECT segments.id, segments.slug FROM users_segments LEFT JOIN segments ON users_segments.segments_id= segments.id WHERE users_segments.user_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        return statement;
    }


}