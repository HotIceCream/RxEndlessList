package ru.hoticecream.rxendlesslist.data;

import java.util.Locale;

public class DataItem {

    private int id;
    private String message;

    public DataItem(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return String.format(Locale.getDefault(), "%s №%d", message, id);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataItem dataItem = (DataItem) o;

        if (id != dataItem.id) return false;
        return message != null ? message.equals(dataItem.message) : dataItem.message == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
