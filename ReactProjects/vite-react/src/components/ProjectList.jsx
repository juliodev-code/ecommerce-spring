function ProjectList(){
    const projects = [
    {title:"Project One", description:"A web application using React and Node JS", link: "#"},
    {title:"Project two", description:"An ecommerce platform using Java", link: "#"}
  ]
  const profession = "Full Stack Developer"
  const name = "Julio Jaramillo"
    return(
        <section id='projects' className='projects-section'>
          <h2>Projects</h2>
          <div className='projects-list'>
            {projects.map((project, index)=> <div key={index} className='project-item'>
              <h3>{project.title}</h3>
              <h3>{project.description}</h3>
              <a href={project.link} target='_blank' rel='noopener noreferrer'>View project</a>
            </div>)}
          </div>

          <p>Hello! I am {name}, a passionate {profession}. I love building web applications that solves real user problems </p>
        </section>
    )
}

export default ProjectList