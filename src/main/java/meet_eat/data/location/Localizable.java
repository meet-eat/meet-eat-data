package meet_eat.data.location;

public interface Localizable {
    
    public double getDistance(Localizable location);
    
    public Coordinate getCoordinate();
}