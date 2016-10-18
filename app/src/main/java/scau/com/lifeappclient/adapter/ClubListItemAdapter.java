package scau.com.lifeappclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.ClubInfo;
import scau.com.lifeappclient.utils.OkHttpNetWorkUtil;

/**
 * Created by beyondboy on 2016/10/18.
 */
public class ClubListItemAdapter extends RecyclerView.Adapter<ClubListItemAdapter.ViewHolder> {
    private List<ClubInfo> mClubInfoList;

    public ClubListItemAdapter(List<ClubInfo> clubInfoList) {
        this.mClubInfoList = clubInfoList;
    }

    public void addData(List<ClubInfo> clubInfoList){
        this.mClubInfoList.addAll(clubInfoList);
        notifyDataSetChanged();
    }
    @Override
    public ClubListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_page_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClubListItemAdapter.ViewHolder holder, int position) {
        final ClubInfo clubInfo=mClubInfoList.get(position);
        OkHttpNetWorkUtil.displaySimpleImage(holder.clubLogo,"https://static.pexels.com/photos/127677/pexels-photo-127677-large.jpeg");
        holder.clubName.setText(clubInfo.getClubName());
        holder.clubDesc.setText(clubInfo.getClubDesc());
        holder.clubWexXinNum.setText(clubInfo.getClubWeixinNum());
    }

    @Override
    public int getItemCount() {
        return mClubInfoList!=null? mClubInfoList.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View clubHeader;
        SimpleDraweeView clubLogo;
        TextView clubName;
        TextView clubWexXinNum;
        TextView clubDesc;
        public ViewHolder(View itemView) {
            super(itemView);
            clubHeader=itemView.findViewById(R.id.club_header);
            clubLogo=(SimpleDraweeView) itemView.findViewById(R.id.club_logo);
            clubName=(TextView)itemView.findViewById(R.id.club_name);
            clubWexXinNum=(TextView)itemView.findViewById(R.id.club_wexxin_num);
            clubDesc=(TextView)itemView.findViewById(R.id.club_desc);
        }
    }
}
