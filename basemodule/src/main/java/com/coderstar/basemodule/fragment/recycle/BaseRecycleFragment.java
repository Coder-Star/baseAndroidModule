package com.coderstar.basemodule.fragment.recycle;



import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.coderstar.basemodule.R;
import com.coderstar.basemodule.activity.BaseActivity;
import com.coderstar.basemodule.activity.recycle.BaseRecyclerActivity;
import com.coderstar.basemodule.fragment.base.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseRecycleFragment<T> extends BaseFragment {
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected SwipeMenuRecyclerView swipeMenuRecyclerView;
    protected RvAdapter rvAdapter;
    //list布局
    protected static final int LINEAR_LAYOUT_MANAGER = 0;
    //grid 布局
    protected static final int GRID_LAYOUT_MANAGER = 1;
    //瀑布流布局
    protected static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
    //默认为0 单行布局
    private int listType = 0;
    //排列方式默认垂直
    private boolean isVertical = true;
    //gird布局与瀑布流布局默认单行数量
    private int spanCount = 1;
    //子布局ID
    private int layoutResId = -1;
    //是否可刷新，默认可以
    private boolean canRefresh = true;
    private boolean canLoadMore = true;
    private int lastVisibleItem;
    LinearLayoutManager linearLayoutManager = null;
    public int page = 1;
    public String sidx = "";
    private String sord = "desc";
    private int rows = 10;//每页显示的记录数

    private BaseActivity baseActivity;


    public BaseRecycleFragment () {

    }

    public void setBaseActivity (BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView (inflater,container,savedInstanceState);
        TextView textView = new TextView (getActivity ());
        textView.setText (R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    protected void initView (View view) {
        initItemLayout ();
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById (R.id.base_swipe_refresh_lay);
        swipeMenuRecyclerView = (SwipeMenuRecyclerView)view.findViewById (R.id.base_rv_list);
        setRefreshEnable (canRefresh);
        chooseListTye (listType,isVertical);
        if(layoutResId == -1){
            throw new RuntimeException ("layoutResId is null!");
        }
        rvAdapter = new RvAdapter (layoutResId,new ArrayList<T> ());
        swipeMenuRecyclerView.setAdapter (rvAdapter);
    }

    @Override
    protected void initEvents (View view) {
        if(canRefresh){
            //设置下拉刷新
            setRefresh ();
        }
        //设置列表点击事件
        setItemCick ();
        if(canLoadMore){
            //设置加载更多
            setLoadMoreEnable ();
        }
    }
    public void setEmpty(){
        rvAdapter.setNewData (null);
        View emptyView=baseActivity.getLayoutInflater().inflate(R.layout.empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rvAdapter.setEmptyView (emptyView);
    }
    /**
     * @author coderstar
     * @createon 2018-07-14 16:35
     * @Describe 设置子布局id
     */
    public void setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }
    /**
     * @author coderstar
     * @createon 2018-07-14 16:23
     * @Describe 初始化子布局
     */
    protected abstract void initItemLayout();

    /**
     * @author coderstar
     * @createon 2018-07-14 16:25
     * @Describe 设置下拉刷新
     * 下拉刷新功能是否可用，true：允许
     *                     false：禁止
     */
    protected void setRefreshEnable(boolean canRefresh){
        if(canRefresh){
            swipeRefreshLayout.setEnabled (true);
        }else{
            swipeRefreshLayout.setEnabled (false);
        }
    }

    /**
     * @author coderstar
     * @createon 2018-07-14 16:27
     * @Describe 设置加载更多
     */
    protected void setLoadMoreEnable(){
        //添加滚动监听
        swipeMenuRecyclerView.addOnScrollListener (onScrollListener);
    }

    /**
     * @author coderstar
     * @createon 2018-07-16 15:21
     * @Describe 开启下拉刷新
     */
    protected void setRefresh(){
        swipeRefreshLayout.setOnRefreshListener (onRefreshListener);
    }

    protected void setItemCick(){
        swipeMenuRecyclerView.addOnItemTouchListener (onItemTouchListener);
    }


    /**
     * @author coderstar
     * @createon 2018-07-14 16:28
     * @Describe 设置布局类型
     */
    protected void setListType(int type,boolean isVertical,boolean canRefresh){
        this.listType = type;
        this.isVertical = isVertical;
        this.canRefresh = canRefresh;
    }

    /**
     * @author coderstar
     * @createon 2018-07-14 16:28
     * @Describe 设置布局类型
     */
    protected void setListType(int type,boolean isVertical,boolean canRefresh,boolean canLoadMore){
        this.listType = type;
        this.isVertical = isVertical;
        this.canRefresh = canRefresh;
        this.canLoadMore = canLoadMore;
    }

    /**
     * @author coderstar
     * @createon 2018-07-14 16:29
     * @Describe 设置grid样式和瀑布流横向或者纵向数量
     */
    protected void setSpanCount(int spanCount){
        if(spanCount > 0){
            this.spanCount = spanCount;
        }
    }

    /**
     * @author coderstar
     * @createon 2018-07-14 16:31
     * @Describe 设置布局管理器
     */
    private void chooseListTye(int listType,boolean isVertical){
        switch (listType) {
            case LINEAR_LAYOUT_MANAGER:
                linearLayoutManager = new LinearLayoutManager(getContext ());
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case GRID_LAYOUT_MANAGER:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext (), spanCount);
                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                    (spanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                linearLayoutManager = new LinearLayoutManager(getContext ());
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
                break;
        }
    }

    /**
     * @author coderstar
     * @createon 2018-07-14 16:16
     * @Describe 适配器内部处理
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

    public class RvAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public RvAdapter(int layoutResId, List<T> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            MyHolder(baseViewHolder, t);
        }
    }

    //下拉刷新监听
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener () {
        @Override
        public void onRefresh () {
            loadFirstData();
        }
    };

    /**
     * @author coderstar
     * @createon 2018-07-16 15:15
     * @Describe 加载第一页数据
     */
    protected abstract void loadFirstData();

    /**
     * @author coderstar
     * @createon 2018-07-16 15:16
     * @Describe 加载数据成功
     */
    public void loadDataSuccess(){
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * @author coderstar
     * @createon 2018-07-16 15:27
     * @Describe 列表点击事件
     */
    private RecyclerView.OnItemTouchListener onItemTouchListener = new OnItemClickListener () {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
//            showToast (MyRecyActivity.this,rvAdapter.getItem (position).getTitle ());
            itemClick(position);
        }
    };

    /**
     * @author coderstar
     * @createon 2018-07-16 15:28
     * @Describe 列表点击事件
     */
    protected abstract void itemClick(int position);

    /**
     * @author coderstar
     * @createon 2018-07-16 15:39
     * @Describe 加载更多监听事件
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener () {
        @Override
        public void onScrolled (RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled (recyclerView, dx, dy);
            if(listType == LINEAR_LAYOUT_MANAGER){
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        }

        @Override
        public void onScrollStateChanged (RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged (recyclerView, newState);
            if(listType == LINEAR_LAYOUT_MANAGER){
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +2 > linearLayoutManager.getItemCount()){
//                    swipeRefreshLayout.setRefreshing (true);
                    loadMoreData ();
                }
            }

        }
    };
    protected abstract void loadMoreData();

    /**
     * @author coderstar
     * @createon 2018-08-11 17:06
     * @Describe  数据展示
     */
    protected void showList(List<T> dataList){
        if(dataList != null && dataList.size () > 0){
            Log.d (TAG,"dataList.get(0) = " + dataList.get (0).toString ());
            rvAdapter.addData (dataList);
        }else{
            if(page == 1){
                setEmpty ();
            }else{
                baseActivity.showToast (baseActivity,R.string.no_more);
            }
        }
    }
    /**
     * @author coderstar
     * @createon 2018-08-18 15:02
     * @Describe 初始化查询参数
     */
    public void initParas(String sidx,String sord,int rows){
        initParas (sidx,sord);
        this.rows = rows;
    }

    public void initParas(String sidx,String sord){
        this.sidx = sidx;
        this.sord = sord;
    }
}
