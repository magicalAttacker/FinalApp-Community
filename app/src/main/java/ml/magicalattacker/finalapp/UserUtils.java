package ml.magicalattacker.finalapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserUtils {
    SQLiteDatabase db;
    UserEntry userEntry;

    public UserUtils(Context context, UserEntry userEntry) {
        SqlHelper sqlHelper = new SqlHelper(context, "database", null, 1);
        db = sqlHelper.getReadableDatabase();
        this.userEntry = userEntry;
    }

    public boolean isPass() {
        @SuppressLint("Recycle") Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String usernameDB = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String passwordDB = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            if (userEntry.getUsername().equals(usernameDB) && userEntry.getPassword().equals(passwordDB))
                return true;
        }
        return false;
    }
}
