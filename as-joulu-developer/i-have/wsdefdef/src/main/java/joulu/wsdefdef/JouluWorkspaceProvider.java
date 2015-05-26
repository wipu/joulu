package joulu.wsdefdef;

import net.sf.iwant.api.javamodules.JavaSrcModule;
import net.sf.iwant.api.wsdef.IwantWorkspaceProvider;
import net.sf.iwant.api.wsdef.WorkspaceDefinitionContext;

public class JouluWorkspaceProvider implements IwantWorkspaceProvider {

	@Override
	public JavaSrcModule workspaceModule(WorkspaceDefinitionContext ctx) {
		return JavaSrcModule.with().name("joulu-workspace")
				.locationUnderWsRoot("as-joulu-developer/i-have/wsdef")
				.mainJava("src/main/java").mainDeps(ctx.iwantApiModules())
				.mainDeps(ctx.iwantPlugin().jacoco().withDependencies())
				.mainDeps(ctx.wsdefdefModule()).end();
	}

	@Override
	public String workspaceClassname() {
		return "joulu.wsdef.JouluWorkspace";
	}

}
