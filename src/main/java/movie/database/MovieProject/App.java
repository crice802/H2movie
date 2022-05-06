package movie.database.MovieProject;

import org.hibernate.Session;
/**
 * Hello world!
 *
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.ActorRepository;
import model.Actors;
import model.Genre;
import model.GenreRepository;
import model.Movie;
import model.MovieRepository;

public class App {
	public static void main(String[] args) {
		final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Movie.class).addAnnotatedClass(Actors.class).addAnnotatedClass(Genre.class)
				.buildSessionFactory();

		Genre genre = new Genre();
		genre.setName("Horror");
		final GenreRepository genreRepo = new GenreRepository(sessionFactory.createEntityManager());
		genreRepo.save(genre);
		System.out.println(genreRepo.findAll().size());

		final Session session = sessionFactory.openSession();
		final MovieRepository movieRepo = new MovieRepository(session.getSession());
		final ActorRepository actorRepo = new ActorRepository(session.getSession());
		Movie movie = new Movie();
		movie.setTitle("Nightmare on Elm St.");
		movie.setYearOfRelease(1984);
		movieRepo.save(movie);
		Actors actor = new Actors();
		actor.setLastName("England");
		actor.setName("Robert");
		actor.setYearOfBirth(1964);
		actorRepo.save(actor);
	}
}
