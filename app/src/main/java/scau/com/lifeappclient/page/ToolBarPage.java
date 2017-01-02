package scau.com.lifeappclient.page;

import android.view.View;
import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;

/**
 * Created by beyondboy on 2017/1/2.
 */
@PageLayout(R.layout.toobar_layout)
public abstract class ToolBarPage extends Page  implements View.OnClickListener{
    @InjectView(value = R.id.left_back,listenerTypes = View.OnClickListener.class)
    private View back;
    @InjectView(R.id.title)
    private TextView titleTx;
    @InjectView(R.id.right_tx)
    private TextView rightTx;
    public ToolBarPage(PageActivity pageActivity) {
        super(pageActivity);
    }

    @Override
    public void onShown() {
        super.onShown();
        rightTx.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_back:
                hide(true);
                break;
        }
    }

    protected void setRightVisble(boolean isVisible){
        if(isVisible){
            rightTx.setVisibility(View.VISIBLE);
        }else {
            rightTx.setVisibility(View.INVISIBLE);
        }
    }

    protected void setTitleText(final String title){
        titleTx.setText(title);
    }
}
