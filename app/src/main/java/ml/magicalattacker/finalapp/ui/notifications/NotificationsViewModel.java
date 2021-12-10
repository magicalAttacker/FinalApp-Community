package ml.magicalattacker.finalapp.ui.notifications;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ml.magicalattacker.finalapp.UserEntry;
import ml.magicalattacker.finalapp.UserUtils;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private UserEntry userEntry;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("轻点头像以登录");
    }

    public void loadUserEntry(UserEntry userEntry) {
        this.userEntry = userEntry;
    }

    public void updateUsername(Context context) {
        boolean isPass = new UserUtils(context, userEntry).isPass();
        if (isPass)
            mText.postValue(userEntry.getUsername());
    }

    public LiveData<String> getText() {
        return mText;
    }
}