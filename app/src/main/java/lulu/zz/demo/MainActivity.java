package lulu.zz.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import lulu.zz.demo.fancycoverflow.FancyCoverFlow;
import lulu.zz.demo.fancycoverflow.FancyCoverFlowAdapter;

public class MainActivity extends AppCompatActivity {

	private FancyCoverFlow fancyCoverFlow;
	private Context context;

	private String[] string = new String[]{"条目1", "条目2", "条目3", "条目4", "条目5", "条目6",};


	public static final String[] BANNER_IMGS =
			{
					"http://upload.jianshu.io/admin_banners/app_images/2542/5b04b8ccb01432425cb13dab1aed399da607b454.jpg",
					"http://upload.jianshu.io/admin_banners/app_images/2547/ae57560a074ccbf0213c1ac004e6c8e27e9e4094.jpg",
					"http://upload.jianshu.io/admin_banners/app_images/2383/126ffdb2dacadba08292bc2bf326cbeddb9e13f6.jpg",
					"http://upload.jianshu.io/admin_banners/app_images/2295/bf91c806e3b88e6f38f58c1f20367605f17cd797.jpg",
					"http://upload.jianshu.io/admin_banners/app_images/2474/82506437d78663c5ca0bee823bd4b7bed04b9b96.jpg",
					"http://upload.jianshu.io/admin_banners/app_images/2523/7207d4150a150f14efbdfc4411bf05b0199ceffa.jpg",
			};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		initView();
	}

	private void initView() {

		fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);
		FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams) fancyCoverFlow.getLayoutParams();
		params2.height = this.getResources().getDisplayMetrics().widthPixels / 3 * 2;
		fancyCoverFlow.setLayoutParams(params2);

		fancyCoverFlow.setAdapter(new MyFancyCoverFlowAdapter(context));

	}

	private class MyFancyCoverFlowAdapter extends FancyCoverFlowAdapter {

		private Context context;
		private LayoutInflater inflater;
		private DisplayImageOptions options;


		public MyFancyCoverFlowAdapter(Context context) {
			this.context = context;
			this.inflater = LayoutInflater.from(context);
			// 显示图片的配置
			options = new DisplayImageOptions.Builder()
					.cacheInMemory(true)
					.cacheOnDisk(true)
					.resetViewBeforeLoading(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public Integer getItem(int i) {
			return i;
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getCoverFlowItem(int position, View reuseableView, ViewGroup viewGroup) {

			ViewGroup vg = null;
			ViewHolder holder = null;

			if (reuseableView != null) {
				vg = (ViewGroup) reuseableView;
				holder = (ViewHolder) vg.getTag();
			} else {
				holder = new ViewHolder();

				vg = (ViewGroup) inflater.inflate(R.layout.layout_item_cover2, null);
				holder.tv_catname = (TextView) vg.findViewById(R.id.tv_catname);
				holder.iv01 = (ImageView) vg.findViewById(R.id.iv01);
				holder.iv02 = (ImageView) vg.findViewById(R.id.iv02);
				holder.iv03 = (ImageView) vg.findViewById(R.id.iv03);
				holder.iv04 = (ImageView) vg.findViewById(R.id.iv04);

				vg.setTag(holder);
			}

			vg.setLayoutParams(new FancyCoverFlow.LayoutParams(CustomApplication.app.displayMetrics.widthPixels / 5 * 3, CustomApplication.app.displayMetrics.widthPixels / 5 * 3 + utils.dip2px(context, 40)));


			holder.tv_catname.setText( string[position % string.length]);
			holder.tv_catname.setTag( string[position % string.length]);
			holder.tv_catname.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
				}
			});

			ImageView[] ivs = new ImageView[]{holder.iv01, holder.iv02, holder.iv03, holder.iv04};

			for (int j = 0; j < ivs.length; j++) {
				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivs[j].getLayoutParams();
				params.width = CustomApplication.app.displayMetrics.widthPixels / 10 * 3 - 2;
				params.height = CustomApplication.app.displayMetrics.widthPixels / 10 * 3 - 2;
				params.setMargins(1, 1, 1, 1);
				ivs[j].setLayoutParams(params);

				if (j < BANNER_IMGS.length) {
					ImageLoader.getInstance().displayImage(BANNER_IMGS[j], ivs[j], options);
					ivs[j].setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

						}
					});

				} else {
					ivs[j].setImageResource(0);

					ivs[j].setOnClickListener(null);
				}

			}
			return vg;
		}


		public class ViewHolder {
			public TextView tv_catname;
			public ImageView iv01, iv02, iv03, iv04;
		}
	}
}
