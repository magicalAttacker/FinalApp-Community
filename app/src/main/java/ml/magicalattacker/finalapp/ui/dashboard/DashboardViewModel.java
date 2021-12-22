package ml.magicalattacker.finalapp.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ml.magicalattacker.finalapp.CardViewRecyclerAdapter;
import ml.magicalattacker.finalapp.CraftItemEntry;
import ml.magicalattacker.finalapp.SqlHelper;

public class DashboardViewModel extends ViewModel {

    private final CardViewRecyclerAdapter adapter;
    private final List<CraftItemEntry> list = new ArrayList<>();
    private SQLiteDatabase db;
    public static double total;
    public static MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }

    public DashboardViewModel() {
        initDB();
        initList();
        mText = new MutableLiveData<>();
        mText.setValue("购物车为空");
        adapter = new CardViewRecyclerAdapter(list, DashboardFragment.context);
    }

    public static void updateTotal() {
        if (total <= 0) {
            mText.postValue("购物车为空");
        } else {
            mText.postValue("总价：" + String.format(Locale.getDefault(), "%.2f", total));
        }
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