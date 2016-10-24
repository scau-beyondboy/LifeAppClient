package scau.com.lifeappclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.neevek.android.lib.paginize.PageActivity;

import java.util.List;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.ClubInfo;
import scau.com.lifeappclient.page.ClubWebSitePage;
import scau.com.lifeappclient.utils.OkHttpNetWorkUtil;

/**
 * Created by beyondboy on 2016/10/18.
 */
public class ClubListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    private List<ClubInfo> mClubInfoList;

    public ClubListItemAdapter(List<ClubInfo> clubInfoList) {
        this.mClubInfoList = clubInfoList;
    }

    public void addData(List<ClubInfo> clubInfoList){
        this.mClubInfoList.addAll(clubInfoList);
        notifyDataSetChanged();
    }

    public void addOneData(ClubInfo clubInfo){
        this.mClubInfoList.add(clubInfo);
        notifyItemInserted(this.mClubInfoList.size()-1);
    }

    public void removeOneData(){
        this.mClubInfoList.remove(this.mClubInfoList.size()-1);
        notifyItemRemoved(this.mClubInfoList.size());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.club_page_item, parent, false);
            return  new ItemViewHolder(view);
        }else  if(viewType==VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.request_page_footer, parent, false);
            return  new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mClubInfoList.get(position)==null ?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
            final ClubInfo clubInfo=mClubInfoList.get(position);
            OkHttpNetWorkUtil.displaySimpleImage(itemViewHolder.clubLogo,"https://static.pexels.com/photos/127677/pexels-photo-127677-large.jpeg");
            itemViewHolder.clubName.setText(clubInfo.getClubName());
            itemViewHolder.clubDesc.setText(clubInfo.getClubDesc());
            itemViewHolder.clubWexXinNum.setText(clubInfo.getClubWeixinNum());
            itemViewHolder.clubHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ClubWebSitePage((PageActivity) v.getContext()).onShown("http://su.scau.edu.cn/").show(true);
                }
            });
        }   else if(holder instanceof FootViewHolder){
            FootViewHolder footViewHolder=(FootViewHolder)holder;
            footViewHolder.progress.start();
        }
    }

    @Override
    public int getItemCount() {
        return mClubInfoList!=null? mClubInfoList.size():0;
    }
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        View clubHeader;
        SimpleDraweeView clubLogo;
        TextView clubName;
        TextView clubWexXinNum;
        TextView clubDesc;
        public ItemViewHolder(View itemView) {
            super(itemView);
            clubHeader=itemView.findViewById(R.id.club_header);
            clubLogo=(SimpleDraweeView) itemView.findViewById(R.id.club_logo);
            clubName=(TextView)itemView.findViewById(R.id.club_name);
            clubWexXinNum=(TextView)itemView.findViewById(R.id.club_wexxin_num);
            clubDesc=(TextView)itemView.findViewById(R.id.club_desc);
        }
    }
    static class FootViewHolder extends RecyclerView.ViewHolder{
        LoadingView progress;
        public  FootViewHolder(View footView){
            super(footView);
            progress=(LoadingView)footView.findViewById(R.id.bottom_progress);
        }
    }
}
