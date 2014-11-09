package com.andro_yce.safedelhi.adapters;

import com.andro_yce.safedelhi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomGrid extends BaseAdapter{
    private Context mContext;
    private final String[] options;
    private final int[] Imageid;
      public CustomGrid(Context c,String[] options,int[] Imageid ) {
          mContext = c;
          this.Imageid = Imageid;
          this.options = options;
      }
    @Override
    public int getCount() {
      // TODO Auto-generated method stub
      return options.length;
    }
    @Override
    public Object getItem(int position) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override
    public long getItemId(int position) {
      // TODO Auto-generated method stub
      return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // TODO Auto-generated method stub
      View grid;
      LayoutInflater inflater = (LayoutInflater) mContext
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          if (convertView == null) {
            grid = new View(mContext);
        grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(options[position]);
            imageView.setImageResource(Imageid[position]);
          } else {
            grid = (View) convertView;
          }
      return grid;
    }
}