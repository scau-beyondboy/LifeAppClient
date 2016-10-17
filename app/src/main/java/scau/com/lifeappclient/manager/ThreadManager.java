package scau.com.lifeappclient.manager;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-06
 * Time: 18:45
 * 线程池管理
 */
public class ThreadManager
{
    /**创建线程数量*/
    public static int scoolPoolSize=0;
    private static final String TAG = ThreadManager.class.getName();
    private static ExecutorService sExecutorService;
    /** 总共多少任务（根据CPU个数决定创建活动线程的个数,这样取的好处就是可以让手机承受得住） */
    private static final int count = Runtime.getRuntime().availableProcessors();
    /**
     * 创建线程池,最大线程个数为Java虚拟机可用的手机CPU核数
     */
    public static ExecutorService createThreadPool()
    {
        if(scoolPoolSize>count)
            scoolPoolSize=count;
        if(sExecutorService==null)
        {
            sExecutorService= new ThreadPoolExecutor(scoolPoolSize,count,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),new HandlerThreadFactory());
        }
        return sExecutorService;
    }

    /**释放线程资源*/
    public static void release()
    {
        sExecutorService.shutdown();
        sExecutorService=null;
    }

    /**
     * 线程异常处理器
     */
    static class  MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
    {
        @Override
        public void uncaughtException(Thread thread, Throwable ex)
        {
            Log.e(TAG, thread.currentThread().getName() + "线程run方法运行异常", ex);
        }
    }

    /**
     * 线程创建工厂
     */
    static class  HandlerThreadFactory implements ThreadFactory
    {
        @Override
        public Thread newThread(Runnable r)
        {
            Thread t = new Thread(r);
            //设置线程处理器
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            return t;
        }
    }

}
