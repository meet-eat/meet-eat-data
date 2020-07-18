package meet_eat.data.location.geometry;

import meet_eat.data.location.Coordinate;

import java.util.function.Function;

public final class Haversine {

    private static final double EARTH_RADIUS = 6371000d;

    private Haversine() {
    }

    private static Function<Double, Double> HAVERSINE_FUNCTION = new Function<>() {
        @Override
        public Double apply(Double t) {
            return Math.pow(Math.sin(t / 2.0), 2.0);
        }
    };

    public static Double applyHaversineFunction(Double value) {
        return HAVERSINE_FUNCTION.apply(value);
    }

    public static Double applyHaversineFormula(Coordinate origin, Coordinate destination) {
        double havLat = HAVERSINE_FUNCTION.apply(destination.getLatitudeAsRadians() - origin.getLatitudeAsRadians());
        double havLon = HAVERSINE_FUNCTION.apply(destination.getLongitudeAsRadians() - origin.getLongitudeAsRadians());

        double cosLatOrigin = Math.cos(origin.getLatitudeAsRadians());
        double cosLatDestination = Math.cos(destination.getLatitudeAsRadians());

        return 2.0 * EARTH_RADIUS * Math.asin(Math.sqrt(havLat + cosLatOrigin * cosLatDestination * havLon));
    }
}