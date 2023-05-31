package backend.service;

import backend.model.Music;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MusicServiceIMPL implements IMusicService {
    public static SessionFactory sessionFactory;
    public static EntityManager entityManager;

    static {
        sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Music> findAll() {
        String SELECT_MUSIC_LIST = "select m from Music  m";
        TypedQuery<Music> query = entityManager.createQuery(SELECT_MUSIC_LIST, Music.class);
        return query.getResultList();
    }

    @Override
    public Music findById(Long id) {
        String SELECT_MUSIC_BY_ID = "select m from Music m where m.id=:id";
        TypedQuery<Music> query = entityManager.createQuery(SELECT_MUSIC_BY_ID, Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Music music) {
        Session session;
        Transaction transaction;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        if (music.getId() != null) {
            Music music1 = findById(music.getId());
            music1.setName(music.getName());
            music1.setCategory(music.getCategory());
            music1.setFile(music.getFile());
            session.saveOrUpdate(music1);
        } else session.saveOrUpdate(music);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {
        Session session;
        Transaction transaction;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Music music = findById(id);
        session.delete(music);
        transaction.commit();
        session.close();
    }
}
