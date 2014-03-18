package joulu.wsdef;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.iwant.api.EclipseSettings;
import net.sf.iwant.api.EmmaTargetsOfJavaModules;
import net.sf.iwant.api.FromRepository;
import net.sf.iwant.api.IwantWorkspace;
import net.sf.iwant.api.SideEffectDefinitionContext;
import net.sf.iwant.api.TestedIwantDependencies;
import net.sf.iwant.api.javamodules.JavaBinModule;
import net.sf.iwant.api.javamodules.JavaSrcModule;
import net.sf.iwant.api.javamodules.JavaSrcModule.IwantSrcModuleSpex;
import net.sf.iwant.api.model.SideEffect;
import net.sf.iwant.api.model.StringFilter;
import net.sf.iwant.api.model.Target;

public class JouluWorkspace implements IwantWorkspace {

	private static StringFilter testNameFilter = new StringFilter() {
		@Override
		public boolean matches(String candidate) {
			return candidate.matches(".*Test$")
					&& !candidate.matches(".*Abstract[^.]*Test$");
		}

		@Override
		public String toString() {
			return "JouluTestNameFilter";
		}
	};

	private final JavaBinModule hamcrestCore = JavaBinModule.providing(
			FromRepository.repo1MavenOrg().group("org/hamcrest")
					.name("hamcrest-core").version("1.3")).end();
	private final JavaBinModule junit = JavaBinModule
			.providing(
					FromRepository.repo1MavenOrg().group("junit").name("junit")
							.version("4.11")).runtimeDeps(hamcrestCore).end();

	private final JavaSrcModule stronglyTyped = srcModule("strongly-typed")
			.noMainResources().noTestResources().mainDeps()
			.testDeps(hamcrestCore, junit).end();

	private final JavaSrcModule optional = srcModule("optional")
			.noMainResources().noTestResources().mainDeps(stronglyTyped)
			.testDeps(hamcrestCore, junit).end();

	private final JavaSrcModule equivalence = srcModule("equivalence")
			.noMainResources().noTestJava().noTestResources().end();

	private final JavaSrcModule collections = srcModule("collections")
			.noMainResources().noTestResources()
			.mainDeps(equivalence, optional).testDeps(hamcrestCore, junit)
			.end();

	private final JavaSrcModule byteConsumer = srcModule("byte-consumer")
			.noMainResources().noTestJava().noTestResources().end();

	private final JavaSrcModule byteProducer = srcModule("byte-producer")
			.noMainResources().noTestJava().noTestResources().end();

	private final JavaSrcModule byteProducers = srcModule("byte-producers")
			.noMainResources().noTestResources().mainDeps(byteProducer)
			.testDeps(hamcrestCore, junit).end();

	private final JavaSrcModule unsignedByte = srcModule("unsigned-byte")
			.noMainResources().noTestResources().mainDeps()
			.testDeps(hamcrestCore, junit).end();

	private static IwantSrcModuleSpex srcModule(String name) {
		return JavaSrcModule.with().name("joulu-" + name)
				.locationUnderWsRoot(name).mavenLayout()
				.testedBy(testNameFilter);
	}

	@Override
	public List<? extends Target> targets() {
		return Arrays.asList(emmaReport());
	}

	@Override
	public List<? extends SideEffect> sideEffects(
			SideEffectDefinitionContext ctx) {
		return Arrays.asList(EclipseSettings.with().name("eclipse-settings")
				.modules(ctx.wsdefdefJavaModule(), ctx.wsdefJavaModule())
				.modules(allSrcModules()).end());
	}

	private Set<JavaSrcModule> allSrcModules() {
		return new TreeSet<JavaSrcModule>(Arrays.asList(byteConsumer,
				byteProducer, byteProducers, collections, equivalence,
				optional, stronglyTyped, unsignedByte));
	}

	private Target emmaReport() {
		EmmaTargetsOfJavaModules emmaTargets = EmmaTargetsOfJavaModules
				.with()
				.antJars(TestedIwantDependencies.antJar(),
						TestedIwantDependencies.antLauncherJar())
				.emma(TestedIwantDependencies.emma()).modules(allSrcModules())
				.end();
		return emmaTargets.emmaReport();
	}

}
