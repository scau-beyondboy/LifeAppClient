package scau.com.lifeappclient.page;

import net.neevek.android.lib.paginize.ContainerPage;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InnerPageContainerLayoutResId;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;

/**
 * Created by beyondboy on 2016/10/17.
 */
@PageLayout(R.layout.main_page_tab)
@InnerPageContainerLayoutResId(R.id.layout_content_container)
public class MainPage extends ContainerPage {
    private static final String TAG=MainPage.class.getName();
    public MainPage(PageActivity pageActivity) {
        super(pageActivity);
    }
}
