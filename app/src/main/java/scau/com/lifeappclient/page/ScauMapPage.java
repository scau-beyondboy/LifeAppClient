package scau.com.lifeappclient.page;

import android.net.Uri;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InsertPageLayout;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import me.relex.photodraweeview.PhotoDraweeView;
import scau.com.lifeappclient.R;

/**
 * Created by beyondboy on 2016/10/19.
 */
//@PageLayout(R.layout.scau_map_page)
@InsertPageLayout(value = R.layout.scau_map_page,parent = R.id.container)
public class ScauMapPage extends ToolBarPage {
    @InjectView(R.id.photo_drawee_view)
    private PhotoDraweeView mPhotoDraweeView;
    public ScauMapPage(PageActivity pageActivity) {
        super(pageActivity);
        setTitleText("华农地图");
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
    }

    private void init(){
        mPhotoDraweeView.setPhotoUri(Uri.parse("res:///"+R.drawable.scau_map));
    }
}
