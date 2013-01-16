package com.inventage.tools.versiontiger.internal.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.inventage.tools.versiontiger.MavenVersion;
import com.inventage.tools.versiontiger.OsgiVersion;
import com.inventage.tools.versiontiger.ProjectUniverse;
import com.inventage.tools.versiontiger.Versioning;
import com.inventage.tools.versiontiger.VersioningLogger;

public class VersioningImpl implements Versioning {

	@Override
	public ProjectUniverse createUniverse(String id, VersioningLogger logger) {
		return createUniverse(id, null);
	}

	@Override
	public ProjectUniverse createUniverse(String id, String name, VersioningLogger logger) {
		return createUniverse(id, name, System.getProperty("user.dir"), logger);
	}

	@Override
	public ProjectUniverse createUniverse(String id, String name, String rootPath, VersioningLogger logger) {
		ProjectFactory projectFactory = new ProjectFactory(rootPath);

		return new ProjectUniverseImpl(id, name, projectFactory, logger);
	}

	@Override
	public MavenVersion createMavenVersion(String mavenVersion) {
		return new VersionFactory().createMavenVersion(mavenVersion);
	}

	@Override
	public OsgiVersion createOsgiVersion(MavenVersion mavenVersion) {
		return new VersionFactory().createOsgiVersion(mavenVersion);
	}

	@Override
	public void executeCommandScript(BufferedReader commandScript, String rootPath, VersioningLogger logger) throws IOException {
		new CommandExecuter(this, rootPath, logger).executeCommands(commandScript, logger);
	}

}