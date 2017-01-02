package scau.com.lifeappclient.page;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InsertPageLayout;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.adapter.ScauTelListItemAdapter;
import scau.com.lifeappclient.model.TelInfo;

/**
 * Created by beyondboy on 2016/10/19.
 */
@PageLayout(R.layout.scau_tel_page)
//@InsertPageLayout(value = R.layout.scau_tel_page,parent = R.id.container)
public class ScauTelPage extends ToolBarPage {
    @InjectView(R.id.recy_list)
    private RecyclerView mRecyTelList;

    public ScauTelPage(PageActivity pageActivity) {
        super(pageActivity);
        setTitleText("学校重要电话");
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
    }

    private void init(){
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyTelList.setLayoutManager(linearLayoutManager);
        mRecyTelList.setAdapter(new ScauTelListItemAdapter(loadData()));
    }

    private List<TelInfo> loadData(){
        final List<TelInfo> telInfoList=new ArrayList<>();
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校内报警电话",85280110));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校医院急救电话",85280349));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校医院救护车",85283712));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校园卡挂失",85283712));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校内报警电话",85280110));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校内报警电话",85280110));
        telInfoList.add(new TelInfo(R.drawable.ambulance,"校内报警电话",85280110));
        return telInfoList;
    }
}
