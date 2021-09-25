package platform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "code")
@JsonIgnoreProperties({"id", "restriction", "uuid"})
public  class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private  LocalDateTime date;
    private String code;

    private Restriction restriction;


    private long time;

    private int views;


    private String UUID;

    public Code() {
    }

    public Code(LocalDateTime date, String code) {
        this.date = date;
        this.code = code;
    }

    public Code(String code, long time, int views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public  String getDate() {
        if(date != null) {
            return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(date);
        }
        return null;
    }

    public  void setDate(LocalDateTime date) {
        this.date = date;
    }

    public  String getCode() {
        return code;
    }

    public  void setCode(String code) {
        this.code = code;
    }


    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }



    public long getTime() {
        if (restriction == Restriction.ALL || restriction == Restriction.TIME) {
            long createTime = date.toEpochSecond(ZoneOffset.UTC);
            long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            return time - currentTime + createTime;
        }
        return time;
    }

    public void setTime(long timeRestriction) {
        this.time = timeRestriction;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int viewsRestriction) {
        this.views = viewsRestriction;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }
}
