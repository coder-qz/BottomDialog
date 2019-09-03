package com.zqz.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 章勤志
 * @date : 2019/8/7
 */
public class BottomListDialog extends Dialog {
    private View mContentView;

    public BottomListDialog(@NonNull Context context) {
        super(context, R.style.BottomDialog);
    }

    public BottomListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setContentView(int layoutResID) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        super.setContentView(mContentView);
    }

    @Override
    public void setContentView(@NonNull View view, ViewGroup.LayoutParams params) {
        mContentView = view;
        super.setContentView(view, params);
    }
    @Override
    public void setContentView(@NonNull View view) {
        mContentView = view;
        super.setContentView(view);
    }



    public static class Builder<T> {
        private ListAdapter mAdapter;

        public Builder setOutSideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        private Context mContext;
        private BottomListDialog mDialog;
        private List<T> mList;
        private OnDismissListener mOnDismissListener;
        private OnItemClickListener mOnItemClickListener;
        private boolean mShowCancel;
        private boolean cancelable = true;
        private ListView mListView;
        private View mBottom;

        public Builder(Context context) {
            this(context, false);
        }
        public Builder(Context context, boolean showCancel) {
            mContext = context;
            mList = new ArrayList<>();
            mShowCancel = showCancel;
        }
        public Builder addList(List<T> items) {
            mList.addAll(items);
            return this;
        }
        public Builder addItem(T item) {
            mList.add(item);
            return this;
        }
        public Builder setOnItemClickListener(OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
            return this;
        }
        public Builder setOnDismissListener(OnDismissListener listener) {
            mOnDismissListener = listener;
            return this;
        }
        public interface OnItemClickListener {
            void onClick(BottomListDialog dialog, View itemView, int position);
        }
        public BottomListDialog build() {
            mDialog = new BottomListDialog(mContext);
            View contentView = View.inflate(mContext, R.layout.list_dialog, null);
            mListView =  contentView.findViewById(R.id.listview);
            mBottom =  contentView.findViewById(R.id.ll_bottom);
            View mClose = contentView.findViewById(R.id.rl_close);
            if (mShowCancel){
                mBottom.setVisibility(View.VISIBLE);
            }else{
                mBottom.setVisibility(View.GONE);
            }
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams params = dialogWindow.getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialogWindow.setAttributes(params);
            }

            mDialog.setCancelable(cancelable);//
            mAdapter = new ListAdapter();
            mListView.setAdapter(mAdapter);
            mDialog.setContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (mOnDismissListener != null) {
                mDialog.setOnDismissListener(mOnDismissListener);
            }
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            return mDialog;
        }
        private static class ViewHolder {
            View viewLine;
            TextView tvItem;
        }

        private class ListAdapter extends BaseAdapter {
            private String getContentText(Object item) {
                if (item == null) {
                    return "";
                } else if (item instanceof IBottomData) {
                    return ((IBottomData) item).getPickerViewText();
                }
                return item.toString();
            }
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public T getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                 String data = getContentText(getItem(position));
                 ViewHolder holder;
                if (convertView == null) {
                    convertView =  LayoutInflater.from(mContext).inflate(R.layout.item_bottom_dialog, parent, false);
                    holder = new ViewHolder();
                    holder.viewLine =  convertView.findViewById(R.id.view_line);
                    holder.tvItem =  convertView.findViewById(R.id.tv_item);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.tvItem.setText(data);
                if (position==mList.size()-1){
                    holder.viewLine.setVisibility(View.GONE);
                }else{
                    holder.viewLine.setVisibility(View.VISIBLE);
                }
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onClick(mDialog, view, position);
                        }
                    }
                });

                return convertView;
            }
        }
    }

}
