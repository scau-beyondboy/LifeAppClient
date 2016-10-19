package scau.com.lifeappclient.adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.TelInfo;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/19.
 */
public class ScauTelListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TelInfo> mTelInfoList;

    public ScauTelListItemAdapter(List<TelInfo> mTelInfoList) {
        this.mTelInfoList = mTelInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scau_tel_page_item, parent, false);
        return new ScauTelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ScauTelViewHolder) {
            final ScauTelViewHolder scauTelViewHolder = (ScauTelViewHolder) holder;
            final TelInfo telInfo = mTelInfoList.get(position);
            scauTelViewHolder.mTelDescView.setText(telInfo.getmTelDesc());
            scauTelViewHolder.mLogoView.setImageURI(Uri.parse("res///" + telInfo.getmTelLogo()));
            scauTelViewHolder.mTelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                                + telInfo.getmTelNum()));
                        v.getContext().startActivity(intent);
                    }else{
                        ToaskUtils.showToast("请允许直接\n打电话权限");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTelInfoList!=null? mTelInfoList.size():0;
    }

    static  class ScauTelViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView mLogoView;
        SimpleDraweeView mTelView;
        TextView mTelDescView;
        public ScauTelViewHolder(View itemView) {
            super(itemView);
            mLogoView=(SimpleDraweeView)itemView.findViewById(R.id.tel_logo);
            mTelView=(SimpleDraweeView)itemView.findViewById(R.id.tel);
            mTelDescView=(TextView)itemView.findViewById(R.id.tel_desc);
        }
    }
}
