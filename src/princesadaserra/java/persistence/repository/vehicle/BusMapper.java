package princesadaserra.java.persistence.repository.vehicle;

import princesadaserra.java.core.vehicle.Bus;
import princesadaserra.java.core.vehicle.Model;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusMapper implements Mapper<ResultSet, Bus>{

    @Override
    public Bus map(ResultSet result){

        Bus bus = null;
        Model model = null;
        try{

            bus = new Bus();
            model = new Model();

            bus.setId(result.getLong("id_bus"));
            model.setId(result.getLong("id_model"));
            bus.setLastMaintenance(result.getDate("id_dateLastMaintenance"));
            bus.setModel(model);
        } catch(SQLException e){

            e.printStackTrace();
        }
        return bus;
    }
}
