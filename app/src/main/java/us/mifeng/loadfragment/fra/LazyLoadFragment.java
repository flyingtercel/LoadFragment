package us.mifeng.loadfragment.fra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 黑夜之火 on 2018/6/13.
 */

public abstract class LazyLoadFragment extends Fragment{

    /*定义两个变量，用来标识视图是否已经初始化*/
    protected boolean isInit = false;
    protected boolean isLoad = false;
    private View view;
    protected String TAG = "lazyLoad";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(),container,false);
        isInit = true;
        /*初始化的时候去加载数据*/
        isCanLoadData();
        return view;
    }
    /*视图是否对用户可见*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    public void isCanLoadData() {
        if (!isInit){
            return;
        }
        if (getUserVisibleHint()){
            lazyLoad();
            isLoad = true;
        }else{
            if (isLoad){
                stopLoad();
            }
        }
    }

    /*设置Fragment要显示的布局*/
    protected abstract int setContentView();
    /*获取设置的布局*/
    protected View getContentView(){
        return view;
    }
    /*找出对应的子控件*/
    protected <T extends View> T findViewById(int id){
        return (T) getContentView().findViewById(id);
    }

    /*当视图初始化，对用户可见的情况下去真正的加载数据*/
    protected abstract void lazyLoad();


    /*当视图对用户不可见，并且加载过数据，如果需要切换到其他页面时停止加载数据，可以复写此方法*/
    protected abstract void stopLoad();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }
    protected void showToast(String message){
        if (!TextUtils.isEmpty(message)){
            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
        }
    }
}
