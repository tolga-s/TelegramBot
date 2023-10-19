package telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private boolean firstEntry = true;
    Menu menu = new Menu();
    Map<Long, Game> userGames = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getFirstName();
        String message = update.getMessage().getText();
        System.out.println(message);

        Game game = userGames.get(chatId);
        if (game == null) {
            game = new Game();
            userGames.put(chatId, game);
        }

        if (firstEntry) {
            sendResponse(chatId, menu.firstWelcome(userName));
            firstEntry = false;
        }
        else if (game.isOn()) {
            sendResponse(chatId, game.playGame(message));
            game.setPlayGame(game.state());

            if (!game.isOn()) {
                sendResponse(chatId, game.end(userName));
                game.clearField();
            }
        }
        else if (message.equalsIgnoreCase("play") || message.equalsIgnoreCase("/play") || message.equalsIgnoreCase("/playagain")) {
            sendResponse(chatId, "Alright, let's play then! 🎇");
            try {
                sendResponse(chatId, game.setGame());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            game.setPlayGame(true);
        }
        else {
            sendResponse(chatId, menu.menu(message, userName));
        }
    }

    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);
        msg.setParseMode("HTML");

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6614741073:AAEJnIamUmw9PZL-5bfza94U6WcMqePlRDU";
    }

    @Override
    public String getBotUsername() {
        return "AW2023_bot";
    }
}
