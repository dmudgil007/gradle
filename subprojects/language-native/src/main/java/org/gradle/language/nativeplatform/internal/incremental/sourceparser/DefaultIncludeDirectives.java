/*
 * Copyright 2013 the original author or authors.
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
package org.gradle.language.nativeplatform.internal.incremental.sourceparser;

import com.google.common.collect.ImmutableList;
import org.gradle.api.specs.Spec;
import org.gradle.language.nativeplatform.internal.Include;
import org.gradle.language.nativeplatform.internal.IncludeDirectives;
import org.gradle.language.nativeplatform.internal.IncludeType;
import org.gradle.language.nativeplatform.internal.Macro;
import org.gradle.util.CollectionUtils;

import java.util.List;

public class DefaultIncludeDirectives implements IncludeDirectives {
    private final ImmutableList<Include> allIncludes;
    private final ImmutableList<Macro> macros;

    public DefaultIncludeDirectives(ImmutableList<Include> allIncludes, ImmutableList<Macro> macros) {
        this.allIncludes = allIncludes;
        this.macros = macros;
    }

    @Override
    public List<Include> getQuotedIncludes() {
        return CollectionUtils.filter(allIncludes, new Spec<Include>() {
            @Override
            public boolean isSatisfiedBy(Include element) {
                return element.getType() == IncludeType.QUOTED;
            }
        });
    }

    @Override
    public List<Include> getSystemIncludes() {
        return CollectionUtils.filter(allIncludes, new Spec<Include>() {
            @Override
            public boolean isSatisfiedBy(Include element) {
                return element.getType() == IncludeType.SYSTEM;
            }
        });
    }

    @Override
    public List<Include> getMacroIncludes() {
        return CollectionUtils.filter(allIncludes, new Spec<Include>() {
            @Override
            public boolean isSatisfiedBy(Include element) {
                return element.getType() == IncludeType.MACRO;
            }
        });
    }

    @Override
    public List<Include> getIncludesAndImports() {
        return allIncludes;
    }

    @Override
    public List<Include> getIncludesOnly() {
        return CollectionUtils.filter(allIncludes, new Spec<Include>() {
            @Override
            public boolean isSatisfiedBy(Include element) {
                return !element.isImport();
            }
        });
    }

    @Override
    public List<Macro> getMacros() {
        return macros;
    }

    @Override
    public IncludeDirectives discardImports() {
        return new DefaultIncludeDirectives(ImmutableList.copyOf(getIncludesOnly()), macros);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultIncludeDirectives that = (DefaultIncludeDirectives) o;

        if (!allIncludes.equals(that.allIncludes)) {
            return false;
        }
        if (!macros.equals(that.macros)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return allIncludes.hashCode() ^ macros.hashCode();
    }
}
