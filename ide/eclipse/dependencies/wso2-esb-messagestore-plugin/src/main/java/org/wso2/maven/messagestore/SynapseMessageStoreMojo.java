/*
 * Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.wso2.maven.messagestore;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.util.FileUtils;

/**
 * Prepare an artifact to be installed in the local Maven repository
 * 
 * @goal package-messagestore
 */
public class SynapseMessageStoreMojo extends AbstractMojo{

	/**
	 * @parameter default-value="${project}"
	 */
	private MavenProject project;

	/**
	 * Maven ProjectHelper.
	 * 
	 * @component
	 */
	private MavenProjectHelper projectHelper;

	/**
	 * The path of the existing artifact
	 * 
	 * @parameter expression="${deploy-file.artifact}"
	 * @required
	 */
	private File artifact;

	/**
	 * The resulting extension of the file
	 * 
	 * @parameter expression="${deploy-file.extension}
	 */
	private String extension;

	/**
	 * The resulting extension of the file
	 * 
	 * @parameter expression="${deploy-file.fileName}
	 */
	private String fileName;

	/**
	 * If the file should be archived
	 * 
	 * @parameter expression="${deploy-file.enableArchive}" default-value=false
	 */
	private boolean enableArchive;

	private File destFolder;

	public void execute() throws MojoExecutionException, MojoFailureException {

		destFolder = new File(project.getBuild().getDirectory());
		String newPath = null;

		if (fileName != null) { // if the user gave a name for the file
			newPath = destFolder.getAbsolutePath() + File.separator + fileName;
		} else {
			if (extension != null) { // if the user provided the extension
				String fileNameWithoutExtension = (artifact.getName()
						.split("\\."))[0];
				newPath = destFolder.getAbsolutePath() + File.separator
						+ fileNameWithoutExtension + "." + extension;
			} else {
				newPath = destFolder.getAbsolutePath() + File.separator
						+ artifact.getName();
			}
		}

		File result = new File(newPath);

		if (!artifact.exists()) {
			throw new MojoExecutionException(artifact.getAbsolutePath() + " doesn't exist.");
		}

		try {
			FileUtils.copyFile(artifact, result);

		} catch (IOException e) {
			throw new MojoExecutionException("Error when copying " + artifact.getName() + " to " +
			                                 result.getName() + "\n" + e.getMessage());
		}

		if (result != null && result.exists()) {
			project.getArtifact().setFile(result);
			//projectHelper.attachArtifact(project, extension, null, result);
		} else {
			throw new MojoExecutionException(result
					+ " is null or doesn't exist");
		}

		if (enableArchive) {
			// TODO make the zip file
		}
	}

}
