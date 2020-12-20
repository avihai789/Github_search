package main.github.repository;

import main.github.model.table.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * This class is an interface that defines all the option to communicate with the database
 */
public interface SearchHistoryRepo extends JpaRepository<SearchHistory, Long> {
    /**
     *
     * @param userName
     * @return
     */
    SearchHistory findByUserName(String userName);

    /**
     *
     * @return
     */
    List<SearchHistory> findByOrderBySearchCountDesc();

}
