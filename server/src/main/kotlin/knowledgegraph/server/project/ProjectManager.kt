package knowledgegraph.server.project

object ProjectManager {
    private val openProject: MutableMap<String, Project> = mutableMapOf()

    fun createProject(name: String): Project {
        val newProject = Project(name)
        openProject[name] = newProject
        return newProject
    }

    fun getOrCreateProject(name: String): Project = openProject[name] ?: createProject(name)

}