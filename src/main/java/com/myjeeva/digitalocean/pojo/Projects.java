package com.myjeeva.digitalocean.pojo;

import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents list of projects.
 *
 * @author Thomas Crombez (thomasc@trikthom.com)
 */
public class Projects extends Base {

  private static final long serialVersionUID = -7659167641617957056L;

  private List<Project> projects;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the projects */
  public List<Project> getProjects() {
    return projects;
  }

  /** @param projects the projects to set */
  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
}
