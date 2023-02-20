package com.example.spring2.respositories;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import com.example.spring2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class UserRepositoryImplementation implements UserRepository{
    private static final String SQL_CREATE = "INSERT INTO USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES(NEXTVAL('ET_USERS_SEQ'), ?, ?, ?, ?)";
    // private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) AS emails FROM et_users WHERE email = :id";
    // private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) AS emails FROM et_users WHERE email = :email";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";
    //private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) AS emails FROM et_users WHERE email = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD " +
            "FROM USERS WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;



    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    };

    private RowMapper<Integer> userRowCount = (rs, rowNum) -> {
        return rs.getInt(1);
    };

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new EtAuthException("Invalid details. Failed to create account " + e.getMessage());
        }


    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, email);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, userId);
    }
}
