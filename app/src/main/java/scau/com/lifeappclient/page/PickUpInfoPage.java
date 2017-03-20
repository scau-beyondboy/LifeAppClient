package scau.com.lifeappclient.page;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InsertPageLayout;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.model.PickUpInfoWithBLOBs;
import scau.com.lifeappclient.utils.NetWorkHandlerUtils;
import scau.com.lifeappclient.utils.SoftInputUtils;
import scau.com.lifeappclient.utils.StringUtils;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2017/1/2.
 */
//@PageLayout(R.layout.pickup_info)
@InsertPageLayout(value = R.layout.pickup_info,parent = R.id.container)
public class PickUpInfoPage extends ToolBarPage implements View.OnClickListener {
    private static final SimpleDateFormat dateform=new SimpleDateFormat("yyyy-MM-dd");
    @InjectView(R.id.pickup_desc)
    private EditText pickupDesc;
    @InjectView(R.id.card_num_et)
    private EditText cardNumEt;
    @InjectView(R.id.phone_et)
    private EditText phoneEt;
    @InjectView(value = R.id.submmit,listener = View.OnClickListener.class)
    private Button submmit;
    public PickUpInfoPage(PageActivity pageActivity) {
        super(pageActivity);
        setTitleText("失物描述");
    }

    @Override
    public void onShown() {
        super.onShown();
        submmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.submmit:
                handlerDone();
                break;
        }
    }

    public void handlerDone(){
        SoftInputUtils.hideSofeInput(getContext());
        final String desc=pickupDesc.getText().toString();
        final String phone=phoneEt.getText().toString();
        final String cardNum=cardNumEt.getText().toString();
        if(StringUtils.isEmpty(phone,cardNum,desc)){
            ToaskUtils.showToast("不能为空");
            return;
        }
        PickUpInfoWithBLOBs pickUpInfoWithBLOBs=new PickUpInfoWithBLOBs();
        pickUpInfoWithBLOBs.setPickupDesc(desc);
        pickUpInfoWithBLOBs.setPickupPhone(phone);
        pickUpInfoWithBLOBs.setPickupCardNum(Long.valueOf("2014243242"));
        pickUpInfoWithBLOBs.setPickupImage(cardNum);
        pickUpInfoWithBLOBs.setPickupDate(dateform.format(new Date()));
        //Log.i(TAG,"开始>>>>>>>>>>注册");
        NetWorkHandlerUtils.postAsynHandler(NetWorkConstants.ADD_PICKUP_INFO,pickUpInfoWithBLOBs,new NetWorkHandlerUtils.PostSuccessCallback<Object>() {
            @Override
            public void success(Object result) {
              ToaskUtils.showToast("提交信息成功");
            }
        });
    }
}
