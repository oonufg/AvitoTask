package ru.Pavel.Domain.Persistance.Tables.DataSource;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SegmentServiceTable extends PostgresqlTable{
    @Value("${pgsql.url}")
    private String url;
    @Value("${pgsql.username}")
    private String username;
    @Value("${pgsql.password}")
    private String password;

    public SegmentServiceTable(){
        init();
    }

    @PostConstruct
    private void init(){
        setupConnection(url,username,password);
    }
}
