package telegrambot.quiz;

import java.util.List;
import java.util.Random;

public class Quiz {
    private List<Question> questionsList = this.initQuestions();
    private int score = 0;

    public Quiz() {
    }

    private List<Question> initQuestions() {
        return List.of(
                new Question("Japan", "What is the name of the popular Japanese dish made from fermented soybeans?",
                        List.of("Sushi", "Ramen", "Miso", "Tempura"), "Miso"),
                new Question("Shakespeare", "In which famous Shakespearean play does the character Othello appear?",
                        List.of("Macbeth", "Romeo and Juliet", "Othello", "Hamlet"), "Othello"),
                new Question("Human Body", "What is the largest organ in the human body?",
                        List.of("Brain", "Liver", "Heart", "Skin"), "Skin"),
                new Question("Paintings", "Who painted the famous artwork \"Mona Lisa\"?",
                        List.of("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), "Leonardo da Vinci"));
    }

    public boolean addQuestion(Question q) {
        return this.questionsList.add(q);
    }

    public Question getRandomQuestion() {
        Random r = new Random();
        int index = r.nextInt(0, this.questionsList.size());
        return (Question) this.questionsList.get(index);
    }
}
