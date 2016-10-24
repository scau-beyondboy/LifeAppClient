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
import scau.com.lifeappclient.model.NoticeInfo;
import scau.com.lifeappclient.page.NoticeDetailPage;

/**
 * Created by beyondboy on 2016/10/24.
 */
public class NoticeListItemAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    private List<NoticeInfo> mNoticeInfoList;
    private boolean isLoad=false;
    public NoticeListItemAdapter(List<NoticeInfo> noticeInfoList){
        this.mNoticeInfoList=noticeInfoList;
    }

    public void addData(List<NoticeInfo> noticeInfoList){
        this.mNoticeInfoList.addAll(noticeInfoList);
        notifyDataSetChanged();
    }

    public void addOneData(NoticeInfo noticeInfo){
        isLoad=true;
        this.mNoticeInfoList.add(noticeInfo);
        notifyItemInserted(this.mNoticeInfoList.size()-1);
    }

    public void removeOneData(){
        if(isLoad){
            this.mNoticeInfoList.remove(this.mNoticeInfoList.size()-1);
            notifyItemRemoved(this.mNoticeInfoList.size());
            isLoad=false;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notice_page_item, parent, false);
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
        return mNoticeInfoList.get(position).getType()==VIEW_TYPE_LOADING ?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ItemViewHolder){
            ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
            final  NoticeInfo noticeInfo=mNoticeInfoList.get(position);
            itemViewHolder.noticeTitle.setText(noticeInfo.getNoticeTitle());
            itemViewHolder.noticeDate.setText(noticeInfo.getNoticeDate().toString());
            itemViewHolder.noticeDesc.setText(noticeInfo.getNoticeDesc());
            itemViewHolder.noticeHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new NoticeDetailPage((PageActivity) v.getContext()).onShown(noticeInfo).show();
                }
            });
        } else if(holder instanceof FootViewHolder){
            FootViewHolder footViewHolder=(FootViewHolder)holder;
            footViewHolder.progress.start();
        }
    }

    @Override
    public int getItemCount() {
        return this.mNoticeInfoList!=null?this.mNoticeInfoList.size():0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        View noticeHeader;
        SimpleDraweeView noticeLogo;
        TextView noticeTitle;
        TextView noticeDesc;
        TextView noticeDate;
        public ItemViewHolder(View itemView) {
            super(itemView);
            noticeHeader=itemView.findViewById(R.id.notice_header);
            noticeLogo=(SimpleDraweeView)itemView.findViewById(R.id.notice_logo);
            noticeTitle=(TextView)itemView.findViewById(R.id.notice_title);
            noticeDesc=(TextView)itemView.findViewById(R.id.notice_desc);
            noticeDate=(TextView)itemView.findViewById(R.id.notice_date);
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
