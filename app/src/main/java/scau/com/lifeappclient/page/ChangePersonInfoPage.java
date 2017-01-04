package scau.com.lifeappclient.page;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.model.UserDetail;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ShareUtils;
import scau.com.lifeappclient.utils.StringUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/12/25.
 */
@PageLayout(R.layout.change_info_item)
public class ChangePersonInfoPage extends Page implements View.OnClickListener{
    @InjectView(R.id.textView)
    private TextView textView;
    @InjectView(R.id.nickname)
    private EditText modifyTxView;
    @InjectView(value = R.id.change_back,listenerTypes = View.OnClickListener.class)
    private View view;
    @InjectView(value = R.id.save,listenerTypes = View.OnClickListener.class)
    private Button save;
    private UserDetail userDetail;
    private int code;
    public ChangePersonInfoPage(PageActivity pageActivity) {
        super(pageActivity);
    }

   public ChangePersonInfoPage onShow(final String data, final String modify,final UserDetail userDetail,int code){
       super.onShow();
       textView.setText(data);
       modifyTxView.setText(modify);
       this.userDetail=userDetail;
       this.code=code;
       return this;
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_back:
                hide(true);
                break;
            case R.id.save:
                if(StringUtils.isEmpty(modifyTxView.getText().toString())){
                    ToaskUtils.showToast("不能填空");
                    return;
                }
                final String content=modifyTxView.getText().toString();
                switch (code){
                    case 0:
                        this.userDetail.setUserNum(Long.valueOf(content));
                        break;
                    case 1:
                        this.userDetail.setUserIden(1);
                        break;
                    case 2:
                        this.userDetail.setUserMajor(content);
                        break;
                    case 3:
                        this.userDetail.setUserGradle(content);
                        break;
                    case 4:
                        this.userDetail.setUserClass(content);
                        break;
                }
                final Long userId= ShareUtils.getUserId();
                userDetail.setUserId(userId);
                NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.UPDATE_INFO,userDetail,new NetWorkHandlerUtils.PostSuccessCallback<Object>() {
                    @Override
                    public void success(Object result) {
                        ToaskUtils.showToast("提交信息成功");
                    }
                });
                break;
        }
    }
}
