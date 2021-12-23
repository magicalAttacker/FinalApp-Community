package ml.magicalattacker.finalapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GoodsDetailsActivity extends AppCompatActivity {
    CraftItemEntry entry;
    SQLiteDatabase db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        entry = getIntent().getParcelableExtra("data");
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(entry.getId());
        TextView textView = findViewById(R.id.textView10);
        textView.setText(entry.getInfo());
        TextView priceView = findViewById(R.id.price);
        priceView.setText("售价：" + entry.getPrice());
    }

    public void initDB() {
        SqlHelper helper = new SqlHelper(this, "database", null, 1);
        db = helper.getWritableDatabase();
    }

    public void addCart(View view) {
        initDB();
        String username = getSharedPreferences("userinfo", 0).getString("username", "");
        if (username.isEmpty()) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor cursor = db.query("cart", null, "username = ?", new String[]{ username }, null, null, null);
        while (cursor.moveToNext()) {
            String goodsInfo = cursor.getString(cursor.getColumnIndexOrThrow("goodsinfo"));
            if (entry.getInfo().equals(goodsInfo)) {
                Toast.makeText(this, "已经在购物车里了", Toast.LENGTH_SHORT).show();
                return;
            }
        }
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