package joulu.wsdef;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.iwant.api.EclipseSettings;
import net.sf.iwant.api.FromRepository;
import net.sf.iwant.api.IwantWorkspace;
import net.sf.iwant.api.SideEffectDefinitionContext;
import net.sf.iwant.api.javamodules.JavaBinModule;
import net.sf.iwant.api.javamodules.JavaSrcModule;
import net.sf.iwant.api.model.HelloTarget;
import net.sf.iwant.api.model.SideEffect;
import net.sf.iwant.api.model.Target;

public class JouluWorkspace implements IwantWorkspace {

	private final JavaBinModule hamcrestCore = JavaBinModule.providing(
			FromRepository.repo1MavenOrg().group("org/hamcrest")
					.name("hamcrest-core").version("1.3")).end();
	private final JavaBinModule junit = JavaBinModule
			.providing(
					FromRepository.repo1MavenOrg().group("junit").name("junit")
							.version("4.11")).runtimeDeps(hamcrestCore).end();

	private final JavaSrcModule stronglyTyped = JavaSrcModule.with()
			.name("joulu-strongly-typed").locationUnderWsRoot("strongly-typed")
			.mavenLayout().noMainResources().noTestResources().mainDeps()
			.testDeps(hamcrestCore, junit).end();
    
	private final JavaSrcModule optional = JavaSrcModule.with()
			.name("joulu-optional").locationUnderWsRoot("optional")
			.mavenLayout().noMainResources().noTestResources().mainDeps(stronglyTyped)
			.testDeps(hamcrestCore, junit).end();
	
	@Override
	public List<? extends Target> targets() {
		return Arrays.asList(new HelloTarget("hello", "hello from iwant"));
	}

	@Override
	public List<? extends SideEffect> sideEffects(
			SideEffectDefinitionContext ctx) {
		return Arrays.asList(EclipseSettings.with().name("eclipse-settings")
				.modules(ctx.wsdefdefJavaModule(), ctx.wsdefJavaModule())
				.modules(allSrcModules()).end());
	}

	private Set<JavaSrcModule> allSrcModules() {
		return new TreeSet<JavaSrcModule>(Arrays.asList(stronglyTyped, optional));
	}

}
