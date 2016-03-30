package com.caderea.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Created by Cade on 3/27/2016.
 */
@Repository
public class BuildingRepository {
    private JdbcTemplate jdbc;
    private RowMapper buildingRowMapper, roomRowMapper;

    /*
        Row Mapper for Building database
        Id  Name  Address   Floors
     */
    private class BuildingRowMapper implements RowMapper<Building>{
        @Override
        public Building mapRow(ResultSet resultSet, int i) throws SQLException {
            Building building = new Building();
            building.setId(resultSet.getLong(1));
            building.setName(resultSet.getString(2));
            building.setAddress(resultSet.getString(3));
            building.setNumFloors(resultSet.getInt(4));

            return building;
        }
    }

    /*
        Row Mapper for Rooms database
        Id  Building Floor
     */
    private class RoomRowMapper implements RowMapper<Room>{
        @Override
        public Room mapRow(ResultSet resultSet, int i) throws SQLException{
            Room room = new Room();
            room.setId(resultSet.getLong(1));
            room.setBuildingId(resultSet.getInt(2));
            room.setFloorId(resultSet.getInt(3));

            return room;
        }
    }

    @Autowired
    public BuildingRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        buildingRowMapper = new BuildingRowMapper();
        roomRowMapper = new RoomRowMapper();
    }

    public List<Building> getAllBuildings(){
        return jdbc.query("select * from buildings order by id", buildingRowMapper);
    }

    public Building getOneBuilding(final long buildingId) {
        return (Building)jdbc.queryForObject("select * from buildings where id=?", new Long[]{buildingId}, buildingRowMapper);
    }

    public void saveBuilding(final Building building){
        //use a prepared statement so I can get the auto-generated id
        PreparedStatementCreator psc =  new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =  connection.prepareStatement(
                        "insert into buildings (name, address, numFloors) values (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,building.getName());
                ps.setString(2,building.getAddress());
                ps.setInt(3,building.getNumFloors());

                return ps;}
        };

        building.setId(getIdFromInsert(psc)); //set the id of the building now. Could also reload.
    }

    public void saveRoom(final Room room){
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into rooms(buildingId, floorId) values (?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,room.getBuildingId());
                ps.setLong(2,room.getFloorId());

                return ps;
            }
        };

        room.setId(getIdFromInsert(psc));
    }

    /*
        Get the auto-generated id from an insert statement
     */
    private long getIdFromInsert(PreparedStatementCreator preparedStatementCreator){
        //This holds the generated id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void deleteBuilding(long buildingId){
        jdbc.update("DELETE FROM buildings WHERE id = ?", buildingId);
    }

    public List<Room> getAllRooms(){
        return jdbc.query("select * from rooms", roomRowMapper);
    }

    public Room getOneRoom(final long roomId){
        return (Room)jdbc.queryForObject("select * from rooms where id=?",new Long[]{roomId},roomRowMapper);
    }

    public List<Room> getRoomsInBuilding(long buildingId){
        return jdbc.query("select * from rooms where buildingId=? order by floorId",new Long[]{buildingId},roomRowMapper);
    }

    public List<Room> getRoomsInBuildingOnFloor(long buildingId, long floorId){
        return jdbc.query("select * from rooms where buildingId=? and floorId=?",new Long[]{buildingId,floorId},roomRowMapper);
    }
}
