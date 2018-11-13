package princesadaserra.java.persistence.repository.route;

import princesadaserra.java.core.route.Segment;
import princesadaserra.java.core.route.City;
import princesadaserra.java.util.mapping.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SegmentMapper implements Mapper<ResultSet, Segment>{

    @Override
    public Segment map(ResultSet result){

        Segment segment = null;
        City cityS = null;
        City cityA = null;
        try{

            segment = new Segment();
            cityS = new City();
            cityA = new City();

            segment.setId(result.getLong("id_segment"));
            cityS.setId(result.getLong("id_citys"));
            cityS.setName(result.getString("names"));
            cityA.setId(result.getLong("id_citia"));
            cityA.setName(result.getString("namea"));
            segment.setTime(result.getInt("estimatedTime"));
            segment.setValue(result.getDouble("value"));
            segment.setCityDestination(cityA);
            segment.setCityOrigin(cityS);
        } catch(SQLException e){

            e.printStackTrace();
        }
        return segment;
    }
}
