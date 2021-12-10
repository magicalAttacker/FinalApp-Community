package ml.magicalattacker.finalapp.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ml.magicalattacker.finalapp.LoginActivity;
import ml.magicalattacker.finalapp.UserEntry;
import ml.magicalattacker.finalapp.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private UserEntry userEntry;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initUser();
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        notificationsViewModel.loadUserEntry(userEntry);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView userName = binding.userName;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), userName::setText);
        notificationsViewModel.updateUsername(getActivity());

        final ImageView headView = binding.headView;
        headView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return root;
    }

    private void initUser() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("userinfo", 0);
        userEntry = new UserEntry(preferences.getString("username", ""), preferences.getString("password", ""));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}