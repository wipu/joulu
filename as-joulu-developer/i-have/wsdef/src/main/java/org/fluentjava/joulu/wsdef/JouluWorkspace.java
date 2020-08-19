package org.fluentjava.joulu.wsdef;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.fluentjava.iwant.api.javamodules.JavaBinModule;
import org.fluentjava.iwant.api.javamodules.JavaSrcModule;
import org.fluentjava.iwant.api.javamodules.JavaSrcModule.IwantSrcModuleSpex;
import org.fluentjava.iwant.api.model.SideEffect;
import org.fluentjava.iwant.api.model.StringFilter;
import org.fluentjava.iwant.api.model.Target;
import org.fluentjava.iwant.api.wsdef.SideEffectDefinitionContext;
import org.fluentjava.iwant.api.wsdef.TargetDefinitionContext;
import org.fluentjava.iwant.api.wsdef.Workspace;
import org.fluentjava.iwant.core.download.FromRepository;
import org.fluentjava.iwant.core.download.TestedIwantDependencies;
import org.fluentjava.iwant.eclipsesettings.EclipseSettings;
import org.fluentjava.iwant.plugin.jacoco.JacocoDistribution;
import org.fluentjava.iwant.plugin.jacoco.JacocoTargetsOfJavaModules;

public class JouluWorkspace implements Workspace {

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

	private final JavaBinModule commonsCodec = JavaBinModule
			.providing(FromRepository.repo1MavenOrg().group("commons-codec")
					.name("commons-codec").version("1.9").jar())
			.end();
	private final JavaBinModule hamcrestCore = JavaBinModule
			.providing(FromRepository.repo1MavenOrg().group("org/hamcrest")
					.name("hamcrest-core").version("1.3").jar())
			.end();
	private final JavaBinModule junit = JavaBinModule
			.providing(FromRepository.repo1MavenOrg().group("junit")
					.name("junit").version("4.11").jar())
			.runtimeDeps(hamcrestCore).end();

	private final JavaSrcModule stronglyTyped = srcModule("strongly-typed")
			.noMainResources().noTestResources().mainDeps()
			.testDeps(hamcrestCore, junit).end();

	private final JavaSrcModule optional = srcModule("optional")
			.noMainResources().noTestResources().mainDeps(stronglyTyped)
			.testDeps(hamcrestCore, junit).end();

	private final JavaSrcModule equivalence = srcModule("equivalence")
			.noMainResources().noTestJava().noTestResources().end();

	private final JavaSrcModule collections = srcModule("collections")
			.noMainResources().noTestResources().mainDeps(equivalence, optional)
			.testDeps(hamcrestCore, junit).end();

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

	private final JavaSrcModule midievents = srcModule("midievents")
			.noMainResources().mainDeps(commonsCodec, unsignedByte)
			.testDeps(junit).end();

	private static IwantSrcModuleSpex srcModule(String name) {
		return JavaSrcModule.with().name("joulu-" + name)
				.locationUnderWsRoot(name).mavenLayout()
				.testedBy(testNameFilter);
	}

	@Override
	public List<? extends Target> targets(TargetDefinitionContext ctx) {
		return Arrays.asList(coverageReport());
	}

	@Override
	public List<? extends SideEffect> sideEffects(
			SideEffectDefinitionContext ctx) {
		return Arrays.asList(EclipseSettings.with().name("eclipse-settings")
				.modules(ctx.wsdefdefJavaModule(), ctx.wsdefJavaModule())
				.modules(allSrcModules()).end());
	}

	private Set<JavaSrcModule> allSrcModules() {
		return new TreeSet<>(Arrays.asList(byteConsumer, byteProducer,
				byteProducers, collections, equivalence, optional,
				stronglyTyped, unsignedByte, midievents));
	}

	private Target coverageReport() {
		return JacocoTargetsOfJavaModules.with().jacoco(jacoco())
				.antJars(TestedIwantDependencies.antJar(),
						TestedIwantDependencies.antLauncherJar())
				.modules(allSrcModules()).end().jacocoReport("coverage-report");

	}

	private static JacocoDistribution jacoco() {
		return JacocoDistribution.newestTestedVersion();
	}

}
