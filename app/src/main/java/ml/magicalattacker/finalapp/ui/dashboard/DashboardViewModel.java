package ml.magicalattacker.finalapp.ui.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ml.magicalattacker.finalapp.CardViewRecyclerAdapter;
import ml.magicalattacker.finalapp.CraftItemEntry;
import ml.magicalattacker.finalapp.R;
import ml.magicalattacker.finalapp.SqlHelper;

public class DashboardViewModel extends ViewModel {

    private final CardViewRecyclerAdapter adapter;
    private final List<CraftItemEntry> list = new ArrayList<>();
    private SQLiteDatabase db;

    public DashboardViewModel() {
        initDB();
        initList();
        adapter = new CardViewRecyclerAdapter(list, DashboardFragment.context);
    }

    public void initDB() {
        SqlHelper helper = new SqlHelper(DashboardFragment.context, "database", null, 1);
        db = helper.getReadableDatabase();
    }

    public void initList() {
        String username = DashboardFragment.context.getSharedPreferences("userinfo", 0).getString("username", "");
        if (username.isEmpty()) return;
        Cursor cursor = db.query("cart", null, "username = ?", new String[]{ username }, null, null, null);
        while (cursor.moveToNext()) {
            int goodsImage = cursor.getInt(cursor.getColumnIndexOrThrow("goodsimage"));
            String goodsInfo = cursor.getString(cursor.getColumnIndexOrThrow("goodsinfo"));
            double goodsPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("goodsprice"));
            CraftItemEntry entry = new CraftItemEntry(goodsImage, goodsInfo, goodsPrice);
            list.add(entry);
        }
        cursor.close();
    }

    public CardViewRecyclerAdapter getAdapter() {
        return adapter;
    }
}