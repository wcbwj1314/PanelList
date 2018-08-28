package sysu.zyb.panellistlibrary.defaultcontent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sysu.zyb.panellistlibrary.CheckableLinearLayout;
import sysu.zyb.panellistlibrary.R;

/**
 * <pre>
 *     @author: zyb
 *     email  : hbdxzyb@hotmail.com
 *     time   : 2018/4/15 下午1:25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DefaultContentAdapter extends ArrayAdapter<List<String>> {

    private int contentItemSize;

    private List<Integer> itemWidthList;

    private ListView lv_content;
    private int itemHeight;

    public DefaultContentAdapter(@NonNull Context context, int resource,
                                 @NonNull List<List<String>> objects, List<Integer> itemWidthList,
                                 int itemHeight, ListView lv_content) {
        super(context, resource, objects);
        this.contentItemSize = itemWidthList.size();
        this.itemWidthList = itemWidthList;
        this.lv_content = lv_content;
        this.itemHeight = itemHeight;
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        Log.d("ybz", "getCount: " + count);
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        List<String> itemData = getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.defaultcontentitem, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        for (int i = 0; i < contentItemSize; i++) {
            viewHolder.contentTextViewList.get(i).setText(itemData != null ? itemData.get(i) : "");
        }

        if (lv_content.isItemChecked(position)) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.colorSelected));
        } else {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.colorDeselected));
        }

        return view;
    }

    class ViewHolder {

        List<TextView> contentTextViewList = new ArrayList<>(contentItemSize);

        ViewHolder(View view) {
            CheckableLinearLayout layout = (CheckableLinearLayout) view;
            for (int i = 0; i < contentItemSize; i++) {
                TextView item = (TextView) View.inflate(getContext(), R.layout.column_content_item, null);
                try {
                    item.setWidth(itemWidthList.get(i));
                } catch (Exception e) {
                    item.setWidth(0);
                }
                item.setHeight(itemHeight);
                layout.addView(item);
                contentTextViewList.add(item);
            }
        }
    }
}
