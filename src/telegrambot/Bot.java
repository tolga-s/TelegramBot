package telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private boolean firstWelcome = true;
    Menu menu = new Menu();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        System.out.println(message);

        if (firstWelcome) {
            sendResponse(chatId, menu.firstWelcome(message));
            firstWelcome = false;
        } else {
            sendResponse(chatId, menu.menu(message));
        }
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

    @Override
    public String getBotToken() {
        return "6614741073:AAEJnIamUmw9PZL-5bfza94U6WcMqePlRDU";
    }

    @Override
    public String getBotUsername() {
        return "AW2023_bot";
    }
}
