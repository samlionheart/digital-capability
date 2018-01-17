package models;

/**
 * Created by daniel.rothig on 21/10/2016.
 *
 * Enumeratinon of known digital roles
 */
public class DigitalRole {
    public static DigitalRole BUSINESS_ANALYST = new DigitalRole("Business analyst");
    public static DigitalRole CONTENT_DESIGNER = new DigitalRole("Content designer");
    public static DigitalRole DELIVERY_MANAGER = new DigitalRole("Delivery manager");
    public static DigitalRole DESIGNER = new DigitalRole("Designer");
    public static DigitalRole USER_RESEARCHER = new DigitalRole("User researcher");




    public final String descriptor;

    private DigitalRole(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public int hashCode() {
        return descriptor != null ? descriptor.hashCode() : 0;
    }
}
