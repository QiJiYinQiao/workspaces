package com.example.suntest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	private TextView tv_version;
	private LinearLayout ll;
	
	private UpdateInfo info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//���ò�Ҫ��ʾ��ʾ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		//����ȫ����ʾ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		tv_version = (TextView)findViewById(R.id.tv_splash_version);
		String version = getVersion();
		tv_version.setText("�汾��"+version);
		
		ll = (LinearLayout)findViewById(R.id.ll_splash_main);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
		alphaAnimation.setDuration(2000);
		ll.startAnimation(alphaAnimation);
		
		if(isNeedUpdate(version)){
			showUpdateDialog();
		}
	}
	
	private void showUpdateDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("��������");
		builder.setMessage(info.getDescription());
		builder.setCancelable(false);
		
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog,int which){
				
			}
		});
		builder.create().show();
	}
	
	private boolean isNeedUpdate(String version){
		UpdateInfoService updateInfoService = new UpdateInfoService(this);
		try{
			info = updateInfoService.getUpdateInfo(R.string.serverUrl);
			String v = info.getVersion();
			if(v.equals(version)){
				System.out.println("���ø���");
				return false;
			}else{
				System.out.println("Ҫ����");
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(this, "��ȡ������Ϣ�쳣�����Ժ�����", Toast.LENGTH_SHORT);
		}
		return false;
	}
	
	private String getVersion(){
		try{
			PackageManager packageManager = getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			
			return packageInfo.versionName;
		}catch(NameNotFoundException e){
			e.printStackTrace();
			return "�汾��δ֪";
		}
		
	}
	
}
