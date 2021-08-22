package code_sharing_platform.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "codeSnippet")
public class CodeSnippet {


    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2")
    @JsonIgnore
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column
    private String code;

    @Column
    private String date;

    @Column
    private int time;

    @Column
    private int views;

    @JsonIgnore
    @Column
    private boolean secretTime;

    @JsonIgnore
    @Column
    private boolean secretViews;


    public CodeSnippet() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = LocalDateTime.now().format(formatter);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean hasSecretTime() {
        return secretTime;
    }

    public void setSecretTime(boolean secretTime) {
        this.secretTime = secretTime;
    }

    public boolean hasSecretViews() {
        return secretViews;
    }

    public void setSecretViews(boolean secretViews) {
        this.secretViews = secretViews;
    }
}
