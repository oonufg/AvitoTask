package ru.Pavel.Domain.Persistance.Repositories.Tables;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.Pavel.Domain.Persistance.Repositories.Enums.ActionsWithSegments;
import ru.Pavel.Domain.Persistance.Repositories.Tables.DataSource.PostgresqlTable;

import java.sql.*;
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

    public List<Map<String, Object>> getUserSegmentsHistory(long user_id){
        List<Map<String, Object>> result = new ArrayList<>();
        try{
            PreparedStatement query = getUserSegmentsHistoryStatement(user_id);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public void addSegmentToUser(long user_id, long segment_id, String action, long timestamp, Long expired_timestamp){
        try{
            PreparedStatement query = getAddSegmentsToUserStatement(user_id, segment_id, action, timestamp, expired_timestamp);
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteUserSegment(long user_id,long segment_id){
        try{
            PreparedStatement query = getDeleteUserSegmentStatement(user_id, segment_id);
            executeUpdate(query);
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void deleteExpiredUserSegments(long timestamp){
        try{
            PreparedStatement query = deleteExpiredUserSegmentsStatement(timestamp);
            executeUpdate(query);
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    private PreparedStatement getDeleteUserSegmentStatement(long user_id, long segment_id) throws SQLException{
        String query = "UPDATE users_segments  SET action = 'removal' WHERE user_id =? AND segment_id =? AND action = 'adding'";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        statement.setLong(2,segment_id);
        return statement;

    }

    public boolean isUserHaveSegment(long userId, long segmentId){
        boolean result = true;
        try{
            PreparedStatement query = getIsUserHaveSegmentStatement(userId, segmentId, ActionsWithSegments.ADDING.getTitle());
            ResultSet queryResult = executeQuery(query);
            result = !isResultSetEmpty(queryResult);

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private PreparedStatement getIsUserHaveSegmentStatement(long userId, long segmentId, String action) throws SQLException{
        String query = "SELECT * FROM users_segments WHERE user_id =? AND segment_id =? AND action = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, segmentId);
        statement.setString(3, action);
        return statement;
    }

    private PreparedStatement getAddSegmentsToUserStatement(long user_id, long segment_id, String action, long timestamp, Long expired_timestamp) throws SQLException{
        String query = "INSERT INTO users_segments(user_id, segment_id, action, timestamp, expired_timestamp) VALUES(?,?,?,?,?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        statement.setLong(2,segment_id);
        statement.setString(3,action);
        statement.setTimestamp(4,new Timestamp(timestamp));
        if(expired_timestamp != null){
            statement.setTimestamp(5,new Timestamp(expired_timestamp));
        }else {
            statement.setNull(5, Types.TIMESTAMP);
        }
        return statement;
    }

    private PreparedStatement getUserSegmentsStatement(long user_id) throws SQLException {
        String query = "SELECT segments.id, segments.slug FROM users_segments LEFT JOIN segments ON users_segments.segment_id= segments.id WHERE users_segments.user_id = ? AND users_segments.action = 'adding'";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,user_id);
        return statement;
    }

    private PreparedStatement getUserSegmentsHistoryStatement(long user_id) throws SQLException{
        String query = "SELECT segments.id, segments.slug, users_segments.action, users_segments.timestamp FROM users_segments LEFT JOIN segments ON users_segments.segment_id= segments.id WHERE users_segments.user_id = ? ORDER BY timestamp DESC";
        PreparedStatement statement =  getStatement(query);
        statement.setLong(1, user_id);
        return statement;
    }

    private PreparedStatement getUserWithoutSegmetnsStatement(long segment_id) throws SQLException{
        String query = "SELECT users.id FROM users_segments WHERE segment_id <> ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, segment_id);
        return statement;
    }

    private PreparedStatement deleteExpiredUserSegmentsStatement(long timestamp) throws SQLException{
        String query = "UPDATE users_segments  SET action = 'removal' WHERE action <> 'removal' AND expired_timestamp <= ?";
        PreparedStatement statement = getStatement(query);
        statement.setTimestamp(1, new Timestamp(timestamp));
        return statement;
    }

}