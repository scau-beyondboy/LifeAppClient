package scau.com.lifeappclient.page;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import net.neevek.android.lib.paginize.InnerPage;
import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.ViewWrapper;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.adapter.GridViewAdapter;

/**
 * Created by beyondboy on 2017/1/3.
 */
@PageLayout(R.layout.grid_layout)
public class GridViewPage  extends InnerPage{
    private Page[] gridItemPage=new Page[]{new ScauMapPage(getContext()),new PickUpInfoPage(getContext())
    ,new ScauTelPage(getContext()),new ScroePage(getContext()).onShown("file:///android_asset/goal/index.html")};
    @InjectView(R.id.gridView)
    private GridView gridView;
    public GridViewPage(ViewWrapper innerPageContainer) {
        super(innerPageContainer);
    }

    @Override
    public void onShown() {
        super.onShown();
        gridView.setAdapter(new GridViewAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0&&position<gridItemPage.length){
                    gridItemPage[position].show(true);
                }
            }
        });
    }
}
