package module3.lesson10_TelegramBot.task1_YandexDictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Def {
    public String text;
    public String pos;
    public String ts;
    public Tr[] tr;
}
