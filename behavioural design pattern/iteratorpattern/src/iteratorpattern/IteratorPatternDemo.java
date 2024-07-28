import java.util.ArrayList;
import java.util.List;

// Iterator Interface
interface GradeIterator {
    boolean hasNext();
    Grade next();
}

// ConcreteIterator
class GradebookIterator implements GradeIterator {
    private List<Grade> grades;
    private int position;

    public GradebookIterator(List<Grade> grades) {
        this.grades = grades;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < grades.size();
    }

    @Override
    public Grade next() {
        return grades.get(position++);
    }
}

// Aggregate Interface
interface Gradebook {
    GradeIterator createIterator();
}

// ConcreteAggregate
class StudentGradebook implements Gradebook {
    private List<Grade> grades;

    public StudentGradebook() {
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    @Override
    public GradeIterator createIterator() {
        return new GradebookIterator(grades);
    }
}

// Grade class
class Grade {
    private String studentName;
    private String subject;
    private int score;

    public Grade(String studentName, String subject, int score) {
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
    }

    @Override
    public String toString() {
        return studentName + " - " + subject + ": " + score;
    }
}

// Client Code
public class IteratorPatternDemo {
    public static void main(String[] args) {
        StudentGradebook gradebook = new StudentGradebook();
        gradebook.addGrade(new Grade("Alice", "Math", 90));
        gradebook.addGrade(new Grade("Bob", "Science", 85));
        gradebook.addGrade(new Grade("Charlie", "History", 92));

        GradeIterator iterator = gradebook.createIterator();
        while (iterator.hasNext()) {
            Grade grade = iterator.next();
            System.out.println(grade);
        }
    }
}
