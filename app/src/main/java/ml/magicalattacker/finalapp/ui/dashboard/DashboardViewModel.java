package ml.magicalattacker.finalapp.ui.dashboard;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ml.magicalattacker.finalapp.CardViewRecyclerAdapter;
import ml.magicalattacker.finalapp.CraftItemEntry;
import ml.magicalattacker.finalapp.R;

public class DashboardViewModel extends ViewModel {

    private final CardViewRecyclerAdapter adapter;
    private final List<CraftItemEntry> list = new ArrayList<>();

    public DashboardViewModel() {
        initList();
        adapter = new CardViewRecyclerAdapter(list, DashboardFragment.context);
    }

    public void initList() {
        for (int i = 0; i < 10; i++) {
            String content = String.valueOf(i);
            CraftItemEntry entry = new CraftItemEntry(R.drawable.icon, content, 99.99);
            list.add(entry);
        }
    }

    public CardViewRecyclerAdapter getAdapter() {
        return adapter;
    }
}