package ml.magicalattacker.finalapp.ui.notifications;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("轻点头像以登录");
    }

    public LiveData<String> getText() {
        return mText;
    }
}