package module3.lesson10_TelegramBot.task1_YandexDictionary.model;

import java.util.List;

public class Translate {
    private Head head;
    private List<Def> def;

    public void setHead(Head head) {
        this.head = head;
    }

    public Head getHead() {
        return this.head;
    }

    public void setDef(List<Def> def) {
        this.def = def;
    }

    public List<Def> getDef() {
        return this.def;
    }

}




