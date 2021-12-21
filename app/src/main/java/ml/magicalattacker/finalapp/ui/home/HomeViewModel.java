package ml.magicalattacker.finalapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ml.magicalattacker.finalapp.CraftItemEntry;
import ml.magicalattacker.finalapp.HomeRecyclerviewAdapter;
import ml.magicalattacker.finalapp.R;

public class HomeViewModel extends ViewModel {

    private final HomeRecyclerviewAdapter adapter;
    private final List<CraftItemEntry> list = new ArrayList<>();

    public HomeViewModel() {
        initList();
        adapter = new HomeRecyclerviewAdapter(list, HomeFragment.context);
    }

    public void initList() {
        for (int i = 0; i < 10; i++) {
            String content = String.valueOf(i);
            CraftItemEntry entry = new CraftItemEntry(R.drawable.icon, content, 99.99);
            list.add(entry);
        }
    }

    public HomeRecyclerviewAdapter getAdapter() {
        return adapter;
    }
}