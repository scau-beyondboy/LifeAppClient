package scau.com.lifeappclient.page;

import android.widget.TextView;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import scau.com.lifeappclient.R;
import scau.com.lifeappclient.model.NoticeInfo;

/**
 * Created by beyondboy on 2016/10/24.
 */
@PageLayout(R.layout.notice_detail_page)
public class NoticeDetailPage extends Page {
    @InjectView(R.id.notice_title)
    private TextView mTitle;
    @InjectView(R.id.notice_desc)
    private TextView mDesc;
    @InjectView(R.id.notice_iden)
    private TextView mIden;
    @InjectView(R.id.notice_date)
    private TextView mDate;
    public NoticeDetailPage(PageActivity pageActivity) {
        super(pageActivity);
    }

    public NoticeDetailPage onShown(NoticeInfo noticeInfo) {
        super.onShown();
        mTitle.setText(noticeInfo.getNoticeTitle());
        mDesc.setText("     "+noticeInfo.getNoticeDesc());
        mIden.setText(noticeInfo.getNoticeIden());
        mDate.setText(noticeInfo.getNoticeDate());
        return this;
    }
}
