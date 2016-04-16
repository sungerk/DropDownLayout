package sunger.net.org.dropmenu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sunger on 16/4/16.
 */
public class ShopAdapter  extends RecyclerView.Adapter<ShopAdapter.ShopViewHoler>{
private  String[] data={"shop1","shop2","shop3","shop4","shop5","shop5","shop6","shop6","shop7","shop8","shop9","shop10","shop1","shop1","shop1","shop1","shop1","shop8","shop9","shop10","shop1","shop1","shop1","shop1","shop1","shop8","shop9","shop10","shop1","shop1","shop1","shop1","shop1"};

    @Override
    public ShopViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv=  new TextView(parent.getContext());
        tv.setHeight(300);
        return new ShopViewHoler(tv);
    }

    @Override
    public void onBindViewHolder(ShopViewHoler holder, int position) {
        holder.mTextView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static   class ShopViewHoler extends  RecyclerView.ViewHolder{

        public final TextView mTextView;
        public ShopViewHoler(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView;
        }
    }
}
