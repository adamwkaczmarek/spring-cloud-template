package home.samples.context;


public class UserContext {


    public static final String CORRELATION_ID = "correlation-id";
    public static final String AUTH_TOKEN = "Authorization";

    private String correlationId = new String();
    private String authToken = new String();

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId){

        this.correlationId = correlationId;
    }

}
