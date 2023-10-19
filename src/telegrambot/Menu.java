package telegrambot;

public class Menu {

    public String menu(String message, String userName) {
        boolean startPlay = false;

        // Hello after Welcome
        if (message.equalsIgnoreCase("/intro") || message.equalsIgnoreCase("intro")) {
            String intro = "👋 Hello " + userName + "!  🎉\n" +
                    """
                                    
                    Ready for some fun? 🤖
                                    
                    If you're ready to dive into the game, simply type /play.
                    
                    Need help? Just type /help.
                    """;
            return intro;
        }

        // Help menu
        if (message.equalsIgnoreCase("help") || message.equalsIgnoreCase("/help")) {
            String help = """
                    Here is a list of all the commands you can use:
                                        
                    🚀 /intro: Gives a short introduction
                    🎮 /play: Starts the game!
                    🆘 /help: Takes you back to this list
                    ℹ️ /info: Get to know more about the creator
                    """;
            return help;
        }

//        if (message.equalsIgnoreCase("game") || message.equalsIgnoreCase("/game")) {
//            String game = """
//                    Great! Let's see what I can do for you:
//
//                    🎮 /play: Start the game!
//                    🏆 /leaderboard: Check out the best players
//                    """;
//            return game;
//        }

        if (message.equalsIgnoreCase("info") || message.equalsIgnoreCase("/info")) {
            String info = """
                    👨‍💻 It's me, the humble creator of this bot!
                    
                    💣 I'm relatively new to the realm of Java and programming but the journey was a blast.
                    
                    🎢 While I had many ups and downs, the process was a great experience.
                    
                    🥳 Join me on this small adventure!
                    """;
            return info;
        }
        return null;
    }

    public String firstWelcome(String userName) {
        String firstWelcome = "<b>Welcome to HangmanBot, " + userName + "!\n\n</b>" +
                """
                🎉 Are you ready to embark on a thrilling word-guessing journey with me? Allow me to introduce myself - I'm HangmanBot, your trusty companion in this game!
                                
                🤖 Despite my unique name, I promise to make your experience engaging and fun. Get ready to put your vocabulary skills to the test as you dive into the world of Hangman.
                
                ❓The rules are simple: I'll pick a secret word, and it's your job to guess the letters one by one. Be careful though; you've got a limited number of attempts to save our stick-figure friend from the gallows!
                                
                🧩 To kick off the game, simply type /play and let the word-guessing fun begin. Don't worry if you need anything; I've got you with /help
                """;
        return firstWelcome;
    }
}
