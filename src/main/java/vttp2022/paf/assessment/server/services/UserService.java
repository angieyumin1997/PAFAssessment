package vttp2022.paf.assessment.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import java.util.UUID;

import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.Queries;

@Service
public class UserService implements Queries{
    @Autowired 
    private JdbcTemplate template;

    public Optional <User> findUserByUsername(String username){
        
        final SqlRowSet result= template.queryForRowSet(SQL_SELECT_USER,username);

        if (!result.next())
            return Optional.empty();

        return Optional.of(User.create(result));

    }

    public String insertUser(User user){
        String user_id = UUID.randomUUID().toString().substring(0, 8);
        template.update(SQL_INSERT_USER,user_id,user.getUsername());
        return user_id;

    }
    
}
