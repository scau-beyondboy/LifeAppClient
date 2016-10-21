package scau.com.lifeappclient.model;


import java.io.Serializable;
import java.util.List;

/**
 * @Author: beyondboy
 * @Gmail: xuguoli.scau@gamil.com
 * @Data: 2016/10/18
 * @Time: 15:40
 */
public class PageInfo<T> implements Serializable {
    private int pageAccount;
    private int pageSize;
    private int Count;
    private List<T> data;
    private static final long serialVersionUID = 1L;
    public int getPageAccount() {
        return pageAccount;
    }

    public void setPageAccount(int pageAccount) {
        this.pageAccount = pageAccount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageAccount=" + pageAccount +
                ", pageSize=" + pageSize +
                ", Count=" + Count +
                ", data=" + data +
                '}';
    }
}
