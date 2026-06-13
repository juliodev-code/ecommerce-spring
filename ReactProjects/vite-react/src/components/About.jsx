function About() {
    const name = "Julio Jaramillo"
    const profession = "Full Stack Developer"
    return (
    <section id="about" className="about-section">
        <h2>About me</h2>
        <p>
        Hello! I am {name}, a passionate {profession}. I love building web
        applications that solves real user problems{" "}
        </p>
    </section>
    );
}

export default About;