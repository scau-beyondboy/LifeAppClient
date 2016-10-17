package scau.com.lifeappclient.page;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.constants.ParamConstants;
import scau.com.lifeappclient.model.Token;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.ShareUtils;
import scau.com.lifeappclient.utils.SoftInputUtils;
import scau.com.lifeappclient.utils.StringUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/16.
 */
@PageLayout(R.layout.register)
public class Register extends Page implements TextView.OnEditorActionListener,View.OnClickListener {
    private static final String TAG = Register.class.getName();
    @InjectView(R.id.account)
    private EditText mEtAccount;
    @InjectView(R.id.pwd1)
    private EditText mEtPwd1;
    @InjectView(value = R.id.pwd2,listenerTypes = TextView.OnEditorActionListener.class)
    private EditText mEtPwd2;
    @InjectView(value = R.id.register,listenerTypes = View.OnClickListener.class)
    private Button mBtRegit;
    public Register(PageActivity pageActivity) {
        super(pageActivity);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEND){
            handlerDone();
        }
        return true;
    }

    public void handlerDone(){
        SoftInputUtils.hideSofeInput(getContext());
        final String account=mEtAccount.getText().toString();
        final String pwd1=mEtPwd1.getText().toString();
        final String pwd2=mEtPwd2.getText().toString();
        if(StringUtils.isEmpty(account,pwd1,pwd2)){
            ToaskUtils.showToast("不能为空");
            return;
        }
        if(!pwd1.equals(pwd2)){
            ToaskUtils.showToast("密码不匹配");
            return;
        }
        ArrayMap<String,String> params=new ArrayMap<>();
        params.put(ParamConstants.USR_NICK,account);
        params.put(ParamConstants.USR_PWD,pwd1);
        params.put(ParamConstants.USE_SEX,"1");
        params.put(ParamConstants.USE_AVA,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSW_KGKOiRg0oTNHgrCNCp4vyjHXQB0GhEAfqWLB3Yupf5LT36v3pPdqw");
        //Log.i(TAG,"开始>>>>>>>>>>注册");
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.USER_REGISTER, params,"注册用户成功","注册用户失败",new NetWorkHandlerUtils.PostSuccessCallback<Token>() {
            @Override
            public void success(Token result) {
                ShareUtils.putUserId(result.getUserId());
                ShareUtils.putUserToken(result.getUserToken());
            }
        },Token.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                handlerDone();
                break;
        }
    }
}
