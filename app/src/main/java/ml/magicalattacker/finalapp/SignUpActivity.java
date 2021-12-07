package ml.magicalattacker.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText confirm;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        username = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPassword2);
        confirm = findViewById(R.id.editTextTextPassword3);
        SqlHelper sqlHelper = new SqlHelper(this, "database", null, 1);
        db = sqlHelper.getWritableDatabase();
    }

    public void signUp(View view) {
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        String inputConfirm = confirm.getText().toString();
        if (!inputUsername.isEmpty() && !inputPassword.isEmpty() && inputPassword.equals(inputConfirm) && !isRegisted(inputUsername)) {
            ContentValues values = new ContentValues();
            values.put("username", inputUsername);
            values.put("password", inputPassword);
            db.insert("user", null, values);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        } else if (!inputPassword.equals(inputConfirm)) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        } else if (isRegisted(inputUsername)) {
            Toast.makeText(this, "该账号已注册", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册信息未填写完整", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isRegisted(String username) {
        @SuppressLint("Recycle") Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String usernameDB = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            if (username.equals(usernameDB))
                return true;
        }
        return false;
    }
}