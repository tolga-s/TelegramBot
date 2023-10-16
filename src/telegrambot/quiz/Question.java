package telegrambot.quiz;

import java.util.List;
import java.util.stream.Collectors;

public class Question {
    private String title;
    private String text;
    private List<String> options;
    private String solution;

    public Question(String title, String text, List<String> options, String solution) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.solution = solution;
    }

    public String toString() {
        String opt = (String)this.options.stream().collect(Collectors.joining("\n"));
        return this.title + "\n" + this.text + "\n" + opt;
    }
}
