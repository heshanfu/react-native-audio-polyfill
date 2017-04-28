#import "UniversalAudioPlayer.h"

static int __id__ = 0;

@implementation UniversalAudioPlayer {
  AVAudioPlayer *player;
  NSMutableDictionary *data;
}

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"UniversalAudioEvent"];
}

- (id)init {
  if(self = [super init]) {
    self.id = [NSNumber numberWithInt:__id__++];

    // default settings
    [self setAudioTracks:@""];
    [self setAutoplay:NO];
    [self _setBuffered:NO];
    //[self etController:NO];
    [self setControls:NO];
    // [self setCrossOrigin:""];
    [self _setCurrentSrc:@""];
    [self setCurrentTime:0.0];
    [self setDefaultMuted:NO];
    [self setDefaultPlaybackRate:1.0];
    [self _setDuration:0.0];
    [self _setEnded:NO];
    [self _setError:@""];
    [self setLoop:NO];
    [self setMediaGroup:@""];
    [self setMuted:NO];
    [self _setNetworkState:@""];
    [self setPaused:NO];
    [self setPlaybackRate:1.0];
    [self _setPlayed:NO];
    [self setPreload:@"auto"];
    [self _setSeekable:YES];
    [self _setSeeking:NO];
    [self setSource:@""];
    [self setTextTracks:@""];
    [self setVolume:1.0];

  }
  return self;
}

- (void)emitEvent:(NSString *)type {
  [self sendEventWithName:@"UniversalAudioEvent"
                     body:@{@"audioId": self.id, @"type": type} ];
}

- (void)setDouble:(double)value forKey:(NSString *)key {
  [data setValue:[NSNumber numberWithDouble:value] forKey:key];
}

- (void)setString:(NSString *)value forKey:(NSString *)key {
  [data setValue:value forKey:key];
}

- (void)setBool:(BOOL)value forKey:(NSString *)key {
  [data setValue:[NSNumber numberWithBool:value] forKey:key];
}

- (double)getDoubleForKey:(NSString *)key {
  NSObject *ret = [data valueForKey:key];
  if(ret == nil) return 0.0;
  return [(NSNumber *)ret doubleValue];
}

- (NSString *)getStringForKey:(NSString *)key {
  NSObject *ret = [data valueForKey:key];
  if(ret == nil) return @"";
  return (NSString *)ret;
}

- (BOOL)getBoolForKey:(NSString *)key {
  NSObject *ret = [data valueForKey:key];
  if(ret == nil) return NO;
  return [(NSNumber *)ret boolValue];
}

- (void)addTextTrack:(NSString *)v {
}

- (BOOL)canPlayType:(NSString *)mediaType {
  return NO;
}

- (void)load {
  if(player == nil) return;
  // TODO
}

- (void)play:(double)pos {
  player.currentTime = pos;
  [player play];
  [self emitEvent:@"play"];
  [self emitEvent:@"playing"];
  
//  final UniversalAudioPlayer self = this;
//  
//  timer = new Timer();
//  timer.scheduleAtFixedRate(new TimerTask() {
//    @Override
//    - (void)run() {
//      if(player.isPlaying()) {
//        self.setData("currentTime", player.getCurrentPosition() / 1000.0);
//        self.emitEvent("timeupdate");
//      } else {
//        timer.cancel();
//        timer.purge();
//      }
//    }
//  }, 0, 1000);
}

- (void)play {
  if(player == nil) return;
  if([player isPlaying]) return;
  double pos = player.currentTime;
  [self play:pos];
}

- (void)pause {
  if(player == nil) return;
  [player pause];
  [self setBool:YES forKey:@"paused"];
  [self emitEvent:@"pause"];
}

- (void)setAudioTracks:(NSString *)v {
  [self setString:v forKey:@"audioTracks"];
}

- (void)setAutoplay:(BOOL)v {
  [self setBool:v forKey:@"autoplay"];
  if(player == nil) return;
  // TODO
}

- (void)_setBuffered:(BOOL)v {
  [self setBool:v forKey:@"buffered"];
}

// - (void)setController:(BOOL)v {
//   [self setBool:v forKey:@"controller"];
// }

- (void)setControls:(BOOL)v {
  [self setBool:v forKey:@"controls"];
  if(player == nil) return;
  // TODO
}

// - (void)setCrossOrigin:(NSString *)v {
//   [self setValue:v forKey:@"crossOrigin"];
// }

- (void)_setCurrentSrc:(NSString *)v {
  [self setString:v forKey:@"currentSrc"];
}

- (void)setCurrentTime:(double)v {
  [self setDouble:v forKey:@"currentTime"];
  if(player == nil) return;
  [self _setSeeking:YES];
  [self emitEvent:@"seeking"];
  player.currentTime = v * 1000.0f;
  [self _setSeeking:NO];
  [self emitEvent:@"seeked"];
}

- (void)setDefaultMuted:(BOOL)v {
  [self setBool:v forKey:@"defaultMuted"];
}

- (void)setDefaultPlaybackRate:(double)v {
  [self setDouble:v forKey:@"defaultPlaybackRate"];
}

- (void)_setDuration:(double)v {
  double current = [self getDoubleForKey:@"duration"];
  if(current != v) {
    [self setDouble:v forKey:@"duration"];
    [self emitEvent:@"durationchange"];
  }
}

- (void)_setEnded:(BOOL)v {
  [self setBool:v forKey:@"ended"];
}

- (void)_setError:(NSString *)v {
  [self setString:v forKey:@"error"];
}

- (void)setLoop:(BOOL)v {
  [self setBool:v forKey:@"loop"];
}

- (void)setMediaGroup:(NSString *)v {
  [self setString:v forKey:@"mediaGroup"];
}

- (void)setMuted:(BOOL)v {
  [self setBool:v forKey:@"muted"];
  if(player == nil) return;
  if(v) {
    player.volume = 0;
  } else {
    player.volume = [self getDoubleForKey:@"volume"];
  }
}

- (void)_setNetworkState:(NSString *)v {
  [self setString:v forKey:@"networkState"];
}

- (void)setPaused:(BOOL)v {
  if(v) {
    [self pause];
  } else {
    [self play];
  }
}
     
- (void)setPlaybackRate:(double)v {
  [self setDouble:v forKey:@"playbackRate"];
  if(player == nil) return;
  player.rate = v;
}
     
- (void)_setPlayed:(BOOL)v {
  [self setBool:v forKey:@"played"];
}
     
- (void)setPreload:(NSString *)v {
  [self setString:v forKey:@"preload"];
  if(player == nil) return;
  // TODO
}
     
- (void)_setSeekable:(BOOL)v {
  [self setBool:v forKey:@"seekable"];
}
     
- (void)_setSeeking:(BOOL)v {
  [self setBool:v forKey:@"seeking"];
}
     
- (void)setTextTracks:(NSString *)v {
  [self setString:v forKey:@"textTracks"];
}
     
- (void)setVolume:(double)v {
  [self setDouble:v forKey:@"volume"];
  if(player == nil) return;
  if([self getBoolForKey:@"muted"] == NO) {
    player.volume = v;
    [self emitEvent:@"volumechange"];
  }
}

- (void)setSource:(NSString *)source {

  // TODO: data-uri
  
  NSError* error;
  NSURL* url;

  // remote
  if([source hasPrefix:@"http"]) {
    url = [NSURL URLWithString:[source stringByRemovingPercentEncoding]];
  }
  // local
  else {
    url = [NSURL fileURLWithPath:[source stringByRemovingPercentEncoding]];
  }

  // 
  player = [[AVAudioPlayer alloc] initWithData:[[NSData alloc] initWithContentsOfURL:url] error:&error];
  player.delegate = self;
  player.enableRate = YES;

  [player prepareToPlay];
  [self _setDuration:player.duration];
	[self emitEvent:@"loadedmetadata"];
	[self emitEvent:@"loadeddata"];
	[self emitEvent:@"canplay"];
  
  if([self getBoolForKey:@"autoplay"] == YES) {
    [self play];
  }
}

- (void)audioPlayerDidFinishPlaying:(AVAudioPlayer *)player 
                       successfully:(BOOL)flag {
  [self emitEvent:@"ended"];
  if([self getBoolForKey:@"loop"] == YES) {
    [self play:0.0];
  }
}

- (void)audioPlayerDecodeErrorDidOccur:(AVAudioPlayer *)player
                                 error:(NSError *)error {
  [self _setError:error.localizedDescription];
  [self emitEvent:@"canplay"];
}

@end
