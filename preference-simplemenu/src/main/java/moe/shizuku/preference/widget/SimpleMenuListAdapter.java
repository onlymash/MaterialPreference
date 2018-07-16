package moe.shizuku.preference.widget;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import moe.shizuku.preference.simplemenu.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SimpleMenuListAdapter extends RecyclerView.Adapter<SimpleMenuListItemHolder> {

    private SimpleMenuPopupWindow mWindow;

    public SimpleMenuListAdapter(SimpleMenuPopupWindow window) {
        super();

        mWindow = window;
    }

    @NonNull
    @Override
    public SimpleMenuListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleMenuListItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_menu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleMenuListItemHolder holder, int position) {
        holder.bind(mWindow, position);
    }

    @Override
    public int getItemCount() {
        return mWindow.getEntries() == null ? 0 : mWindow.getEntries().length;
    }
}
