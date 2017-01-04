package scau.com.lifeappclient.page;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.neevek.android.lib.paginize.ContainerPage;
import net.neevek.android.lib.paginize.InnerPage;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InnerPageContainerLayoutResId;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;

/**
 * Created by beyondboy on 2017/1/2.
 */
//@InsertPageLayout(value = R.layout.main_page_tab,parent = R.id.container)
@PageLayout(R.layout.main_page_tab)
@InnerPageContainerLayoutResId(R.id.layout_content_container)
public class TagPage extends ContainerPage {
    private static final String TAG = TagPage.class.getName();
    @InjectView(R.id.title)
    private TextView title;
    @InjectView(R.id.toobar_layout)
    private View view;
    @InjectView(R.id.left_back)
    private View backView;
//    @InjectView(R.id.tb_header_bar)
//    private Toolbar mTbHeaderBar;
    private InnerPage[] mTabPageArray;
    @InjectView(R.id.layout_tab_container)
    private TabLayout mLayoutTabContainer;
    private String titles[]=new String[]{"学校辅助信息","社团信息","公告信息","个人信息"};
    public TagPage(PageActivity pageActivity) {
        super(pageActivity);
        backView.setVisibility(View.INVISIBLE);
        title.setText("学校辅助信息");
        view.setBackgroundColor(Color.LTGRAY);
        setupInnerPages();
    }

    private void setupInnerPages() {
        mTabPageArray = new InnerPage[] {
              new GridViewPage(this),new ClubPage(this),new NoticePage(this),new PersonInfoPage(this)
        };

        mLayoutTabContainer.addTab(mLayoutTabContainer.newTab().setText("学校辅助信息").setIcon(R.drawable.tab1));
        mLayoutTabContainer.addTab(mLayoutTabContainer.newTab().setText("社团信息").setIcon(R.drawable.tab2));
        mLayoutTabContainer.addTab(mLayoutTabContainer.newTab().setText("公告信息").setIcon(R.drawable.tab3));
        mLayoutTabContainer.addTab(mLayoutTabContainer.newTab().setText("个人信息").setIcon(R.drawable.tab4));
        mLayoutTabContainer.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                title.setText(titles[tab.getPosition()]);
                setInnerPage(mTabPageArray[tab.getPosition()]);
            }
            public void onTabUnselected(TabLayout.Tab tab) { }
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // set first page as the first page to show
        setInnerPage(mTabPageArray[0]);
    }

    @Override
    public void onShow() {
        super.onShow();
        Log.i(TAG, "SimpleTabPage onShow()");
    }

    @Override
    public void onShown() {
        super.onShown();
        Log.i(TAG, "SimpleTabPage onShown()");
    }

    @Override
    public void onHide() {
        super.onHide();
        Log.i(TAG, "SimpleTabPage onHide()");
    }

    @Override
    public void onHidden() {
        super.onHidden();
        Log.i(TAG, "SimpleTabPage onHidden()");
    }
}
