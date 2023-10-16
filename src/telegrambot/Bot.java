package telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.quiz.Quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private Quiz q;

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        System.out.println(messageReceived);

        // Start to evaluate the messages you received
        // 1. Main menu
        if (messageReceived.toLowerCase().startsWith("hello")) {
            sendResponse(chatId, "Welcome, human being 🤖");
            sendResponse(chatId, "Let's have some fun now.... 😎");
            sendResponse(chatId, "1. Type 'quiz', if you want me to ask you something smart");
            sendResponse(chatId, "2. Type any text, if you want me to count its letters");
        }

        // 2. quiz mode
        if (messageReceived.toLowerCase().startsWith("quiz")) {
            q = new Quiz();
            sendResponse(chatId, q.getRandomQuestion().toString());
        }

        // 3. create a poll
        if (messageReceived.toLowerCase().startsWith("poll")) {
            sendPollToUser(chatId);
        } else

        // 4. analyze the text
        {
            sendResponse(chatId, "I counted the letters in your message: " + messageReceived);
            sendResponse(chatId, countLetters(messageReceived).toString());
        }
    }

    /**
     * This method counts the letters of a provided string parameter and saves them into a Map which is returned.
     * All letters are converted into lowercase. Digits and other non-letter characters are not counted.
     * @param input the string to be evaluated
     * @return a map containing the result of
     */
    public Map<Character, Integer> countLetters(String input) {
        Map<Character, Integer> letterCountMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            // Convert the character to lowercase to count both uppercase and lowercase letters together
            char lowercaseC = Character.toLowerCase(c);

            // Check if the character is a letter (alphabet character)
            if (Character.isLetter(lowercaseC)) {
                letterCountMap.put(lowercaseC, letterCountMap.getOrDefault(lowercaseC, 0) + 1);
            }
        }
        return letterCountMap;
    }

    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPollToUser(long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chatId);
        sendPoll.setQuestion("Which programming language do you like the most?");
        sendPoll.setOptions(List.of("Java", "Python", "JavaScript", "C++"));
        try {
            execute(sendPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6614741073:AAEJnIamUmw9PZL-5bfza94U6WcMqePlRDU";  // TODO: insert your bot token here!
    }

    @Override
    public String getBotUsername() {
        return "AW2023_bot";  // TODO: insert your bots username here
    }
}
