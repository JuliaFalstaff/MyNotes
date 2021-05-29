package com.example.mynotes;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class CardsNoteMapping {
    public static class Fields {
        public final static String PICTURE = "picture";
        public final static String TITLE = "title";
        public final static String DESCRIPTION = "description";
        public final static String SUBTITLE = "subtitle";
    }

    public static Note toCardData (String id, Map<String, Object> doc) {
        long indexPic = (long) doc.get(Fields.PICTURE);
        String title = (String) doc.get(Fields.TITLE);
        String subtitle = (String) doc.get(Fields.SUBTITLE);
        Note note = new Note(title, subtitle, PictureIndexConverter.getPictureByIndex((int) indexPic));
        note.setId(id);
        return note;
    }

    public static Map<String,Object> toDocument (Note note) {
        Map<String,Object> doc = new HashMap<>();
        doc.put(Fields.TITLE, note.getTitle());
        doc.put(Fields.SUBTITLE, note.getSubTitle());
        doc.put(Fields.PICTURE,  PictureIndexConverter.getIndexByPicture(note.getPicture()));
        return doc;
    }

}
