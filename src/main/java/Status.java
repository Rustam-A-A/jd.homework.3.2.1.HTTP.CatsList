import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    Boolean verified;
    Integer sentCount;

    public Status(
            @JsonProperty("verified") Boolean verified,
            @JsonProperty("sentCount") int sentCount
    ){
        this.verified = verified;
        this.sentCount = sentCount;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    @Override
    public String toString(){
        return "[Verified: " + getVerified() + ", " + "Count: " + getSentCount() + "]";
    }
}
