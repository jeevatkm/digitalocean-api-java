/**
 * Copyright (c) Jeevanandam M. (https://github.com/jeevatkm)
 *
 * <p>digitalocean-api-client source code and usage is governed by a MIT style license that can be
 * found in the LICENSE file
 */
package com.myjeeva.digitalocean.pojo;

import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents list of volumes.
 *
 * @author Eugene Strokin (https://github.com/strokine)
 * @since v2.7
 */
public class Volumes extends Base {

  private static final long serialVersionUID = 1739002259344347687L;

  private List<Volume> volumes;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the volumes */
  public List<Volume> getVolumes() {
    return volumes;
  }

  /** @param volumes the volumes to set */
  public void setVolumes(List<Volume> volumes) {
    this.volumes = volumes;
  }
}
