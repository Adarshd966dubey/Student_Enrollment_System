package Models;

public class Course {
    private int courseId;
    private String name;
    private String instructor;
    private int credits;

    // Constructor
    public Course(int courseId, String name, String instructor, int credits) {
        this.courseId = courseId;
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", name=" + name + ", instructor=" + instructor + ", credits=" + credits + "]";
    }
}

