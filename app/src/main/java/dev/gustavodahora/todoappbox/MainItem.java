package dev.gustavodahora.todoappbox;

public class MainItem {
    private int id;
    private String titleId;
    private String textId;

    public MainItem(int id, String titleId, String textId) {
        this.id = id;
        this.titleId = titleId;
        this.textId = textId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }
}
