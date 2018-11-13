package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.City;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements Mapper<ResultSet, City>{

    @Override
    public City map(ResultSet result){

        City city = null;
        try{

            city = new City();
            city.setId(result.getLong("id_city"));
            city.setName(result.getString("name"));
        } catch(SQLException e){

            e.printStackTrace();
        }
        return city;
    }
}
