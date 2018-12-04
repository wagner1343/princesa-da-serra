package princesadaserra.java.persistence.repository.vehicle;

import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelMapper implements Mapper<ResultSet, Model>{

    @Override
    public Model map(ResultSet result){

        Model model = null;
        try{

            model = new Model();
            model.setId(result.getLong("id_model"));
            model.setAxisAmount(result.getInt("amntAxis"));
            model.setSeatAmount(result.getInt("amntSeats"));
            model.setYear(result.getInt("year"));
            model.setName(result.getString("name"));
        } catch(SQLException e){

            e.printStackTrace();
        }
        return model;
    }
}
