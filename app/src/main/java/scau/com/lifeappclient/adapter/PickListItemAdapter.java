package scau.com.lifeappclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.PickUpInfoWithBLOBs;

/**
 * Created by beyondboy on 2017/3/20.
 */
public class PickListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    private List<PickUpInfoWithBLOBs> pickUpInfoWithBLOBsList;
    private boolean isLoad = false;

    public PickListItemAdapter(List<PickUpInfoWithBLOBs> upInfoWithBLOBsList) {
        this.pickUpInfoWithBLOBsList = upInfoWithBLOBsList;
    }

    public void addData(List<PickUpInfoWithBLOBs> PickUpInfoWithBLOBsList) {
        this.pickUpInfoWithBLOBsList.addAll(PickUpInfoWithBLOBsList);
        notifyDataSetChanged();
    }

    public void addOneData(PickUpInfoWithBLOBs PickUpInfoWithBLOBs) {
        isLoad = true;
        this.pickUpInfoWithBLOBsList.add(PickUpInfoWithBLOBs);
        notifyItemInserted(this.pickUpInfoWithBLOBsList.size() - 1);
    }

    public void removeOneData() {
        if (isLoad) {
            this.pickUpInfoWithBLOBsList.remove(this.pickUpInfoWithBLOBsList.size() - 1);
            notifyItemRemoved(this.pickUpInfoWithBLOBsList.size());
            isLoad = false;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pickup_info_item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.request_page_footer, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return pickUpInfoWithBLOBsList.get(position).getType() == VIEW_TYPE_LOADING ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            final PickUpInfoWithBLOBs PickUpInfoWithBLOBs = pickUpInfoWithBLOBsList.get(position);
            itemViewHolder.pickContact.setText("联系电话:"+PickUpInfoWithBLOBs.getPickupPhone());
            itemViewHolder.pickName.setText("联系姓名:"+PickUpInfoWithBLOBs.getPickupImage());
            itemViewHolder.pickDesc.setText(PickUpInfoWithBLOBs.getPickupDesc());
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            footViewHolder.progress.start();
        }
    }

    @Override
    public int getItemCount() {
        return this.pickUpInfoWithBLOBsList != null ? this.pickUpInfoWithBLOBsList.size() : 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView pickContact;
        TextView pickDesc;
        TextView pickName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            pickContact = (TextView) itemView.findViewById(R.id.pick_contact);
            pickDesc = (TextView) itemView.findViewById(R.id.pick_desc);
            pickName = (TextView) itemView.findViewById(R.id.pick_name);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        LoadingView progress;

        public FootViewHolder(View footView) {
            super(footView);
            progress = (LoadingView) footView.findViewById(R.id.bottom_progress);
        }
    }
}
