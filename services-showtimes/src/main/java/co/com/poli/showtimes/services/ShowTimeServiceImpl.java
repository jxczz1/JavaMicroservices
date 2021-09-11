package co.com.poli.showtimes.services;

import co.com.poli.showtimes.entities.ShowTime;
import co.com.poli.showtimes.repositories.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {
    private final ShowTimeRepository showTimeRepository;

    @Override
    public void save(ShowTime showTime) {
        showTimeRepository.save(showTime);
    }

    @Override
    public void delete(ShowTime showTime) {
        showTimeRepository.delete(showTime);
    }

    @Override
    public List<ShowTime> findAll() {
        return showTimeRepository.findAll();
    }

    @Override
    public ShowTime findById(long id) {
        return showTimeRepository.findById(id).orElse(null);
    }

    @Override
    public ShowTime update(ShowTime showTime) {
      return showTimeRepository.save(showTime);
    }
}
