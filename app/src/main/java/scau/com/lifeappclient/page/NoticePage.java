package scau.com.lifeappclient.page;

import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import java.util.List;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.adapter.NoticeListItemAdapter;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.constants.ParamConstants;
import scau.com.lifeappclient.model.NoticeInfo;
import scau.com.lifeappclient.model.PageInfo;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/24.
 */
@PageLayout(R.layout.requst_listdata_page)
public class NoticePage extends Page  implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{
    private String pageAccount="1";
    private final String pageSize="5";
    private final int visibleThreshold = 1;
    private final ArrayMap<String,String> params=new ArrayMap<>(2);
    @InjectView(value = R.id.swipeRefreshLayout,listenerTypes = SwipeRefreshLayout.OnRefreshListener.class)
    private SwipeRefreshLayout mRefreshLayout;
    @InjectView(R.id.recy_list)
    private RecyclerView mNoticeReycleView;
    @InjectView(value = R.id.empty_view,listenerTypes = View.OnClickListener.class)
    private ImageView mTvEmpty;
    @InjectView(R.id.progress)
    private LoadingView mLoadingView;
    private boolean isLoading=false;
    private int total=0;
    public NoticePage(PageActivity pageActivity) {
        super(pageActivity);
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
    }

    private void init(){
        if(mNoticeReycleView.getLayoutManager()==null){
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
            mNoticeReycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    final int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= lastVisibleItem+visibleThreshold ) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                final NoticeListItemAdapter noticeListItemAdapter=(NoticeListItemAdapter) mNoticeReycleView.getAdapter();
                                noticeListItemAdapter.addOneData(new NoticeInfo(NoticeListItemAdapter.VIEW_TYPE_LOADING ));
                                loadData();
                            }
                        });
                    }
                }
            });
            mNoticeReycleView.setLayoutManager(linearLayoutManager);
        }
        params.put(ParamConstants.PAGEACCOUNT,pageAccount);
        params.put(ParamConstants.PAGESIZE,pageSize);
        mLoadingView.start();
        isLoading=true;
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_NOTICE__INFO,params,new NetWorkHandlerUtils.PostCallback<PageInfo<NoticeInfo>>(){

            @Override
            public void success(PageInfo<NoticeInfo> result) {
                isLoading=false;
                mLoadingView.stop();
                List<NoticeInfo> noticeListInfo=result.getData();
                if(noticeListInfo==null||noticeListInfo.size()==0){
                    mTvEmpty.setImageResource(R.drawable.not_data);
                    if(mNoticeReycleView.getAdapter().getItemCount()>0){
                        mRefreshLayout.setVisibility(View.VISIBLE);
                    }
                    mTvEmpty.setVisibility(View.VISIBLE);
                }else{
                    final int account=Integer.valueOf(pageAccount);
                    final int size=Integer.valueOf(pageSize);
                    params.put(ParamConstants.PAGEACCOUNT,pageAccount);
                    total=result.getCount();
                    mNoticeReycleView.setAdapter(new NoticeListItemAdapter(result.getData()));
                    if(account*size>=mNoticeReycleView.getAdapter().getItemCount())
                        pageAccount=Integer.valueOf(pageAccount)+1+"";
                    mRefreshLayout.setVisibility(View.VISIBLE);
                    mTvEmpty.setVisibility(View.GONE);
                }
            }

            @Override
            public void fail(Exception e) {
                e.printStackTrace();
                mLoadingView.stop();
                isLoading=false;
                mTvEmpty.setImageResource(R.drawable.load_error);
                mRefreshLayout.setVisibility(View.GONE);
                mTvEmpty.setVisibility(View.VISIBLE);
            }
        },new NetWorkHandlerUtils.NotDataCallBack() {
            @Override
            public void handle() {
                isLoading=false;
                mRefreshLayout.setRefreshing(false);
                ToaskUtils.showToast("没有新的数据");
            }
        },new TypeToken<PageInfo<NoticeInfo>>(){}.getType());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.empty_view:
                mTvEmpty.setVisibility(View.GONE);
                init();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if(!isLoading){
            loadData();
        }else{
            mRefreshLayout.setRefreshing(false);
        }
    }

    private void loadData(){
        isLoading=true;
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_NOTICE__INFO, params, new NetWorkHandlerUtils.PostCallback<PageInfo<NoticeInfo>>() {

            @Override
            public void success(PageInfo<NoticeInfo> result) {
                isLoading = false;
                ((NoticeListItemAdapter) mNoticeReycleView.getAdapter()).removeOneData();
                mRefreshLayout.setRefreshing(false);
                List<NoticeInfo> noticListInfo = result.getData();
                total = result.getCount();
                if (noticListInfo == null || noticListInfo.size() == 0) {
                    ToaskUtils.showToast("没有新的数据");
                } else {
                    final int account=Integer.valueOf(pageAccount);
                    final int size=Integer.valueOf(pageSize);
                    if(account*size<=mNoticeReycleView.getAdapter().getItemCount())
                        pageAccount = Integer.valueOf(pageAccount) + 1 + "";
                    params.put(ParamConstants.PAGEACCOUNT, pageAccount);
                    ((NoticeListItemAdapter) mNoticeReycleView.getAdapter()).addData(result.getData());
                }
            }

            @Override
            public void fail(Exception e) {
                isLoading = false;
                ((NoticeListItemAdapter) mNoticeReycleView.getAdapter()).removeOneData();
                e.printStackTrace();
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setVisibility(View.GONE);
                mTvEmpty.setImageResource(R.drawable.load_error);
                mTvEmpty.setVisibility(View.VISIBLE);
            }
        }, new NetWorkHandlerUtils.NotDataCallBack() {
            @Override
            public void handle() {
                isLoading=false;
                ((NoticeListItemAdapter) mNoticeReycleView.getAdapter()).removeOneData();
                mRefreshLayout.setRefreshing(false);
                ToaskUtils.showToast("没有新的数据");
            }
        },new TypeToken<PageInfo<NoticeInfo>>(){}.getType());
    }
}
