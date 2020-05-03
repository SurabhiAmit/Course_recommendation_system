package edu.gatech.courseManagement.entity;

public class Record implements Comparable<Record> {
    private long UUSID;
    private long courseID;
    private int term;
    private int year;
    private char letterGrade;
    private Student student;
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(char grade) {
        this.letterGrade = grade;
    }

    public long getUUSID() {
        return UUSID;
    }

    public void setUUSID(long UUSID) {
        this.UUSID = UUSID;
    }
    public Boolean isPass(){
        return
                        Character.toUpperCase( letterGrade) == 'A' ||
                        Character.toUpperCase( letterGrade) == 'B' ||
                        Character.toUpperCase( letterGrade) == 'C';
    }


    @Override
    public int compareTo(Record record) {
        if( record.getCourseID() == getCourseID() &&
                record.getUUSID() == getUUSID() &&
                record.getYear() == getYear() &&
                record.getTerm() == getTerm())
        {
            return 0;
        }
        else if( record.getCourseID() == getCourseID() &&
                record.getUUSID() == getUUSID() )
        {
            if( getYear() == record.getYear() )
            {
                if( getTerm() > record.getTerm() ) return 1;
                else return -1;
            }
            else if( getYear() > record.getYear() ) return 1;
            else return -1;
        }
        else
            return 0;
    }
}
