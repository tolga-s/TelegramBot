package telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    Map<Long, Game> userGames = new HashMap<>();
    Menu menu = new Menu();
    private boolean firstEntry = true;

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
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
            game.setState(game.state());

            if (!game.isOn()) {
                sendResponse(chatId, game.end(userName));
                sendPhoto(chatId, game.pictureDescr(), game.picture());
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
            game.setState(true);
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

    public void sendPhoto(Long chatId, String photoCaption, String photoFilePath) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(photoCaption);
        sendPhoto.setPhoto(new InputFile(photoFilePath));

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "XXX";
    }

    @Override
    public String getBotUsername() {
        return "XXX";
    }
}
