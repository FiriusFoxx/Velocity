/*
 * Copyright (C) 2018 Velocity Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.velocitypowered.proxy.plugin.loader;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.velocitypowered.api.plugin.PluginDescription;
import com.velocitypowered.api.plugin.meta.PluginDependency;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

public class VelocityPluginDescription implements PluginDescription {

  private final String id;
  private final @Nullable String name;
  private final String version;
  private final @Nullable String description;
  private final @Nullable String url;
  private final List<String> authors;
  private final Map<String, PluginDependency> dependencies;
  private final @Nullable Path source;

  /**
   * Creates a new plugin description.
   * @param id the ID
   * @param name the name of the plugin
   * @param version the plugin version
   * @param description a description of the plugin
   * @param url the website for the plugin
   * @param authors the authors of this plugin
   * @param dependencies the dependencies for this plugin
   * @param source the original source for the plugin
   */
  public VelocityPluginDescription(String id, @Nullable String name, String version,
      @Nullable String description, @Nullable String url,
      @Nullable List<String> authors, Collection<PluginDependency> dependencies,
      @Nullable Path source) {
    this.id = checkNotNull(id, "id");
    this.name = Strings.emptyToNull(name);
    this.version = checkNotNull(version, "version");
    this.description = Strings.emptyToNull(description);
    this.url = Strings.emptyToNull(url);
    this.authors = authors == null ? ImmutableList.of() : ImmutableList.copyOf(authors);
    this.dependencies = Maps.uniqueIndex(dependencies, d -> d == null ? null : d.id());
    this.source = source;
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public String name() {
    return name == null ? id : name;
  }

  @Override
  public String version() {
    return version;
  }

  @Override
  public @Nullable String description() {
    return description;
  }

  @Override
  public @Nullable String url() {
    return url;
  }

  @Override
  public List<String> authors() {
    return authors;
  }

  @Override
  public Collection<PluginDependency> dependencies() {
    return dependencies.values();
  }

  @Override
  public @Nullable PluginDependency getDependency(String id) {
    return dependencies.get(id);
  }

  @Override
  public @Nullable Path file() {
    return source;
  }

  @Override
  public String toString() {
    return "VelocityPluginDescription{"
        + "id='" + id + '\''
        + ", name='" + name + '\''
        + ", version='" + version + '\''
        + ", description='" + description + '\''
        + ", url='" + url + '\''
        + ", authors=" + authors
        + ", dependencies=" + dependencies
        + ", source=" + source
        + '}';
  }
}