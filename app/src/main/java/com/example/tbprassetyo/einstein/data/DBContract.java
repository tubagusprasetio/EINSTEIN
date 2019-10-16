package com.example.tbprassetyo.einstein.data;

import android.provider.BaseColumns;

public class DBContract {
    public static final class MessageEntry implements BaseColumns {

        public static final String TABLE_NAME = "message";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_BODY = "body";
        public static final String COLUMN_MSG_TYPE ="msgType";
    }

    public static final class AkunEntry implements BaseColumns {

        public static final String TABLE_NAME = "akun";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NISN = "nisn";
        public static final String COLUMN_KELAS ="kelas";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD ="password";
    }

    public static final class MateriEntry implements BaseColumns {
        // COMPLETED (2) Inside create a static final members for the table name and each of the db columns
        public static final String TABLE_NAME = "materi";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ACTIVITY_ID = "activityId";
        public static final String COLUMN_MATERI_NAME = "materiname";
        public static final String COLUMN_INTERACT_MODE = "interactMode";
        }

    public static final class ActivityEntry implements BaseColumns {
        // COMPLETED (2) Inside create a static final members for the table name and each of the db columns
        public static final String TABLE_NAME = "activity";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CHAT_ID = "chatId";
        public static final String COLUMN_REQUEST_NUMBER = "requestNumber";
        public static final String COLUMN_REQUEST_MATERI_NAME = "requestNameMateri";
        public static final String COLUMN_ANSWEAR_CORRECT = "answearCorrect";
        public static final String COLUMN_ANSWEAR_FAIL= "answearFail";
    }

}
