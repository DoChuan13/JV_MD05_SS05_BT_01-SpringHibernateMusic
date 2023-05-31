package backend.controller;

import backend.model.Music;
import backend.model.MusicDTO;
import backend.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/music"})
public class MusicController {
    @Autowired
    IMusicService musicService;
    @Value("${file-upload}")
    String fileUpload;

    @GetMapping
    public ModelAndView showListMusic() {
        ModelAndView view = new ModelAndView("music/index");
        List<Music> musicList = musicService.findAll();
        view.addObject("musicList", musicList);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView view = new ModelAndView("music/create");
        MusicDTO music = new MusicDTO();
        view.addObject("music", music);
        return view;
    }

    @PostMapping("/create")
    public String actionCreate(MusicDTO musicDTO, RedirectAttributes redirectAttributes) {
        Music music = new Music();
        music.setId(musicDTO.getId());
        music.setName(musicDTO.getName());
        music.setSinger(musicDTO.getSinger());
        music.setCategory(musicDTO.getCategory());
        MultipartFile multipartFile = musicDTO.getFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
                music.setFile(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        musicService.save(music);
        redirectAttributes.addFlashAttribute("validate", "Create Music Success");
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("music/detail");
        Music music = musicService.findById(id);
        view.addObject("music", music);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("music/edit");
        Music music = musicService.findById(id);
        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setId(music.getId());
        musicDTO.setName(music.getName());
        musicDTO.setSinger(music.getSinger());
        musicDTO.setCategory(music.getCategory());
        view.addObject("src", music.getFile());
        view.addObject("music", musicDTO);
        return view;
    }

    @PostMapping("/edit")
    public String actionEdit(MusicDTO musicDTO, RedirectAttributes redirectAttributes) {
        Music music = musicService.findById(musicDTO.getId());
        music.setId(musicDTO.getId());
        music.setName(musicDTO.getName());
        music.setSinger(musicDTO.getSinger());
        music.setCategory(musicDTO.getCategory());

        MultipartFile multipartFile = musicDTO.getFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
                music.setFile(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        musicService.save(music);
        redirectAttributes.addFlashAttribute("validate", "Edit Music Success");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("music/delete");
        Music music = musicService.findById(id);
        view.addObject("music", music);
        return view;
    }

    @PostMapping("/delete/{id}")
    public String actionDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        musicService.deleteById(id);
        redirectAttributes.addFlashAttribute("validate", "Delete Music Success");
        return "redirect:/";
    }
}
