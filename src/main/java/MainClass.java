import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;

public class MainClass {
    public static void main(String[] args) {
        AlarmTuringBot bot = new AlarmTuringBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

    /*
        //SLEEP 1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //MESSAGGIO DI AVVIO BOT
        //bot.sendMessages("-480710730","AlarmTuringBot Started!");
    */


    /*
        File photo = new File("foto_Antonio.png");
        for(int i=0; i<1; i++){
            //bot.sendPhoto("-480710730", photo);
        }

        File video = new File("video_prova.");
        //bot.sendVideo("-480710730", video);
    */