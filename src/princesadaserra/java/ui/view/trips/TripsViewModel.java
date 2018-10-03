package princesadaserra.java.ui.view.trips;

import java.util.Observable;

import princesadaserra.java.util.arch.ViewModel;
import princesadaserra.java.model.City;

public interface TripsViewModel extends ViewModel {
    void updateTripSearchResult(City origin, City destination, String date, String time);

    public Observable getTripSearchResult();

    public Observable getRecentTripList();

}
