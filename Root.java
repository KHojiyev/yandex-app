package module3.lesson10_TelegramBot.task1_YandexDictionary;

import com.google.gson.Gson;
import module3.lesson9_API.tesk2_Translate.model.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Root extends TelegramLongPollingBot {
    public static boolean selectLanguage = false;
    public static String answer = "";
    public static String language ="";
    public static ConcurrentHashMap<String, String> activeWordMap = new ConcurrentHashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println(update.getMessage().getChatId());
        if (update.hasMessage() ) {
            SendMessage sendMessage = new SendMessage();
            switch (message.getText()) {
                case ButtonAndTexts.START:
                    fromButtons(sendMessage);
                    sendMessage.setText(" Hello✋ " + update.getMessage().getChat().getFirstName() + "\n" +
                            "Admin: @KhojiyevML\n\n" +
                            "Select language ");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "english-russian":
                    selectLanguage = true;
                    language = "en-ru";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "russian-english":
                    selectLanguage = true;
                    language = "ru-en";
                    sendMessage.setText("напишите слово: ");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "english-turkish":
                    selectLanguage = true;
                    language = "en-tr";
                    sendMessage.setText("Enter word:");

                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "english-uzbek":
                    selectLanguage = true;
                    language = "en-uzb";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "russian-turkish":
                    selectLanguage = true;
                    language = "ru-tr";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "russian-uzbek":
                    selectLanguage = true;
                    language = "ru-uz";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "turkish-english":
                    selectLanguage = true;
                    language = "tr-en";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "turkish-uzbek":
                    selectLanguage = true;
                    language = "tr-uz";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "turkish-russian":
                    selectLanguage = true;
                    language = "tr-ru";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "uzbek-russian":
                    selectLanguage = true;
                    language = "uz-ru";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "uzbek-english":
                    selectLanguage = true;
                    language = "uz-en";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "uzbek-turkish":
                    selectLanguage = true;
                    language = "uz-tr";
                    sendMessage.setText("Enter word:");
                    try {
                        sendMessage.setChatId(update.getMessage().getChatId());
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    activeWordMap.put(update.getMessage().getChat().getUserName(),update.getMessage().getText());
                    break;

            }
        }
        if (activeWordMap.size()>0) {
            SendMessage sendMessage = new SendMessage();
            if (!language.equals("")) {
                translateMachine(activeWordMap.get(update.getMessage().getChat().getUserName()), language);
                if (!answer.equals("")) {
                    sendMessage.setText(answer);
                } else {
                    sendMessage.setText("i could not found word sorry\uD83D\uDE47\u200D♂️");
                }
                answer = "";
                selectLanguage = false;
                activeWordMap.remove(update.getMessage().getChat().getUserName());
                try {
                    sendMessage.setChatId(update.getMessage().getChatId());
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else{
                sendMessage.setText("Select Language");
                answer = "";
                try {
                    sendMessage.setChatId(update.getMessage().getChatId());
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fromButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        row1.add(new KeyboardButton("english-russian"));
        row1.add(new KeyboardButton("english-turkish"));
        row1.add(new KeyboardButton("english-uzbek"));
        row2.add(new KeyboardButton("russian-english"));
        row2.add(new KeyboardButton("russian-turkish"));
        row2.add(new KeyboardButton("russian-uzbek"));
        row3.add(new KeyboardButton("turkish-english"));
        row3.add(new KeyboardButton("turkish-russian"));
        row3.add(new KeyboardButton("turkish-uzbek"));
        row4.add(new KeyboardButton("uzbek-english"));
        row4.add(new KeyboardButton("uzbek-russian"));
        row4.add(new KeyboardButton("uzbek-turkish"));
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        keyboardRows.add(row4);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private static void translateMachine(String searchWord, String language) {
        Gson gson = new Gson();
        try {
            URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20210406T070427Z.68976b0a134098e6.c44e58158ee48474d0d56bace9f632c0e026d591&lang=" + language + "&text=" + searchWord);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Translate translate = gson.fromJson(bufferedReader, Translate.class);
            String wordSpelling = "";
            for (Def def : translate.getDef()) {
                if(def.ts!=null){
                    wordSpelling=def.ts;
                }
                for (Tr tr : def.tr) {
                    answer += "\n(" + def.pos + "){ "+def.ts+" }"+ " // " + tr.getText() + "\n";
                    if (tr.getMean() != null) {
                        for (Mean mean : tr.getMean()) {
                            answer+="   Mean: " + mean.getText()+"\n";
                        }
                    }
                    if (tr.getEx() != null) {
                        for (Ex ex : tr.getEx()) {
                            answer += "     Examples: " + ex.text + "\n";
                            if (ex.getTr() != null) {
                                for (Tr tr1 : ex.getTr()) {
                                    answer += "      -" + tr1.getText() + "\n";
                                    System.out.println();
                                }
                            }
                        }
                    }
                    if (tr.getSyn() != null && tr.getSyn().length > 0) {
                        for (Syn syn : tr.getSyn()) {
                            answer += "     Syn: (" + syn.getPos() + ") " + syn.getText() + "\n";
                            System.out.println();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "http://t.me/translateYand_bot";
    }

    @Override
    public String getBotToken() {
        return "1740683985:AAE0cBL8tndhH2gYekNyKxnU3F_StCllJyQ";
    }
}
