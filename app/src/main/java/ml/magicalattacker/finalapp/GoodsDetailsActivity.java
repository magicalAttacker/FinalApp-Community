package ml.magicalattacker.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ml.magicalattacker.finalapp.ui.home.HomeFragment;
import ml.magicalattacker.finalapp.ui.home.HomeViewModel;

public class GoodsDetailsActivity extends AppCompatActivity {
    CraftItemEntry entry;

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

    public void addItem(View view) {
        HomeViewModel.list.add(entry);
        HomeViewModel.adapter.notifyDataSetChanged();
    }
}