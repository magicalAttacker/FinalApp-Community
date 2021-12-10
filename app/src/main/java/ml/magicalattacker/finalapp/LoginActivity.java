package ml.magicalattacker.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        SqlHelper sqlHelper = new SqlHelper(this, "database", null, 1);
        db = sqlHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void autoLogin(String username, String password) {
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    public void login(View view) {
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        if (!inputUsername.isEmpty() && !inputPassword.isEmpty() && isRegisted(inputUsername, inputPassword)) {
            autoLogin(inputUsername, inputPassword);
            finish();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else if (!isRegisted(inputUsername, inputPassword) && !inputUsername.isEmpty() && !inputPassword.isEmpty()) {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录信息不完整", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void listUser(View view) {
        recyclerView.setVisibility(View.VISIBLE);
        List<UserEntry> list = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String usernameDB = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String passwordDB = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            list.add(new UserEntry(usernameDB, passwordDB));
        }
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    private boolean isRegisted(String username, String password) {
        @SuppressLint("Recycle") Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String usernameDB = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String passwordDB = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            if (username.equals(usernameDB) && password.equals(passwordDB))
                return true;
        }
        return false;
    }

}