// Notifier Interface
interface Notifier {
    void send(String message);
}

// BasicNotifier Class
class BasicNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending basic notification: " + message);
    }
}

// NotifierDecorator Abstract Class
abstract class NotifierDecorator implements Notifier {
    protected Notifier decoratedNotifier;

    public NotifierDecorator(Notifier decoratedNotifier) {
        this.decoratedNotifier = decoratedNotifier;
    }

    @Override
    public void send(String message) {
        decoratedNotifier.send(message);
    }
}

// EmailNotifier Class
class EmailNotifier extends NotifierDecorator {
    public EmailNotifier(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        decoratedNotifier.send(message);
        System.out.println("Sending email notification: " + message);
    }
}

// SMSNotifier Class
class SMSNotifier extends NotifierDecorator {
    public SMSNotifier(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        decoratedNotifier.send(message);
        System.out.println("Sending SMS notification: " + message);
    }
}

// PushNotifier Class
class PushNotifier extends NotifierDecorator {
    public PushNotifier(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        decoratedNotifier.send(message);
        System.out.println("Sending push notification: " + message);
    }
}

// DecoratorPatternDemo Class
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // Basic notification
        Notifier notifier = new BasicNotifier();
        notifier.send("This is a basic notification.");

        // Notification with email
        Notifier emailNotifier = new EmailNotifier(notifier);
        emailNotifier.send("This is an email notification.");

        // Notification with SMS
        Notifier smsNotifier = new SMSNotifier(notifier);
        smsNotifier.send("This is an SMS notification.");

        // Notification with push
        Notifier pushNotifier = new PushNotifier(notifier);
        pushNotifier.send("This is a push notification.");

        // Combining multiple decorators
        Notifier allNotifier = new PushNotifier(new SMSNotifier(new EmailNotifier(notifier)));
        allNotifier.send("This is a combined notification.");
    }
}
