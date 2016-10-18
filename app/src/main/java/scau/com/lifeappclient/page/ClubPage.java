package scau.com.lifeappclient.page;

import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;


import java.util.List;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.adapter.ClubListItemAdapter;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.constants.ParamConstants;
import scau.com.lifeappclient.model.ClubInfo;
import scau.com.lifeappclient.model.ClubPageInfo;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/18.
 */
@PageLayout(R.layout.club_page)
public class ClubPage extends Page  implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{
    private static final String TAG = ClubPage.class.getName();
    private String pageAccount="1";
    private final String pageSize="5";
    private final int visibleThreshold = 1;
    private final ArrayMap<String,String> params=new ArrayMap<>(2);
    @InjectView(value = R.id.swipeRefreshLayout,listenerTypes = SwipeRefreshLayout.OnRefreshListener.class)
    private SwipeRefreshLayout mRefreshLayout;
    @InjectView(R.id.recy_list)
    private RecyclerView mClubReycleView;
    @InjectView(value = R.id.empty_view,listenerTypes = View.OnClickListener.class)
    private TextView mTvEmpty;
    private boolean isLoading=false;

    public ClubPage(PageActivity pageActivity) {
        super(pageActivity);
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
    }
    private void init(){
        if(mClubReycleView.getLayoutManager()==null){
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
            mClubReycleView.addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= lastVisibleItem+visibleThreshold ) {
                        loadData();
                    }
                }
            });
            mClubReycleView.setLayoutManager(linearLayoutManager);
        }
        params.put(ParamConstants.PAGEACCOUNT,pageAccount);
        params.put(ParamConstants.PAGESIZE,pageSize);
        isLoading=true;
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_CLUB_INFO,params,new NetWorkHandlerUtils.PostCallback<ClubPageInfo>(){

            @Override
            public void success(ClubPageInfo result) {
                isLoading=false;
                List<ClubInfo> clubInfoList=result.getClubInfoList();
                if(clubInfoList==null||clubInfoList.size()==0){
                    mRefreshLayout.setVisibility(View.GONE);
                    mTvEmpty.setText("没有数据");
                    mTvEmpty.setVisibility(View.VISIBLE);
                }else{
                    pageAccount=Integer.valueOf(pageAccount)+1+"";
                    params.put(ParamConstants.PAGEACCOUNT,pageAccount);
                    mClubReycleView.setAdapter(new ClubListItemAdapter(result.getClubInfoList()));
                    mRefreshLayout.setVisibility(View.VISIBLE);
                    mTvEmpty.setVisibility(View.GONE);
                }
            }

            @Override
            public void fail(Exception e) {
                e.printStackTrace();
                isLoading=false;
                mRefreshLayout.setVisibility(View.GONE);
                mTvEmpty.setText("请求出错\n点击重试");
                mTvEmpty.setVisibility(View.VISIBLE);
            }
        },ClubPageInfo.class);
    }
    @Override
    public void onRefresh() {
        if(!isLoading){
            loadData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empty_view:
                init();
                break;
        }
    }

    private void loadData(){
        isLoading=true;
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_CLUB_INFO,params,new NetWorkHandlerUtils.PostCallback<ClubPageInfo>(){

            @Override
            public void success(ClubPageInfo result) {
                isLoading=false;
                mRefreshLayout.setRefreshing(false);
                List<ClubInfo> clubInfoList=result.getClubInfoList();
                if(clubInfoList==null||clubInfoList.size()==0){
                    ToaskUtils.showToast("没有新的数据");
                }else{
                    pageAccount=Integer.valueOf(pageAccount)+1+"";
                    params.put(ParamConstants.PAGEACCOUNT,pageAccount);
                    ((ClubListItemAdapter)mClubReycleView.getAdapter()).addData(result.getClubInfoList());
                }
            }

            @Override
            public void fail(Exception e) {
                isLoading=false;
                e.printStackTrace();
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setVisibility(View.GONE);
                mTvEmpty.setText("请求出错\n点击重试");
                mTvEmpty.setVisibility(View.VISIBLE);
            }
        },ClubPageInfo.class);
    }
}
