package project

object ProjectRepository {
    private val listOpenProject: MutableList<Project> = mutableListOf()

    fun createProject(name: String): Project {
        val newProject = Project(name)
        listOpenProject.add(newProject)
        return newProject
    }

}