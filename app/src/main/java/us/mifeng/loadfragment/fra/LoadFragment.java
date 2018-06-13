package us.mifeng.loadfragment.fra;


import android.util.Log;

import us.mifeng.loadfragment.R;

public class LoadFragment extends LazyLoadFragment {

    String str ;
    private  LoadFragment(String  str) {
        this.str = str;
    }
    public static LoadFragment getFragmentInstance(String str){
        return new LoadFragment(str);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_load;
    }

    @Override
    protected void lazyLoad() {
        String message = str + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据")+">>>>>>>>>>>>>>>>>>>";
        showToast(message);
        Log.d(TAG, message);
    }


    @Override
    protected void stopLoad() {
        Log.d(TAG, str + "已经对用户不可见，可以停止加载数据");
    }
}
