package io.codebards.veganrealm.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThirdPartyFactory {
    private String env;

    @JsonProperty("env")
    public String getEnv() {
      return env;
    }

}
    


