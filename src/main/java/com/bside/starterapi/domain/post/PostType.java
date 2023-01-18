package com.bside.starterapi.domain.post;

public enum PostType {
    BOOK(Values.BOOK),
    MEDIA(Values.MEDIA),
    DISPLAY(Values.DISPLAY);

    public final String value;

    PostType(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String BOOK = "BOOK";
        public static final String MEDIA = "MEDIA";
        public static final String DISPLAY = "DISPLAY";
    }
}
