/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.plugins.ide.internal.tooling.eclipse;

import java.io.Serializable;
import java.util.List;

public class DefaultEclipseProjectDependency extends DefaultEclipseDependency implements Serializable {
    private final String path;

    private DefaultEclipseProject targetProject;

    public DefaultEclipseProjectDependency(String path, boolean exported, List<DefaultClasspathAttribute> attributes, List<DefaultAccessRule> accessRules) {
        super(exported, attributes, accessRules);
        this.targetProject = null;
        this.path = path;
    }

    public DefaultEclipseProject getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(DefaultEclipseProject targetProject) {
        this.targetProject = targetProject;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "project dependency " + path;
    }
}
