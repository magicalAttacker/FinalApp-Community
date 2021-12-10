package ml.magicalattacker.finalapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ml.magicalattacker.finalapp.LoginActivity;
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
        isPass = new UserUtils(context, userEntry).isPass();
        if (isPass)
            mText.postValue(userEntry.getUsername());
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
        } else {
            headView.setOnClickListener(v -> {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            });
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}