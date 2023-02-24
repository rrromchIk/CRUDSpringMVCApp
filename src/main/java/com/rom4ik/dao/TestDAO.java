package com.rom4ik.dao;

import com.rom4ik.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author rom4ik
 */
@Component
public class TestDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Test> getAll() {
        return jdbcTemplate.query("SELECT * FROM test", new BeanPropertyRowMapper<>(Test.class));
    }

    public Test getById(int id) {
        return jdbcTemplate.query("SELECT * FROM test WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Test.class))
                .stream().findAny().orElse(null);
    }

    public Test getByName(String name) {
        return jdbcTemplate.query("SELECT * FROM test WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Test.class))
                .stream().findAny().orElse(null);
    }

    public void addTest(Test test) {
        jdbcTemplate.update("INSERT INTO test(name, subject, duration, numberOfQuestions)" +
                        " VALUES(?, ?, ?, ?)",
                test.getName(),
                test.getSubject(),
                test.getDuration(),
                test.getNumberOfQuestions());
    }

    public void update(Test test, int id) {
        jdbcTemplate.update("UPDATE test SET name=?, subject=?, duration=?, numberOfQuestions=? WHERE id=?",
                test.getName(),
                test.getSubject(),
                test.getDuration(),
                test.getNumberOfQuestions(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM test WHERE id=?", id);
    }
}
