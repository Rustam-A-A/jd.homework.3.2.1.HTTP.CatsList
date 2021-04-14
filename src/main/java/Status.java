
public class Status {
    Boolean verified;
    Integer sentCount;

    public Status(Boolean verified, Integer sentCount){
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
        return "[Verified: " + getVerified() +
                ", " +
                "Count: " + getSentCount() +
                "]";
    }
}
