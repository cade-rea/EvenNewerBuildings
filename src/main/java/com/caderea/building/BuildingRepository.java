package com.caderea.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cade on 3/27/2016.
 */
@Repository
public class BuildingRepository {
    private JdbcTemplate jdbc;
    private BuildingRowMapper rowMapper;

    private class BuildingRowMapper implements RowMapper<Building>{
        @Override
        public Building mapRow(ResultSet resultSet, int i) throws SQLException {
            Building building = new Building();
            building.setId(resultSet.getLong(1));
            building.setName(resultSet.getString(2));
            building.setAddress(resultSet.getString(3));
            return building;
        }
    }

    @Autowired
    public BuildingRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        rowMapper = new BuildingRowMapper();
    }

    public List<Building> getAll(){
        return jdbc.query("select id, name, address from buildings order by id", rowMapper);
    }

    public Building getOne(final long id) {
        return jdbc.queryForObject("select * from buildings where id=?", new Long[]{id},rowMapper);
    }

    public void save(Building building){
        jdbc.update(
                "insert into buildings (name, address) values (?,?)",
                building.getName(),
                building.getAddress());
    }

    public void delete(long building){
        jdbc.update(
                "DELETE FROM buildings WHERE id = ?", building
        );
    }
}
