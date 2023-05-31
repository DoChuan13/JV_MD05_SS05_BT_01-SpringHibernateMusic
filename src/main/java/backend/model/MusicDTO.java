package backend.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicDTO {
    private Long id;
    private String name, singer, category;
    private MultipartFile file;

    public MusicDTO() {
    }

    public MusicDTO(Long id, String name, String singer, String category, MultipartFile file) {
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "MusicDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", category='" + category + '\'' +
                ", file=" + file +
                '}';
    }
}
