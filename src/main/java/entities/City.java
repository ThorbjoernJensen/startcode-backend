package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    @OneToMany (mappedBy = "city")
    Set<Bookmark> bookmarks = new LinkedHashSet<>();

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void addBookmark(Bookmark bookmark) {
        this.bookmarks.add(bookmark);
        bookmark.setCity(this);
    }

    public String getCityName() {
        return cityName;
    }
}