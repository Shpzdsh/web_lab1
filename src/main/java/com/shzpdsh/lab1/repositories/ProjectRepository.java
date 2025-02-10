package com.shzpdsh.lab1.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shzpdsh.lab1.models.ProjectModel;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProjectRepository implements RepositoryInterface {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<ProjectModel> rowMapper = (rs, rowNum) -> {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(rs.getLong("id"));
        projectModel.setName(rs.getString("name"));
        projectModel.setDescription(rs.getString("description"));
        projectModel.setStart(rs.getDate("start"));
        projectModel.setEnd(rs.getDate("end"));
        return projectModel;
    };

    @Override
    public ProjectModel save(ProjectModel projectModel) {
        String sqlRequest = "INSERT INTO project (name, description, start, end) VALUES (?, ?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sqlRequest, Long.class,
            projectModel.getName(),
            projectModel.getDescription(),
            projectModel.getStart(),
            projectModel.getEnd());
        projectModel.setId(id);
        return projectModel;
    }

    @Override
    public ProjectModel update(Long id, ProjectModel projectModel) {
        String sqlRequest = "UPDATE project SET name = ?, description = ?, start = ?, end = ? WHERE id = ?";
        jdbcTemplate.update(sqlRequest,
            projectModel.getName(),
            projectModel.getDescription(),
            projectModel.getStart(),
            projectModel.getEnd(),
            id);
        return projectModel;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM project WHERE id = ?", id);
    }

    @Override
    public ProjectModel findById(Long id){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM project WHERE id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ProjectModel> findByDateRange(Date start, Date end) {
        String sqlRequest = "SELECT * FROM project WHERE start BETWEEN ? AND ? OR end BETWEEN ? AND ?";
        return jdbcTemplate.query(sqlRequest, rowMapper, start, end);
    }

    @Override
    public List<ProjectModel> findAll() {
        return jdbcTemplate.query("SELECT * FROM project", rowMapper);
    }
}
