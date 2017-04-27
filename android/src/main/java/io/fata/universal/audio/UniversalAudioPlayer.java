package io.fata.universal.audio;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnTimedMetaDataAvailableListener;
import android.media.MediaPlayer.OnTimedTextListener;
import android.media.TimedMetaData;
import android.media.TimedText;
import android.net.Uri;
import android.media.AudioManager;
import android.util.Log;
import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

public class UniversalAudioPlayer {
  protected static int __id__ = 0;

  protected int id;
  protected MediaPlayer player = null;
  protected ReactContext context;
  protected WritableMap data = Arguments.createMap();

  protected void emitEvent(String type) {
    WritableMap data = Arguments.createMap();
    this.emitEvent(type, data);
  }
  protected void emitEvent(String type, WritableMap data) {
    data.putString("type", type);
    RCTDeviceEventEmitter emitter = context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    emitter.emit("UniversalAudioEvent", data);

  }
  public UniversalAudioPlayer(ReactContext context) {
    this.context = context;
    this.id = __id__++;

    // default settings
    this.setAudioTracks("");
    this.setAutoplay(false);
    this._setBuffered(false);
    // this.setController(false);
    this.setControls(false);
    // this.setCrossOrigin("");
    this._setCurrentSrc("");
    this.setCurrentTime(0.0);
    this.setDefaultMuted(false);
    this.setDefaultPlaybackRate(1.0);
    this._setDuration(0.0);
    this._setEnded(false);
    this._setError("");
    this.setLoop(false);
    this.setMediaGroup("");
    this.setMuted(false);
    this._setNetworkState("");
    this.setPaused(false);
    this.setPlaybackRate(1.0);
    this._setPlayed(false);
    this.setPreload("auto");
    this._setSeekable(true);
    this._setSeeking(false);
    this.setSource("");
    this.setTextTracks("");
    this.setVolume(1.0);
  }
  protected void setData(String key, double value) {
    this.data.putDouble(key, value);
  }
  protected void setData(String key, String value) {
    this.data.putString(key, value);
  }
  protected void setData(String key, Boolean value) {
    this.data.putBoolean(key, value);
  }
  protected double getDouble(String key) {
    return this.data.getDouble(key);
  }
  protected String getString(String key) {
    return this.data.getString(key);
  }
  protected Boolean getBoolean(String key) {
    return this.data.getBoolean(key);
  }
  public int getId() {
    return this.id;
  }

  public void addTextTrack(String v) {
  }
  public Boolean canPlayType(String mediaType) {
    return false;
  }
  public void load() {
    if(player == null) return;
    // TODO
  }
  public void play() {
    if(player == null) return;
    if(player.isPlaying()) return;
    int pos = player.getCurrentPosition();
    player.seekTo(pos);
    player.start();
  }
  public void pause() {
    if(player == null) return;
    player.pause();
    this.setData("paused", true);
  }
  public void setAudioTracks(String v) {
    this.setData("audioTracks", v);
  }
  public void setAutoplay(Boolean v) {
    this.setData("autoplay", v);
    if(player == null) return;
    // TODO
  }
  protected void _setBuffered(Boolean v) {
    this.setData("buffered", v);
  }
  // public void setController(Boolean v) {
  //   this.setData("controller", v);
  // }
  public void setControls(Boolean v) {
    this.setData("controls", v);
    if(player == null) return;
    // TODO
  }
  // public void setCrossOrigin(String v) {
  //   this.setData("crossOrigin", v);
  // }
  protected void _setCurrentSrc(String v) {
    this.setData("currentSrc", v);
  }
  public void setCurrentTime(double v) {
    this.setData("currentTime", v);
    if(player == null) return;
    player.seekTo((int)(v * 1000.0f));
    this._setSeeking(true);
    this.emitEvent("seeking");
  }
  public void setDefaultMuted(Boolean v) {
    this.setData("defaultMuted", v);
    if(player == null) return;
    // TODO
  }
  public void setDefaultPlaybackRate(double v) {
    this.setData("defaultPlaybackRate", v);
    if(player == null) return;
    // TODO
  }
  protected void _setDuration(double v) {
    this.setData("duration", v);
  }
  protected void _setEnded(Boolean v) {
    this.setData("ended", v);
  }
  protected void _setError(String v) {
    this.setData("error", v);
  }
  public void setLoop(Boolean v) {
    this.setData("loop", v);
    if(player == null) return;
    // TODO
  }
  public void setMediaGroup(String v) {
    this.setData("mediaGroup", v);
    if(player == null) return;
    // TODO
  }
  public void setMuted(Boolean v) {
    this.setData("muted", v);
    if(player == null) return;
    player.setVolume(0, 0);
  }
  protected void _setNetworkState(String v) {
    this.setData("networkState", v);
  }
  public void setPaused(Boolean v) {
    if(v) {
      this.pause();
    } else {
      this.play();
    }
  }
  public void setPlaybackRate(double v) {
    this.setData("playbackRate", v);
    if(player == null) return;
    // TODO
  }
  protected void _setPlayed(Boolean v) {
    this.setData("played", v);
  }
  public void setPreload(String v) {
    this.setData("preload", v);
    if(player == null) return;
    // TODO
  }
  protected void _setSeekable(Boolean v) {
    this.setData("seekable", v);
  }
  protected void _setSeeking(Boolean v) {
    this.setData("seeking", v);
  }
  public void setTextTracks(String v) {
    this.setData("textTracks", v);
  }
  public void setVolume(double v) {
    this.setData("volume", v);
    if(player == null) return;
    if(this.getBoolean("muted") == false) {
      player.setVolume((float)v, (float)v);
      this.emitEvent("volumechange");
    }
  }
  public void setSource(String source) {
    int res = this.context.getResources().getIdentifier(source, "raw", this.context.getPackageName());
    if(res != 0) {
      player = MediaPlayer.create(this.context, res);
    }

    // TODO: data-uri
    
    // remote
    if(source.startsWith("http://") || source.startsWith("https://")) {
      player = new MediaPlayer();
      player.setAudioStreamType(AudioManager.STREAM_MUSIC);
      try {
        player.setDataSource(source);
      } catch(IOException e) {
        Log.e("UniversalAudioModule", "Exception", e);
      }
    }
    // file
    else {
      File file = new File(source);
      if(file.exists()) {
        Uri uri = Uri.fromFile(file);
        player = MediaPlayer.create(this.context, uri);
      }
    }

    try {
      player.prepare();
    } catch (Exception e) {
      Log.e("UniversalAudioModule", "Exception", e);
    }

    final UniversalAudioPlayer self = this;

    // event
    player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
      @Override
      public synchronized void onBufferingUpdate(MediaPlayer mp, int percent) {
      }
    });
    player.setOnCompletionListener(new OnCompletionListener() {
      @Override
      public synchronized void onCompletion(MediaPlayer mp) {
      }
    });
    player.setOnErrorListener(new OnErrorListener() {
      @Override
      public synchronized boolean onError(MediaPlayer mp, int what, int extra) {
        switch(extra) {
          case MediaPlayer.MEDIA_ERROR_IO:
            self._setError("MEDIA_ERROR_IO");
            break;
          case MediaPlayer.MEDIA_ERROR_MALFORMED:
            self._setError("MEDIA_ERROR_MALFORMED");
            break;
          case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
            self._setError("MEDIA_ERROR_UNSUPPORTED");
            break;
          case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
            self._setError("MEDIA_ERROR_TIMED_OUT");
            break;
        }
        return true;
      }
    });
    player.setOnInfoListener(new OnInfoListener() {
      @Override
      public synchronized boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch(what) {
          case MediaPlayer.MEDIA_INFO_UNKNOWN:
            break;
          case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
            break;
          case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
            break;
          case MediaPlayer.MEDIA_INFO_BUFFERING_START:
            self._setBuffered(false);
            break;
          case MediaPlayer.MEDIA_INFO_BUFFERING_END:
            self._setBuffered(true);
            break;
          case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
            break;
          case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
            self._setSeekable(false);
            break;
          case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
            break;
          case MediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE:
            break;
          case MediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
        }
        return true;
      }
    });
    player.setOnPreparedListener(new OnPreparedListener() {
      @Override
      public synchronized void onPrepared(MediaPlayer mp) {
      }
    });
    player.setOnSeekCompleteListener(new OnSeekCompleteListener() {
      @Override
      public synchronized void onSeekComplete(MediaPlayer mp) {
        self._setSeeking(false);
        self.emitEvent("seeked");
      }
    });
    player.setOnTimedMetaDataAvailableListener(new OnTimedMetaDataAvailableListener() {
      @Override
      public synchronized void onTimedMetaDataAvailable(MediaPlayer mp, TimedMetaData data) {
      }
    });
    player.setOnTimedTextListener(new OnTimedTextListener() {
      @Override
      public synchronized void onTimedText(MediaPlayer mp, TimedText text) {
      }
    });
  }

  // public void release(final Integer key) {
  //   player.release();
  // }

}
// abort	Fires when the loading of an audio/video is aborted
// canplay	Fires when the browser can start playing the audio/video
// canplaythrough	Fires when the browser can play through the audio/video without stopping for buffering
// durationchange	Fires when the duration of the audio/video is changed
// emptied	Fires when the current playlist is empty
// ended	Fires when the current playlist is ended
// error	Fires when an error occurred during the loading of an audio/video
// loadeddata	Fires when the browser has loaded the current frame of the audio/video
// loadedmetadata	Fires when the browser has loaded meta data for the audio/video
// loadstart	Fires when the browser starts looking for the audio/video
// pause	Fires when the audio/video has been paused
// play	Fires when the audio/video has been started or is no longer paused
// playing	Fires when the audio/video is playing after having been paused or stopped for buffering
// progress	Fires when the browser is downloading the audio/video
// ratechange	Fires when the playing speed of the audio/video is changed
  // seeked	Fires when the user is finished moving/skipping to a new position in the audio/video
  // seeking	Fires when the user starts moving/skipping to a new position in the audio/video
// stalled	Fires when the browser is trying to get media data, but data is not available
// suspend	Fires when the browser is intentionally not getting media data
// timeupdate	Fires when the current playback position has changed
  // volumechange	Fires when the volume has been changed
// waiting	Fires when the video stops because it needs to buffer the next frame
