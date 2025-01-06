package dao.publisher;

import model.Publishers;

import java.util.List;

public interface IPublisherDAO {
    List<Publishers> getAllPublishers();
    boolean addPublisher(Publishers publisher);
    boolean updatePublisher(Publishers publisher);
    boolean deletePublisher(int id);
    Publishers getPublisherById(int id);
}
