package telegrambot;

public class Menu {

    public String menu(String message) {

        // Welcome Text
        if (message.toLowerCase().startsWith("/start") || message.toLowerCase().startsWith("start")) {
            String hello = """
                    👋 Welcome human! 🎉
                                    
                    Ready for some fun? 🤖
                                    
                    If you're ready to dive into the game menu, simply type /game.
                    
                    Need help? Just type /help.
                    """;
            return hello;
        }

        // Help menu
        if (message.toLowerCase().startsWith("help") || message.toLowerCase().startsWith("/help")) {
            String help = """
                    Here is a list of all the commands you can use:
                                        
                    🚀 /start: Goes back to the introduction
                    🎮 /game: Takes you to the game menu
                    🆘 /help: Takes you back to this list
                    """;
            return help;
        }

        if (message.toLowerCase().startsWith("game") || message.toLowerCase().startsWith("/game")) {
            String game = """
                    Great! Let's see what I can do for you:
                    
                    🎮 /play: Start the game!
                    🏆 /leaderboard: Check out the best players
                    """;
            return game;
        }

        return "XX";
    }

    public String firstWelcome(String message) {
        String firstWelcome = """
                Welcome to HangmanBot!
                                
                🎉 Are you ready to embark on a thrilling word-guessing journey with me? Allow me to introduce myself - I'm HangmanBot, your trusty companion for all word games!
                                
                🤖 Despite my unique name, I promise to make your experience engaging and fun. Get ready to put your vocabulary skills to the test as you dive into the world of Hangman.
                
                ❓The rules are simple: I'll pick a secret word, and it's your job to guess the letters one by one. Be careful though; you've got a limited number of attempts to save our stick-figure friend from the gallows!
                                
                🧩 To kick off the game, simply type /play and let the word-guessing fun begin. Don't worry if you need anything; I've got you with /help
                """;
        return firstWelcome;
    }
}
