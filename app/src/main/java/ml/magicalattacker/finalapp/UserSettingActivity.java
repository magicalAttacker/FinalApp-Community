package ml.magicalattacker.finalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UserSettingActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String username;
    String password;
    final int ALTER_CALLBACK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        init();
    }

    public void signOut(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void init() {
        SqlHelper sqlHelper = new SqlHelper(this, "database", null, 1);
        db = sqlHelper.getWritableDatabase();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
    }

    public void alterPass(View view) {
        Intent intent = new Intent(this, AlterPasswordActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivityForResult(intent, ALTER_CALLBACK);
    }

    public void destoryUser(View view) {
        db.delete("user", "username = ?", new String[]{username});
        Toast.makeText(this, "销毁账号成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void signOutForCallback() {
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALTER_CALLBACK) {
            if (resultCode == RESULT_OK) {
                signOutForCallback();
            }
        }
    }
}