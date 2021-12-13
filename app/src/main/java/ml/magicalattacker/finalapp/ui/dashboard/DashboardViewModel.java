package ml.magicalattacker.finalapp.ui.dashboard;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ml.magicalattacker.finalapp.CardViewRecyclerAdapter;

public class DashboardViewModel extends ViewModel {

    private final CardViewRecyclerAdapter adapter;
    private final List<String> list = new ArrayList<>();

    public DashboardViewModel() {
        initList();
        adapter = new CardViewRecyclerAdapter(list);
    }

    public void initList() {
        for (int i = 0; i < 10; i++) {
            String content = String.valueOf(i);
            list.add(content);
        }
    }

    public CardViewRecyclerAdapter getAdapter() {
        return adapter;
    }
}