import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, Participant sender);
    void addParticipant(Participant participant);
}

// ConcreteMediator
class ClassroomChatMediator implements ChatMediator {
    private List<Participant> participants;

    public ClassroomChatMediator() {
        this.participants = new ArrayList<>();
    }

    @Override
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    @Override
    public void sendMessage(String message, Participant sender) {
        for (Participant participant : participants) {
            if (participant != sender) {
                participant.receiveMessage(message);
            }
        }
    }
}

// Colleague Interface
abstract class Participant {
    protected ChatMediator mediator;
    protected String name;

    public Participant(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}

// ConcreteColleague
class Student extends Participant {
    public Student(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " (Student) sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " (Student) receives: " + message);
    }
}

class Teacher extends Participant {
    public Teacher(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " (Teacher) sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " (Teacher) receives: " + message);
    }
}

// Client Code
public class MediatorPatternDemo {
    public static void main(String[] args) {
        ChatMediator mediator = new ClassroomChatMediator();

        Participant student1 = new Student(mediator, "Alice");
        Participant student2 = new Student(mediator, "Bob");
        Participant teacher = new Teacher(mediator, "Mr. Smith");

        mediator.addParticipant(student1);
        mediator.addParticipant(student2);
        mediator.addParticipant(teacher);

        student1.sendMessage("Hello, everyone!");
        teacher.sendMessage("Good morning, class!");
    }
}
