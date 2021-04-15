import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {
    Status status;
    String type;
    Boolean deleted;
    String id;
    String user;
    String text;
    long v;
    String source;
    String updatedAt;
    String createdAt;
    Boolean used;

    public Cat(
            @JsonProperty("status") Status status,
            @JsonProperty("type") String type,
            @JsonProperty("deleted") Boolean deleted,
            @JsonProperty("_id") String id,
            @JsonProperty("user") String user,
            @JsonProperty("text") String text,
            @JsonProperty("__v") long v,
            @JsonProperty("source") String source,
            @JsonProperty("updateAt") String updateAt,
            @JsonProperty("createAt") String createAt,
            @JsonProperty("used") Boolean used
    ){
        this.status = status;
        this.type = type;
        this.deleted = deleted;
        this.id = id;
        this.user = user;
        this.text = text;
        this.v = v;
        this.source = source;
        this.updatedAt = updateAt;
        this.createdAt = createAt;
        this.used = used;
    }

    public Status getStatus(){return status;}

    public String getType() {
        return type;
    }

    public Boolean getDeleted(){
        return deleted;
    }

    public String getId() {
        return id;
    }

    public String getUser(){
        return user;
    }

    public String getText(){
        return text;
    }

    public long get__(){ return v; }

    public String getSource(){
        return source;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public Boolean getUsed(){
        return used;
    }

    @Override
    public String toString(){
        return "\n{Type: " + type +
                "\nID: " + id +
                "\nDeleted: " + deleted +
                "\nUser: " + user +
                "\nStatus: " + getStatus() +
                "\nText: " + text + "}";
    }
}
