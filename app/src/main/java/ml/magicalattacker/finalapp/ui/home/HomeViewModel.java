package ml.magicalattacker.finalapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        CraftItemEntry entry0 = new CraftItemEntry(R.drawable.a, "iPhone13 Pro", 7999.99);
        CraftItemEntry entry1 = new CraftItemEntry(R.drawable.b, "Mac mini", 5999.99);
        CraftItemEntry entry2 = new CraftItemEntry(R.drawable.c, "Macbook Pro", 10999.99);
        CraftItemEntry entry3 = new CraftItemEntry(R.drawable.d, "Freebuds 4", 599.99);
        CraftItemEntry entry4 = new CraftItemEntry(R.drawable.e, "iPad Pro", 6999.99);
        CraftItemEntry entry5 = new CraftItemEntry(R.drawable.f, "MatePad Pro", 5999.99);
        list.add(entry0);
        list.add(entry1);
        list.add(entry2);
        list.add(entry3);
        list.add(entry4);
        list.add(entry5);
    }

    public HomeRecyclerviewAdapter getAdapter() {
        return adapter;
    }
}