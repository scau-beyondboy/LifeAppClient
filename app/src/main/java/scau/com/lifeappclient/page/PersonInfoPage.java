package scau.com.lifeappclient.page;

import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import net.neevek.android.lib.paginize.InnerPage;
import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.ViewWrapper;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.constants.ParamConstants;
import scau.com.lifeappclient.model.UserDetail;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ShareUtils;
import scau.com.lifeappclient.utils.StringUtils;

/**
 * Created by beyondboy on 2016/12/25.
 */
@PageLayout(R.layout.login_info)
public class PersonInfoPage extends InnerPage implements View.OnClickListener{

    @InjectView(value = R.id.num_layout,listenerTypes = View.OnClickListener.class)
    private View nickNameView;
    @InjectView(value = R.id.email_layout,listenerTypes = View.OnClickListener.class)
    private View shcoolView;
    @InjectView(value = R.id.birthday_layout,listenerTypes = View.OnClickListener.class)
    private View marjorView;
    @InjectView(value = R.id.sex_layout,listenerTypes = View.OnClickListener.class)
    private View graldeView;
    @InjectView(value = R.id.address_layout,listenerTypes = View.OnClickListener.class)
    private View classView;
    @InjectView(R.id.nickname)
    private TextView numTxView;
    @InjectView(R.id.email)
    private TextView shcoolTxView;
    @InjectView(R.id.birthday)
    private TextView majorTxView;
    @InjectView(R.id.sex)
    private TextView gradeTxView;
    @InjectView(R.id.address)
    private TextView classTxView;
    private UserDetail userDetail;
    public PersonInfoPage(ViewWrapper innerPageContainer) {
        super(innerPageContainer);
    }

    @Override
    public void onShown() {
        super.onShown();
        init();
        getData();
    }

    @Override
    public void onClick(View v) {
        String text="";
        String modify="";
        switch (v.getId()){
            case R.id.num_layout:
                text="学号";
                modify=String.valueOf(userDetail.getUserNum());
                break;
            case R.id.email_layout:
                text="学校";
                modify=String.valueOf("华南农业大学");
                break;
            case R.id.birthday_layout:
                text="专业";
                modify=String.valueOf(userDetail.getUserMajor());
                break;
            case R.id.sex_layout:
                text="年级";
                modify=String.valueOf(userDetail.getUserGradle());
                break;
            case R.id.address_layout:
                text="班级";
                modify=String.valueOf(userDetail.getUserClass());
                break;
        }
        new ChangePersonInfoPage(getContext()).onShow(text,modify).show();
    }

    private void init(){
        this.userDetail=new UserDetail();
        this.userDetail.setUserMajor("未填写");
        this.userDetail.setUserClass("未填写");
        this.userDetail.setUserGradle("未填写");
        numTxView.setText("未填写");
        shcoolTxView.setText("未填写");
        majorTxView.setText("未填写");
        gradeTxView.setText("未填写");
        classTxView.setText("未填写");
    }

    public void getData(){
        final String userId=ShareUtils.getUserId()+"";
        ArrayMap<String,String> params=new ArrayMap<>();
        params.put(ParamConstants.USERID,"1");
        //Log.i(TAG,"开始>>>>>>>>>>注册");
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.USER_INFO, params,"获取用户信息成功","获取用户信息失败",new NetWorkHandlerUtils.PostSuccessCallback<UserDetail>() {
            @Override
            public void success(UserDetail result) {
                if(result==null)return;
                PersonInfoPage.this.userDetail=result;
                numTxView.setText(result.getUserNum()+"");
                shcoolTxView.setText("华南农业大学");
                setText(majorTxView,result.getUserMajor());
                setText(gradeTxView,result.getUserGradle());
                setText(classTxView,result.getUserClass());
            }
        },UserDetail.class);
    }

    private void setText(TextView view,String text){
        if(!StringUtils.isEmpty(text)){
            view.setText(text);
        }
    }

    @Override
    public void onUncover(Object arg) {
        super.onUncover(arg);
        getData();
    }
}
