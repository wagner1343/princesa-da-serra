package princesadaserra.java.core.vehicle;

public class Model {

    private Long id;
    private int axisAmount;
    private int seatAmount;
    private String name;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString(){
        return String.format("%s - %d", name, year);
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }

        else {
            return this.getId() == ((Model) other).getId();
        }
    }
}
