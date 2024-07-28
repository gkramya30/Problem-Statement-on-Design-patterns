// WebService.java
interface WebService {
    void request();
}

// StudentWebService.java
class StudentWebService {
    public void fetchStudentData() {
        System.out.println("Fetching student data from StudentWebService.");
    }
}

// CourseWebService.java
class CourseWebService {
    public void fetchCourseData() {
        System.out.println("Fetching course data from CourseWebService.");
    }
}

// StudentServiceAdapter.java
class StudentServiceAdapter implements WebService {
    private StudentWebService studentWebService;

    public StudentServiceAdapter(StudentWebService studentWebService) {
        this.studentWebService = studentWebService;
    }

    @Override
    public void request() {
        studentWebService.fetchStudentData();
    }
}

// CourseServiceAdapter.java
class CourseServiceAdapter implements WebService {
    private CourseWebService courseWebService;

    public CourseServiceAdapter(CourseWebService courseWebService) {
        this.courseWebService = courseWebService;
    }

    @Override
    public void request() {
        courseWebService.fetchCourseData();
    }
}

// AdapterPatternDemo2.java
public class AdapterPatternDemo2 {
    public static void main(String[] args) {
        WebService studentService = new StudentServiceAdapter(new StudentWebService());
        WebService courseService = new CourseServiceAdapter(new CourseWebService());

        System.out.println("Using StudentServiceAdapter:");
        studentService.request();

        System.out.println("\nUsing CourseServiceAdapter:");
        courseService.request();
    }
}
