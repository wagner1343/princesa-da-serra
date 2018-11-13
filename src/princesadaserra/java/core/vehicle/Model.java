package princesadaserra.java.core.vehicle;

public class Model {

    private Long id;
    private int axisAmount;
    private int seatAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAxisAmount() {
        return axisAmount;
    }

    public void setAxisAmount(int axisAmount) {
        this.axisAmount = axisAmount;
    }

    public int getSeatAmount() {
        return seatAmount;
    }

    public void setSeatAmount(int seatAmount) {
        this.seatAmount = seatAmount;
    }
}
