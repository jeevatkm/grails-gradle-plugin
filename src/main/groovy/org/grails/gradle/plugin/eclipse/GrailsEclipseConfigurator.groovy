/*
 * Copyright 2014 the original author or authors.
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

package org.grails.gradle.plugin.eclipse

import org.gradle.api.Project
import org.gradle.plugins.ide.eclipse.EclipsePlugin
import org.gradle.plugins.ide.eclipse.model.EclipseModel

/**
 * Configure the Eclipse IDE integration for the project.
 *
 * Created by Jeevanandam M. (jeeva@myjeeva.com) on 7/4/14.
 */
class GrailsEclipseConfigurator {
    final GRAILS_PLUGIN_DIR_LINK_NAME = '.link_to_grails_plugins'
    final GRADLE_GRAILS_PLUGIN_RELATIVE_DIR = 'buildPlugins'

    /**
     * Registering Eclipse IDE project configuration
     *
     * @param project gradle project instance of {@code Project}
     */
    void configure(Project project) {
        project.plugins.withType(EclipsePlugin) {
            project.eclipse {
                prepareEclipseProject(project, model)
            }
        }
    }

    void prepareEclipseProject(Project project, EclipseModel model) {
        model.project {
            buildCommand 'org.eclipse.wst.common.project.facet.core.builder'
            buildCommand 'org.eclipse.jdt.core.javabuilder'

            natures = ['org.grails.ide.eclipse.core.nature',
                       'org.eclipse.jdt.groovy.core.groovyNature',
                       'org.eclipse.jdt.core.javanature',
                       'org.eclipse.wst.common.project.facet.core.nature']

            linkedResource name: GRAILS_PLUGIN_DIR_LINK_NAME,
                type: '2',
                location: "${project.projectDir.absolutePath}${File.separator}${GRADLE_GRAILS_PLUGIN_RELATIVE_DIR}"
        }
    }
}
