package meet_eat.data.location;

public class SphericalLocation implements Localizable {

    private SphericalPosition sphericalPosition;

    public SphericalLocation(SphericalPosition sphericalPosition) {
        this.sphericalPosition = sphericalPosition;
    }

    @Override
    public SphericalPosition getSphericalPosition() {
        return sphericalPosition;
    }

    public void setSphericalPosition(SphericalPosition sphericalPosition) {
        this.sphericalPosition = sphericalPosition;
    }
}