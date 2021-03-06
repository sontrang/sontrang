package poiuyt.alarm.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ALARM".
 */
public class Alarm {

    private Long id;
    private java.util.Date time;
    private Boolean active;
    private Long durationTime;
    private String repeatDay;
    private String tonePath;
    private Boolean vibrate;
    private String label;

    public Alarm() {
    }

    public Alarm(Long id) {
        this.id = id;
    }

    public Alarm(Long id, java.util.Date time, Boolean active, Long durationTime, String repeatDay, String tonePath, Boolean vibrate, String label) {
        this.id = id;
        this.time = time;
        this.active = active;
        this.durationTime = durationTime;
        this.repeatDay = repeatDay;
        this.tonePath = tonePath;
        this.vibrate = vibrate;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Long durationTime) {
        this.durationTime = durationTime;
    }

    public String getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(String repeatDay) {
        this.repeatDay = repeatDay;
    }

    public String getTonePath() {
        return tonePath;
    }

    public void setTonePath(String tonePath) {
        this.tonePath = tonePath;
    }

    public Boolean getVibrate() {
        return vibrate;
    }

    public void setVibrate(Boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
