package ro.autogest.client.util;

public enum MIMETypes {
    APPLICATION_JSON("application/json");

    private final String name;

    private MIMETypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
