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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ml.magicalattacker.finalapp.LoginActivity;
import ml.magicalattacker.finalapp.UserEntry;
import ml.magicalattacker.finalapp.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;
    private ImageView headView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView userName = binding.userName;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), userName::setText);

        headView = binding.headView;
        headView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        initUser();

        return root;
    }

    private void initUser() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("userinfo", 0);
        UserEntry userEntry = new UserEntry(preferences.getString("username", ""), preferences.getString("password", ""));
        notificationsViewModel.loadUserEntry(userEntry);
        notificationsViewModel.updateUsername(getActivity());
        notificationsViewModel.updateHead(getActivity(), headView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUser();
    }
}