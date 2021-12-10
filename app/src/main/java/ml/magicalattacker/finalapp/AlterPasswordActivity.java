package ml.magicalattacker.finalapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlterPasswordActivity extends AppCompatActivity {
    EditText oldPasswordView;
    EditText newPasswordView;
    EditText repeatPasswordView;
    String username;
    String oldPassword;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        init();
    }

    public void init() {
        SqlHelper sqlHelper = new SqlHelper(this, "database", null, 1);
        db = sqlHelper.getWritableDatabase();
        oldPasswordView = findViewById(R.id.editTextTextPassword6);
        newPasswordView = findViewById(R.id.editTextTextPassword5);
        repeatPasswordView = findViewById(R.id.editTextTextPassword4);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        oldPassword = intent.getStringExtra("password");
    }

    public void alterPassword(View view) {
        String inputOldPassword = oldPasswordView.getText().toString();
        String inputNewPassword = newPasswordView.getText().toString();
        String inputRepeatPassword = repeatPasswordView.getText().toString();
        boolean isCheckPass = inputOldPassword.equals(oldPassword);
        boolean isFinishFill = !inputOldPassword.isEmpty() && !inputNewPassword.isEmpty() && !inputRepeatPassword.isEmpty();
        boolean isEqual = inputNewPassword.equals(inputRepeatPassword);
        if (isCheckPass && isFinishFill) {
            if (isEqual) {
                ContentValues values = new ContentValues();
                values.put("password", inputNewPassword);
                db.update("user", values, "username = ?", new String[]{username});
                Toast.makeText(this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                this.setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "确认密码与新密码输入不一致", Toast.LENGTH_SHORT).show();
            }
        } else if (!isCheckPass) {
            Toast.makeText(this, "旧密码输入不正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "信息未填写完整", Toast.LENGTH_SHORT).show();
        }
    }
}