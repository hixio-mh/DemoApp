enum class Module(val tag: String, val runAlone: Boolean = false) {
  //---------------------base-------------------------------//
  Base("base"),
  Common("common"),

  //---------------------biz-------------------------------//
  Login("login"),
  Main("main"),
  Map("map"),
  Web("web"),

  //---------------------func-------------------------------//
  Adapter("adapter"),
  Widget("widget"),

  //---------------------app-------------------------------//
  App("app"),
  Test("test")
}

enum class Flavor(val flavor: String) {
  Daily("daily"),
  Online("online")
}

enum class BuildConfigField(val tag: String) {
  VersionCode("VERSION_CODE"),
  VersionName("VERSION_NAME"),
  CdnPrefix("CDN_PREFIX"),
  ApiHost("API_HOST"),
  DoraemonKitKey("DORAEMON_KIT_KEY")
}

enum class GradleTask(val task: String) {
  Clean("clean"),
  DependencyUpdate("dependencyUpdates"),
  LintKotlin("lintKotlin"),
  FormatKotlin("formatKotlin")
}