import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.LinkedList;


public class AlarmTuringBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "AlarmTuringBot";
    }

    @Override
    public String getBotToken() {
        return "1931529186:AAHZ475A6MZGffo3nqsBp1UATkp07WBmg-Y";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage()!=null) {
            String msg = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            LinkedList<SendMessage> msgSendList = new LinkedList<>();

            if (msg.equals("/chatid") || msg.equals("/chatid@AlarmTuringBot")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(chatId);

                msgSendList.add(sendMessage);
            } else if (msg.equals("/start") || msg.equals("/start@AlarmTuringBot")) {
                String startMsg = "AlarmTuringBot Started!",
                        chatIdMsg = "Your ChatID is: " + chatId,
                        msg3 = "Remember to copy the ChatID on the AlarmTuring App with the \t\"-\" symbol if you are in a group chat.";
                SendMessage sendMessage1 = new SendMessage(chatId, startMsg);
                SendMessage sendMessage2 = new SendMessage(chatId, chatIdMsg);
                SendMessage sendMessage3 = new SendMessage(chatId, msg3);

                msgSendList.add(sendMessage1);
                msgSendList.add(sendMessage2);
                msgSendList.add(sendMessage3);
            }else if (msg.equals("/help") || msg.equals("/help@AlarmTuringBot")) {
                String helpMsg1 = "Welcome to the AlarmTuring Bot! To connect your app to this Bot and chat follow this steps:";
                String helpMsg2 = "1 - Get the chatID of this chat using the /chatid command with me :)";
                String helpMsg3 = "2 - Open the AlarmTuringApp and activate the Bot function in the bottom panel, and insert the chatID in the form below. (Do not forget the \"-\" symbol if you are in a group chat).";
                String helpMsg4 = "3 - Click the confirm button. If all is correct I'll send you a confirm message :)";
                String helpMsg5 = "Now enjoy the app and see what happens all from this chat!";

                SendMessage m1 = new SendMessage(chatId, helpMsg1);
                SendMessage m2 = new SendMessage(chatId, helpMsg2);
                SendMessage m3 = new SendMessage(chatId, helpMsg3);
                SendMessage m4 = new SendMessage(chatId, helpMsg4);
                SendMessage m5 = new SendMessage(chatId, helpMsg5);

                msgSendList.add(m1);
                msgSendList.add(m2);
                msgSendList.add(m3);
                msgSendList.add(m4);
                msgSendList.add(m5);
            }

            //EXECUTE Send of the msg list
            for(SendMessage sendMsg : msgSendList){
                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    System.err.println("ERRORE INVIO MESSAGGIO : \"" + sendMsg.getText() + "\"");
                }
            }

            //LOG MESSAGGI IN ARRIVO
            boolean isGroup = update.getMessage().isGroupMessage();
            String userName = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
            if(!isGroup)
                System.out.println(chatId + " - " + userName + ": \"" + msg + "\"");
            else
                System.out.println(chatId + ":" + "Group" + " - " + userName + ": \"" + msg + "\"");
        }


        /* REPLY
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            String reply = "Ciao " + update.getMessage().getFrom().getFirstName() +" "+ update.getMessage().getFrom().getLastName() + ": \"" + msg + "\"";
            sendMessage.setText(reply);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                // gestione errore in invio
            }
        */
    }

    public void sendMessages( String chatId, String msg){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("ERRORE INVIO MESSAGGIO!");
            // gestione errore in invio
        }
    }

    public void sendPhoto(String chatId, File photo){
        InputFile photo2 = new InputFile(photo);
        SendPhoto sendPhoto = new SendPhoto(chatId, photo2);

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }
    }

    public void sendVideo(String chatId, File video){
        InputFile video2 = new InputFile(video);
        SendVideo sendVideo = new SendVideo(chatId, video2);

        try {
            execute(sendVideo);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }
    }


}
