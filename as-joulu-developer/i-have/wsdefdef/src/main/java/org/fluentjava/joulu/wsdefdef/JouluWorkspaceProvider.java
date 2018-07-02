package org.fluentjava.joulu.wsdefdef;

import net.sf.iwant.api.javamodules.JavaSrcModule;
import net.sf.iwant.api.wsdef.WorkspaceModuleContext;
import net.sf.iwant.api.wsdef.WorkspaceModuleProvider;

public class JouluWorkspaceProvider implements WorkspaceModuleProvider {

	@Override
	public JavaSrcModule workspaceModule(WorkspaceModuleContext ctx) {
		return JavaSrcModule.with().name("joulu-workspace")
				.locationUnderWsRoot("as-joulu-developer/i-have/wsdef")
				.mainJava("src/main/java").mainDeps(ctx.iwantApiModules())
				.mainDeps(ctx.iwantPlugin().jacoco().withDependencies())
				.mainDeps(ctx.wsdefdefModule()).end();
	}

	@Override
	public String workspaceFactoryClassname() {
		return "org.fluentjava.joulu.wsdef.JouluWorkspaceFactory";
	}

}
