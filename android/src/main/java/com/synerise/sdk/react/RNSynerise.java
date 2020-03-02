package com.synerise.sdk.react;

import android.app.Application;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.synerise.sdk.client.Client;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class RNSynerise extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private RNSyneriseInitializer initializer;
  private Application app;
  private static String RNSyneriseInitializationFailureEventListenerKey = "INITIALIZATION_FAILURE_LISTENER_KEY";
  private static String RNSyneriseInitializationSucessEventListenerKey = "INITIALIZATION_SUCCESS_LISTENER_KEY";
  private static String RN_SYNERISE_INIT_FAILURE = "RNInitFailed";
  private static String RN_SYNERISE_INIT_SUCESS = "RNInitSuccess";

  public RNSynerise(ReactApplicationContext reactContext) {
    super(reactContext);
     this.reactContext = reactContext;
     this.app = (Application) reactContext.getApplicationContext();
  }

  @Override
  public String getName() {
    return "RNSynerise";
  }

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(RNSyneriseInitializationSucessEventListenerKey, RN_SYNERISE_INIT_SUCESS);
    constants.put(RNSyneriseInitializationFailureEventListenerKey, RN_SYNERISE_INIT_FAILURE);
    return constants;
  }

  @ReactMethod
  public void createInitializer() {
    if (initializer == null) {
      initializer = new RNSyneriseInitializer();
    }
  }

  @ReactMethod
  public void withClientApiKey(@NonNull String clientApiKey) {
    initializer.clientApiKey = clientApiKey;
  }

  @ReactMethod
  public void withBaseUrl(String url) {
    initializer.baseUrl = url;
  }

  @ReactMethod
  public void withDebugModeEnabled(boolean debugModeEnabled) {
    initializer.isDebugModeEnabled = debugModeEnabled;
  }

  @ReactMethod
  public void withCrashHandlingEnabled(boolean crashHandlingEnabled) {
    initializer.isCrashHandlingEnabled = crashHandlingEnabled;
  }

  @ReactMethod
  public void initialize() {
    if (initializer != null) {
      initializer.initialize(app);
      WritableMap map = Arguments.createMap();
      sendEventToJs(RN_SYNERISE_INIT_SUCESS, map, reactContext);
    }
  }

  @ReactMethod
  public void initialized() {
    initializer.notifyModules();
  }

  @ReactMethod
  public void changeClientApiKey(String apiKey) {
    Client.changeApiKey(apiKey);
  }

  private void sendEventToJs(String eventName, @Nullable WritableMap data, ReactApplicationContext context) {
    context
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, data);
  }
}