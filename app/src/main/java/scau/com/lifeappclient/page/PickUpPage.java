package scau.com.lifeappclient.page;

import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;

import net.neevek.android.lib.paginize.InnerPage;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.ViewWrapper;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InsertPageLayout;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import java.util.ArrayList;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.adapter.NoticeListItemAdapter;
import scau.com.lifeappclient.adapter.PickListItemAdapter;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.constants.ParamConstants;
import scau.com.lifeappclient.model.PickUpInfoWithBLOBs;
import scau.com.lifeappclient.model.PageInfo;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/24.
 */
//@PageLayout(R.layout.requst_listdata_page)
@InsertPageLayout(value = R.layout.requst_listdata_page,parent = R.id.container)
public class PickUpPage extends ToolBarPage implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{
    private static final String TAG = PickUpPage.class.getName();
    private int pageStart=0;
    private int pageEnd=5;
    private static final int SIZE=5;
    private final int visibleThreshold = 1;
    private final ArrayMap<String,String> params=new ArrayMap<>(2);
    @InjectView(value = R.id.swipeRefreshLayout,listenerTypes = SwipeRefreshLayout.OnRefreshListener.class)
    private SwipeRefreshLayout mRefreshLayout;
    @InjectView(R.id.recy_list)
    private RecyclerView mPickUpReycleView;
    @InjectView(value = R.id.empty_view,listenerTypes = View.OnClickListener.class)
    private ImageView mTvEmpty;
    @InjectView(R.id.progress)
    private LoadingView mLoadingView;
    private boolean isLoading=false;
    public PickUpPage(PageActivity activity) {
        super(activity);
        setTitleText("拾物公告");
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
    }

    private void init(){
        if(mPickUpReycleView.getLayoutManager()==null){
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
            mPickUpReycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    final int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= lastVisibleItem+visibleThreshold ) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
//                                final NoticeListItemAdapter noticeListItemAdapter=(NoticeListItemAdapter) mPickUpReycleView.getAdapter();
//                                noticeListItemAdapter.addOneData(new PickUpInfoWithBLOBs(NoticeListItemAdapter.VIEW_TYPE_LOADING ));
                                loadData();
                            }
                        });
                    }
                }
            });
            mPickUpReycleView.setLayoutManager(linearLayoutManager);
            mPickUpReycleView.setAdapter(new PickListItemAdapter(new ArrayList<PickUpInfoWithBLOBs>()));
        }
        loadData();
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.empty_view:
                mTvEmpty.setVisibility(View.GONE);
                loadData();
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
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_PICKUP_TOTAL,null,"获取总数成功","获取总数失败",new NetWorkHandlerUtils.PostSuccessCallback<Integer>(){
            @Override
            public void success(Integer result) {
                final int score=result-pageStart;
                if(5>score&&score>0){
                    pageEnd =score+pageStart;
                }else if(score>=5){
                    pageEnd =pageStart+SIZE;
                }else {
                    pageEnd =pageStart;
                }
                getData();
            }
        },Integer.class);
    }

    private void getData(){
        if(pageEnd ==pageStart){
            isLoading=false;
            mRefreshLayout.setRefreshing(false);
            return;
        }
        mLoadingView.start();
        params.put(ParamConstants.PAGESIZE, pageEnd +"");
        params.put(ParamConstants.PAGESTART,pageStart+"");
        pageStart=pageEnd;
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.GET_PICKUP__INFO,params,new NetWorkHandlerUtils.PostCallback<PageInfo<PickUpInfoWithBLOBs>>(){

            @Override
            public void success(PageInfo<PickUpInfoWithBLOBs> result) {
                Log.d(TAG,"beyondboy返回数据>>>>>>>>>>>:"+result.toString());
//                if(isScrollLoad){
//                    isScrollLoad=false;
//                    ((ClubListItemAdapter)mClubReycleView.getAdapter()).removeOneData();
//                }
                isLoading=false;
                mLoadingView.stop();
                ((PickListItemAdapter) mPickUpReycleView.getAdapter()).addData(result.getData());
                Log.d(TAG,"beyondboy输出结果>>>>>>>>>>:"+mPickUpReycleView.getAdapter().getItemCount());
                mRefreshLayout.setVisibility(View.VISIBLE);
                mTvEmpty.setVisibility(View.GONE);
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                e.printStackTrace();
                mLoadingView.stop();
                isLoading=false;
                mTvEmpty.setImageResource(R.drawable.load_error);
                mRefreshLayout.setVisibility(View.GONE);
                mTvEmpty.setVisibility(View.VISIBLE);
                mRefreshLayout.setRefreshing(false);
            }
        },new NetWorkHandlerUtils.NotDataCallBack() {
            @Override
            public void handle() {
                mLoadingView.stop();
                isLoading=false;
                mRefreshLayout.setRefreshing(false);
                ToaskUtils.showToast("没有新的数据");
            }
        },new TypeToken<PageInfo<PickUpInfoWithBLOBs>>(){}.getType());
    }
}
