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
@PageLayout(R.layout.login)
public class LoginPage extends Page implements View.OnClickListener,TextView.OnEditorActionListener {
    private static final String TAG = LoginPage.class.getName();
    @InjectView(value = R.id.account)
    private EditText mEtAccount;
    @InjectView(value = R.id.password,listenerTypes = TextView.OnEditorActionListener.class)
    private EditText mEtPwd;
    @InjectView(value = R.id.login,listenerTypes = View.OnClickListener.class)
    private Button mBtLogin;
    @InjectView(value = R.id.register,listenerTypes = View.OnClickListener.class)
    private TextView mTvRegist;
    public LoginPage(PageActivity activity){
        super(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                new Register(getContext()).show(true);
                break;
            case R.id.login:
                handlerDone();
                break;
        }
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
        final String pwd=mEtPwd.getText().toString();
        if(StringUtils.isEmpty(account,pwd)){
            ToaskUtils.showToast("不能为空");
            return;
        }
        ArrayMap<String,String> params=new ArrayMap<>();
        params.put(ParamConstants.NICK,account);
        params.put(ParamConstants.PWD,pwd);
        //Log.i(TAG,"开始>>>>>>>>>>注册");
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.USER_LOGIN, params,"用户登录成功","用户登录失败",new NetWorkHandlerUtils.PostSuccessCallback<Token>() {
            @Override
            public void success(Token result) {
                ShareUtils.putUserId(result.getUserId());
                ShareUtils.putUserToken(result.getUserToken());
            }
        },Token.class);
    }
}
