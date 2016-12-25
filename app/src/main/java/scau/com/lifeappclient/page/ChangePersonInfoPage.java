package scau.com.lifeappclient.page;

import android.view.View;
import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.UserDetail;

/**
 * Created by beyondboy on 2016/12/25.
 */
@PageLayout(R.layout.change_info_item)
public class ChangePersonInfoPage extends Page implements View.OnClickListener{
    @InjectView(R.id.textView)
    private TextView textView;
    @InjectView(R.id.nickname)
    private TextView modifyTxView;
    @InjectView(value = R.id.change_back,listenerTypes = View.OnClickListener.class)
    private View view;
    private UserDetail userDetail;
    public ChangePersonInfoPage(PageActivity pageActivity) {
        super(pageActivity);
    }

   public ChangePersonInfoPage onShow(final String data, final String modify){
       super.onShow();
       textView.setText(data);
       modifyTxView.setText(modify);
       return this;
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_back:
                hide(true);
                break;
        }
    }
}
