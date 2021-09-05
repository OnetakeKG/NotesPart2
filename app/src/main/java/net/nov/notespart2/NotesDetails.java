package net.nov.notespart2;

import java.util.Date;

public class NotesDetails {

    private String noteName;
    private String details;
    private String date;

    public NotesDetails(String noteName, String details, String date) {
        this.noteName = noteName;
        this.details = details;
        this.date = date;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }
}
