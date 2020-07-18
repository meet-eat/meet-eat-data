package meet_eat.data.location;

import meet_eat.data.location.geometry.Haversine;

public interface Localizable {

    public Coordinate getCoordinate() throws UnlocalizableException;

    public default double getDistance(Localizable localizable) throws UnlocalizableException {
        return Haversine.applyHaversineFormula(this.getCoordinate(), localizable.getCoordinate());
    }
}