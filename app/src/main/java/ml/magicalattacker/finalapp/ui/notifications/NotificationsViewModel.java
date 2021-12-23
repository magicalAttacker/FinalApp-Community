package ml.magicalattacker.finalapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import ml.magicalattacker.finalapp.Base64Utils;
import ml.magicalattacker.finalapp.LoginActivity;
import ml.magicalattacker.finalapp.R;
import ml.magicalattacker.finalapp.UserEntry;
import ml.magicalattacker.finalapp.UserSettingActivity;
import ml.magicalattacker.finalapp.UserUtils;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private UserEntry userEntry;
    private boolean isPass = false;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("轻点头像以登录");
    }

    public void loadUserEntry(UserEntry userEntry) {
        this.userEntry = userEntry;
    }

    public void updateUsername(Context context) {
        Date date = new Date();
        int now = date.getHours();
        String status = (now < 12) ? "上午" : "下午";
        isPass = new UserUtils(context, userEntry).isPass();
        if (isPass)
            mText.postValue(userEntry.getUsername() + "， "+ status + "好");
        else {
            mText.postValue("轻点头像以登录");
        }
    }

    public void updateHead(Context context, ImageView headView) {
        if (isPass) {
            headView.setOnClickListener(v -> {
                Intent intent = new Intent(context, UserSettingActivity.class);
                intent.putExtra("username", userEntry.getUsername());
                intent.putExtra("password", userEntry.getPassword());
                context.startActivity(intent);
            });
            final String key = "";
            try {
                String keyName = Base64Utils.setKey(key);
                if (userEntry.getUsername().equals(keyName)) {
                    Glide.with(context).load(R.drawable.head).into(headView);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            headView.setOnClickListener(v -> {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            });
            headView.setImageResource(R.drawable.outline_account_circle_24);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}