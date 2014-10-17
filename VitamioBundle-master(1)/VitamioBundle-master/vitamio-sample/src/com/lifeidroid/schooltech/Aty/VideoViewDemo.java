/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lifeidroid.schooltech.Aty;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class VideoViewDemo extends Activity {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media
	 * file path.
	 */
	// private String path =
	// "http://main.gslb.ku6.com/s1/F8h2_PiJDiEkcztx/1187749991000/1cc33640135b35f02e2c42cf0f113b16/1413201206047/v123/200708/22/10/8BTNHSa2tQKFcP5N.flv";
	private String path;
	private VideoView mVideoView;
	private LinearLayout mLoadingView;
	private TextView Logger;
	private MediaController controller;
	private Intent mIntent;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.aty_videoview);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mLoadingView = (LinearLayout) findViewById(R.id.pgb1);
		Logger = (TextView) findViewById(R.id.tv);
		controller = new MediaController(this);
		mIntent = getIntent();
		path = mIntent.getExtras().getString(Config.KEY_CHAPTER_URL);
		if (path == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(VideoViewDemo.this, "该视频无效，请联系客服！",
					Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			mVideoView.setVideoPath(path);
			controller.setPadding(50, 50, 50, 10);
			mVideoView.setMediaController(controller);
			mVideoView.requestFocus();
			mVideoView.start();
			mVideoView.setOnInfoListener(new OnInfoListener() {
				/** 是否需要自动恢复播放，用于自动暂停，恢复播放 */
				private boolean needResume;

				@Override
				public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
					switch (arg1) {
					case MediaPlayer.MEDIA_INFO_BUFFERING_START:
						// 开始缓存，暂停播放
						if (mVideoView.isPlaying()) {
							mVideoView.pause();
							needResume = true;
						}
						mLoadingView.setVisibility(View.VISIBLE);
						break;
					case MediaPlayer.MEDIA_INFO_BUFFERING_END:
						// 缓存完成，继续播放
						if (needResume)
							mVideoView.start();
						mLoadingView.setVisibility(View.GONE);
						break;
					case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
						// 显示 下载速度
						Logger.setText("download rate:" + arg2);
						break;
					}
					return true;
				}
			});

			mVideoView
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							// optional need Vitamio 4.0
							mediaPlayer.setPlaybackSpeed(1.0f);
						}
					});
		}

	}

	public void startPlay(View view) {
		// String url = mEditText.getText().toString();
		// path = url;
		// if (!TextUtils.isEmpty(url)) {
		// mVideoView.setVideoPath(url);
		// }
	}

	public void openVideo(View View) {
		mVideoView.setVideoPath(path);
	}

}
