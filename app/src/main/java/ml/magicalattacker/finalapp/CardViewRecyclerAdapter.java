package ml.magicalattacker.finalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardViewRecyclerAdapter extends RecyclerView.Adapter<CardViewRecyclerAdapter.ViewHolder> {

    private final List<CraftItemEntry> localDataSet;
    private final Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView info;
        private final ImageView imageView;
        private final TextView price;
        private final TextView num;
        private final Button add;
        private final Button mins;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            info = view.findViewById(R.id.info_text);
            imageView = view.findViewById(R.id.imageView);
            price = view.findViewById(R.id.textView13);
            num = view.findViewById(R.id.textView12);
            add = view.findViewById(R.id.button10);
            mins = view.findViewById(R.id.button11);
        }

        public TextView getTextView() {
            return info;
        }
        public ImageView getImageView() { return imageView; }
        public TextView getPriceView() { return price; }
        public TextView getNum() { return num; }
        public Button getAdd() { return add; }
        public Button getMins() { return mins; }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CardViewRecyclerAdapter(List<CraftItemEntry> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).getInfo());
        Glide.with(context).load(localDataSet.get(position).getId()).into(viewHolder.getImageView());
        viewHolder.getPriceView().setText(String.format("%s%s", context.getString(R.string.tip), localDataSet.get(position).getPrice()));
        viewHolder.getNum().setText("1");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
