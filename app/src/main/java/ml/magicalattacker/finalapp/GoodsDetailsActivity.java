package ml.magicalattacker.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ml.magicalattacker.finalapp.ui.dashboard.DashboardFragment;
import ml.magicalattacker.finalapp.ui.home.HomeFragment;
import ml.magicalattacker.finalapp.ui.home.HomeViewModel;

public class GoodsDetailsActivity extends AppCompatActivity {
    CraftItemEntry entry;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        entry = getIntent().getParcelableExtra("data");
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(entry.getId());
        TextView textView = findViewById(R.id.textView10);
        textView.setText(entry.getInfo());
    }

    public void initDB() {
        SqlHelper helper = new SqlHelper(this, "database", null, 1);
        db = helper.getWritableDatabase();
    }

    public void addCart(View view) {
        initDB();
        String username = getSharedPreferences("userinfo", 0).getString("username", "");
        if (username.isEmpty()) return;
        ContentValues values = new ContentValues();
        values.put("goodsimage", entry.getId());
        values.put("goodsinfo", entry.getInfo());
        values.put("goodsprice", entry.getPrice());
        values.put("username", username);
        db.insert("cart", null, values);
        Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}