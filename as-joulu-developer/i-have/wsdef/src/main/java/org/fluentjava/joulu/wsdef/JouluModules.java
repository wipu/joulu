package org.fluentjava.joulu.wsdef;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.fluentjava.iwant.api.javamodules.JavaBinModule;
import org.fluentjava.iwant.api.javamodules.JavaModule;
import org.fluentjava.iwant.api.javamodules.JavaSrcModule;
import org.fluentjava.iwant.api.javamodules.JavaSrcModule.IwantSrcModuleSpex;
import org.fluentjava.iwant.api.wsdef.WorkspaceContext;
import org.fluentjava.iwant.core.javamodules.JavaModules;

public class JouluModules extends JavaModules {

	private static final JavaBinModule commonsCodec = binModule("commons-codec",
			"commons-codec", "1.9");

	private final Set<JavaModule> junit5runnerMods;

	public JouluModules(WorkspaceContext ctx) {
		junit5runnerMods = ctx.iwantPlugin().junit5runner().withDependencies();
		depRoots();
	}

	private SortedSet<JavaSrcModule> depRoots() {
		return new TreeSet<>(List.of(collections(), byteConsumer(),
				byteProducers(), midievents()));
	}

	private IwantSrcModuleSpex jouluModule(String name) {
		return srcModule("joulu-" + name).locationUnderWsRoot(name)
				.testDeps(junit5runnerMods);
	}

	private JavaSrcModule stronglyTyped() {
		return lazy(() -> jouluModule("strongly-typed").noMainResources()
				.noTestResources().mainDeps().testDeps().end());
	}

	private JavaSrcModule optional() {
		return lazy(() -> jouluModule("optional").noMainResources()
				.noTestResources().mainDeps(stronglyTyped()).testDeps().end());
	}

	private JavaSrcModule equivalence() {
		return lazy(() -> jouluModule("equivalence").noMainResources()
				.noTestJava().noTestResources().end());
	}

	private JavaSrcModule collections() {
		return lazy(() -> jouluModule("collections").noMainResources()
				.noTestResources().mainDeps(equivalence(), optional())
				.testDeps().end());
	}

	private JavaSrcModule byteConsumer() {
		return lazy(() -> jouluModule("byte-consumer").noMainResources()
				.noTestJava().noTestResources().end());
	}

	private JavaSrcModule byteProducer() {
		return lazy(() -> jouluModule("byte-producer").noMainResources()
				.noTestJava().noTestResources().end());
	}

	private JavaSrcModule byteProducers() {
		return lazy(() -> jouluModule("byte-producers").noMainResources()
				.noTestResources().mainDeps(byteProducer()).testDeps().end());
	}

	private JavaSrcModule unsignedByte() {
		return lazy(() -> jouluModule("unsigned-byte").noMainResources()
				.noTestResources().mainDeps().testDeps().end());
	}

	private JavaSrcModule midievents() {
		return lazy(() -> jouluModule("midievents").noMainResources()
				.mainDeps(commonsCodec, unsignedByte()).testDeps().end());
	}

}
