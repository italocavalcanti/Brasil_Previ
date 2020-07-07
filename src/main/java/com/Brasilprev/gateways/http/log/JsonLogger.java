package com.Brasilprev.gateways.http.log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JsonLogger {

  private final Gson gson;

  public JsonLogger() {
    this.gson =
        new GsonBuilder()
            .setExclusionStrategies(
                new ExclusionStrategy() {
                  @Override
                  public boolean shouldSkipField(FieldAttributes f) {
                    return f.getAnnotation(Skip.class) != null;
                  }

                  @Override
                  public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                  }
                })
            .serializeNulls()
            .create();
  }

  public String toJson(Object object) {
    return gson.toJson(object);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  public @interface Skip {}
}
