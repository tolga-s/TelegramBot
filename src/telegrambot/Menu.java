package telegrambot;

public class Menu {

    public String menu(String message, String userName) {
        boolean startPlay = false;

        // Hello after Welcome
        if (message.equalsIgnoreCase("/start") || message.equalsIgnoreCase("start")) {
            String hello = "👋 Hello " + userName + "!  🎉\n" +
                    """
                                    
                    Ready for some fun? 🤖
                                    
                    If you're ready to dive into the game, simply type /game.
                    
                    Need help? Just type /help.
                    """;
            return hello;
        }

        // Help menu
        if (message.equalsIgnoreCase("help") || message.equalsIgnoreCase("/help")) {
            String help = """
                    Here is a list of all the commands you can use:
                                        
                    🚀 /start: Gives a short introduction
                    🎮 /game: Takes you to the game menu
                    🆘 /help: Takes you back to this list
                    """;
            return help;
        }

        if (message.equalsIgnoreCase("game") || message.equalsIgnoreCase("/game")) {
            String game = """
                    Great! Let's see what I can do for you:
                    
                    🎮 /play: Start the game!
                    🏆 /leaderboard: Check out the best players
                    """;
            return game;
        }
        return null;
    }

    public String firstWelcome(String userName) {
        String firstWelcome = "Welcome to HangmanBot, " + userName + "!\n\n" +
                """
                🎉 Are you ready to embark on a thrilling word-guessing journey with me? Allow me to introduce myself - I'm HangmanBot, your trusty companion for all word games!
                                
                🤖 Despite my unique name, I promise to make your experience engaging and fun. Get ready to put your vocabulary skills to the test as you dive into the world of Hangman.
                
                ❓The rules are simple: I'll pick a secret word, and it's your job to guess the letters one by one. Be careful though; you've got a limited number of attempts to save our stick-figure friend from the gallows!
                                
                🧩 To kick off the game, simply type /play and let the word-guessing fun begin. Don't worry if you need anything; I've got you with /help
                """;
        return firstWelcome;
    }
}
