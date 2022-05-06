package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "actors", callSuper = true)
@ToString(exclude = "actors")
public class Movie extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "year_of_release")
	private int yearOfRelease;

	@ManyToMany(mappedBy = "movies")
	private List<Actors> actors = new ArrayList<>();

	/**
	 * @return the actors
	 */
	public List<Actors> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<Actors> actors) {
		this.actors = actors;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the yearOfRelease
	 */
	public int getYearOfRelease() {
		return yearOfRelease;
	}

	/**
	 * @param yearOfRelease the yearOfRelease to set
	 */
	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	@ManyToOne
	private Genre genre;
}
