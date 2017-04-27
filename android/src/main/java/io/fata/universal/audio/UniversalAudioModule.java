package io.fata.universal.audio;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.media.AudioManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import android.util.Log;

public class UniversalAudioModule extends ReactContextBaseJavaModule {
  Map<Integer, MediaPlayer> audioMap = new HashMap<>();
  ReactApplicationContext context;
  final static Object NULL = null;

  public UniversalAudioModule(ReactApplicationContext context) {
    super(context);
    this.context = context;
  }

  protected UniversalAudioPlayer getPlayer(int audioId) {
    return audioMap.get(audioId);
  }

  // REACT

  @Override
  public String getName() {
    return "UniversalAudio";
  }

  @ReactMethod
  public void create(Promise promise) {
    // register new MediaPlayer
    UniversalAudioPlayer player = new UniversalAudioPlayer();
    int audioId = player.getId();
    audioMap.put(audioId, player);

    WritableMap map = Arguments.createMap();
    map.putInt("audioId", audioId);
    promise.resolve(map);
  }

  @ReactMethod
  public void addTextTrack(int audioId, String v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.addTextTrack(v);
  }

  @ReactMethod
  public void canPlayType(int audioId, String mediaType, Promise promise) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    Boolean ret = player.canPlayType(mediaType);
    promise.resolve(ret);
  }

  @ReactMethod
  public void load(int audioId) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.load();
  }

  @ReactMethod
  public void play(int audioId) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.play();
  }

  @ReactMethod
  public void pause(int audioId) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.pause();
  }

  @ReactMethod
  public void setAutoplay(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setAutoplay(v);
  }

  @ReactMethod
  public void setControls(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setControls(v);
  }

  @ReactMethod
  public void setCrossOrigin(int audioId, String v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setCrossOrigin(v);
  }

  @ReactMethod
  public void setCurrentTime(int audioId, float v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setCurrentTime(v);
  }

  @ReactMethod
  public void setDefaultMuted(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setDefaultMuted(v);
  }

  @ReactMethod
  public void setDefaultPlaybackRate(int audioId, float v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setDefaultPlaybackRate(v);
  }

  @ReactMethod
  public void setLoop(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setLoop(v);
  }

  @ReactMethod
  public void setMediaGroup(int audioId, String v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setMediaGroup(v);
  }

  @ReactMethod
  public void setMuted(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setMuted(v);
  }

  @ReactMethod
  public void setPaused(int audioId, Boolean v) {
    if(v) {
      this.pause(audioId);
    } else {
      this.play(audioId);
    }
  }

  @ReactMethod
  public void setPlaybackRate(int audioId, float v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setPlaybackRate(v);
  }

  @ReactMethod
  public void setPreload(int audioId, Boolean v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setPreload(v);
  }

  @ReactMethod
  public void setSource(int audioId, String v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setSource(v);
  }

  @ReactMethod
  public void setVolume(int audioId, float v) {
    MediaPlayer player = getPlayer(audioId);
    if(player == null) return;
    player.setVolume(v):
  }
}
