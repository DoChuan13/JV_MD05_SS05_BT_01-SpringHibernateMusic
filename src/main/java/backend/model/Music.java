package backend.model;

import javax.persistence.*;

@Entity
@Table(name = "musics")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, singer, category, file;

    public Music() {
    }

    public Music(Long id, String name, String singer, String category, String file) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", category='" + category + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
