// Question.java
interface Question {
    void display();
}

// MultipleChoiceQuestion.java
class MultipleChoiceQuestion implements Question {
    @Override
    public void display() {
        System.out.println("This is a multiple-choice question.");
    }
}

// TrueFalseQuestion.java
class TrueFalseQuestion implements Question {
    @Override
    public void display() {
        System.out.println("This is a true/false question.");
    }
}

// ShortAnswerQuestion.java
class ShortAnswerQuestion implements Question {
    @Override
    public void display() {
        System.out.println("This is a short answer question.");
    }
}

// QuestionFactory.java
abstract class QuestionFactory {
    public abstract Question createQuestion();
}

// MultipleChoiceFactory.java
class MultipleChoiceFactory extends QuestionFactory {
    @Override
    public Question createQuestion() {
        return new MultipleChoiceQuestion();
    }
}

// TrueFalseFactory.java
class TrueFalseFactory extends QuestionFactory {
    @Override
    public Question createQuestion() {
        return new TrueFalseQuestion();
    }
}

// ShortAnswerFactory.java
class ShortAnswerFactory extends QuestionFactory {
    @Override
    public Question createQuestion() {
        return new ShortAnswerQuestion();
    }
}

// FactoryMethodDemo.java
public class FactoryMethodDemo {
    public static void main(String[] args) {
        QuestionFactory mcFactory = new MultipleChoiceFactory();
        Question mcQuestion = mcFactory.createQuestion();
        mcQuestion.display();

        QuestionFactory tfFactory = new TrueFalseFactory();
        Question tfQuestion = tfFactory.createQuestion();
        tfQuestion.display();

        QuestionFactory saFactory = new ShortAnswerFactory();
        Question saQuestion = saFactory.createQuestion();
        saQuestion.display();
    }
}
