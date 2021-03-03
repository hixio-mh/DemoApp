import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

setupModule(Module.Main)

applyPlugins(Plugins.protobuf) {
  protobuf {
    protoc {
      artifact = Libs.protoc
    }
    generateProtoTasks {
      all().forEach {
        it.builtins.create("java") {
          option("lite")
        }
      }
    }
  }
}

dependencies {
  implementations(
    project(Module.Adapter.moduleName),
    project(Module.Widget.moduleName),
    Libs.coordinatorLayout,
    Libs.protobufLite
  )
}
