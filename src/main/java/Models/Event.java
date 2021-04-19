package Models;

import Utils.DatabaseController;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //id is generated by the DB (do not set it)

    private String name;
    private String description;
    private String image;

    //if the timeline uses one of the time presets
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    //if the timeline uses a custom time unit
    // decided to make LocalTime objects but might change as of less conditional testing
    private LocalTime startTime;
    private LocalTime endTime;

    private int startInt;
    private int endInt;

    //userID that created this event
    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "fk_userid")
    private User createdBy;

    @Generated(GenerationTime.INSERT)
    private Date creationDate;
    //if of the timeline where the event is in
    @ManyToOne
    //@JoinColumn(name = "fk_timeline")
    private Timeline timeline;

    //private int timelineID;

    public Event() {}

    /*public Event(int id, String name, String description, String image, Date endDate, Date startDate,int start, int end, Timeline timeline, User createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.endDate = endDate;
        this.startDate = startDate;
        this.startInt = start;
        this.endInt = end;
        this.timeline = timeline;
        this.createdBy = createdBy;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name can not be Empty, Please enter a name for event");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getStartInt() {
        return startInt;
    }

    public LocalTime getStart() {
        return startTime;
    }

    public void setStart(LocalTime start) {
        this.startTime = start;
    }
    public void setStartInt(int startInt) {
        this.startInt = startInt;
    }

    public LocalTime getEnd() {
        return endTime;
    }

    public int getEndInt() {
        return endInt;
    }

    public void setEnd(LocalTime end) {
        this.endTime = end;
    }

    public void setEndInt(int endInt) {
        this.endInt = endInt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        if(createdBy == null)
                throw new RuntimeException("No timeline selected");
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date createdAt) {
        this.creationDate = createdAt;
    }

    public Timeline getTimelineID() {
        return timeline;
    }

    public void setTimelineID(Timeline timelineID) {
        this.timeline = timelineID;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override
    public String toString() {
        String result = name;
        if(createdBy!=null) {
            result += " by " + createdBy.getUsername();
        }
        result += "; "+startDate.toString();
        if(endDate!=null) {
            result += " -> "+endDate.toString();
        }
        return result;
    }

    //DB CRUD
    public void save() {
        DatabaseController.createRecord(this);
    }

    public void update() {
        DatabaseController.updateRecord(this);
    }

    public void delete() {
        DatabaseController.deleteRecord(this.id, Event.class);
    }

    static public Event load(int id) {
        return (Event) DatabaseController.getRecord(id, Event.class);
    }
}