package lulu.zz.demo;

import android.app.Application;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class CustomApplication extends Application {

	public static CustomApplication app = null;
	public DisplayMetrics displayMetrics;





	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		getScreenSize();
		initImgLoader();

	}

	/**
	 * 初始化图片加载器
	 */
	public void initImgLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(0)
				.showImageOnFail(0)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.discCacheSize(50 * 1024 * 1024)//
				.discCacheFileCount(100)//
				.writeDebugLogs()
				.build();

		ImageLoader.getInstance().init(config);
	}

	/**
	 * 获取屏幕的宽高度（像素）
	 */
	public DisplayMetrics getScreenSize() {
		displayMetrics = this.getResources().getDisplayMetrics();
		System.out.println("当前设备屏幕高度（像素）:" + displayMetrics.heightPixels);
		System.out.println("当前设备屏幕宽度（像素）:" + displayMetrics.widthPixels);
		return displayMetrics;
	}

}