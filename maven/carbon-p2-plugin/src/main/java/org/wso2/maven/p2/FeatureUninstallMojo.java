/*
 * Copyright 2015 The Apache Software Foundation.
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

package org.wso2.maven.p2;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.eclipse.tycho.p2.facade.internal.P2ApplicationLauncher;
import org.wso2.maven.p2.generate.utils.P2Constants;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Write environment information for the current build to file.
 *
 * @goal p2-feature-uninstall
 * @phase package
 */
public class FeatureUninstallMojo extends AbstractMojo {


    /**
     * Destination to which the features should be installed
     *
     * @parameter
     * @required
     */
    private String destination;

    /**
     * target profile
     *
     * @parameter
     * @required
     */
    private String profile;

    /**
     * URL of the Metadata Repository
     *
     * @parameter
     */
    private URL metadataRepository;

    /**
     * URL of the Artifact Repository
     *
     * @parameter
     */
    private URL artifactRepository;
    /**
     * List of features
     *
     * @parameter
     * @required
     */
    private ArrayList features;

    /**
     * @parameter default-value="${project}"
     */
    private MavenProject project;

    /** @component */
    private P2ApplicationLauncher launcher;

    /**
     * Kill the forked test process after a certain number of seconds. If set to 0, wait forever for
     * the process, never timing out.
     *
     * @parameter expression="${p2.timeout}"
     */
    private int forkedProcessTimeoutInSeconds;


    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            //Check if the profile parameter is null if that is so assign the default profile
            if (profile == null){
                profile = P2Constants.DEFAULT_PROFILE_ID;
            }

            this.getLog().info("Running Equinox P2 Director Application");
            uninstallFeatures(getIUsToUninstall());

        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private String getIUsToUninstall() throws MojoExecutionException {
        String uninstallUIs = "";
        for (Object featureObj : features) {
            Feature f;
            if (featureObj instanceof Feature) {
                f = (Feature) featureObj;
            } else if (featureObj instanceof String) {
                f = Feature.getFeature(featureObj.toString());
            } else
                f = (Feature) featureObj;
            uninstallUIs = uninstallUIs + f.getId().trim() + "/" + f.getVersion().trim() + ",";
        }

        if (uninstallUIs.length() == 0) {
            uninstallUIs = uninstallUIs.substring(0, uninstallUIs.length() - 1);
        }
        return uninstallUIs;
    }

    private void uninstallFeatures(String uninstallUIs) throws IOException, MojoFailureException {
        P2ApplicationLauncher launcher = this.launcher;

        launcher.setWorkingDirectory(project.getBasedir());
        launcher.setApplicationName(getPublisherApplication());

        addArguments(launcher,uninstallUIs);

        int result = launcher.execute(forkedProcessTimeoutInSeconds);
        if (result != 0) {
            throw new MojoFailureException("P2 publisher return code was " + result);
        }
    }


    private void addArguments(P2ApplicationLauncher launcher, String uninstallUIs) throws IOException {
        launcher.addArguments(
                // a comma separated list of metadata repository URLs where the software to be installed can be found
                "-metadataRepository", metadataRepository.toExternalForm(),
                // a comma separated list of artifact repository URLs where the software artifacts can be found
                "-artifactRepository", artifactRepository.toExternalForm(),
                // a comma separated list of <key>=<value> pair.
                // The property org.eclipse.update.install.features=true will cause the update manager features to be
                // installed
                "-profileProperties", "org.eclipse.update.install.features=false",
                // a comma separated list of IUs to uninstall. Each entry in the list is in the form
                // <id> [ '/' <version> ]
                "-uninstallIU", uninstallUIs,
                // the location of where the plug-ins and features will be stored. This value is only taken into account
                // when a new profile is created. For an application where all the bundles are located into the
                // plugins/ folder of the destination, set it to <destination>
                //"-bundlepool", destination,
                // use a shared location for the install. The path defaults to ${user.home}/.p2
                "-shared" , destination + File.separator + "p2",
                //the path of a folder in which the targeted product is located
                "-destination", destination + File.separator + profile,
                // the profile id containing the description of the targeted product
                "-profile", profile.toString()
        );
    }

    private String getPublisherApplication() {
        return "org.eclipse.equinox.p2.director";
    }

}
