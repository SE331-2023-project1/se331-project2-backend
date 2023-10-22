package se331.rest.lab.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.rest.lab.entity.Advisor;
import se331.rest.lab.repository.AdvisorRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")

public class AdvisorDaoImpl {

    final AdvisorRepository advisorRepository;

    @Override
    public Integer getAdvisorSize() {
        return Math.toIntExact(advisorRepository.count());
    }

    @Override
    public Page<Advisor> getAdvisors(Integer pageSize, Integer page) {
        long totalEvents = advisorRepository.count();
        pageSize = pageSize == null ? (int) totalEvents : pageSize;
        page = page == null ? 0 : page - 1;
        return advisorRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Advisor getAdvisor(Long id) {
        return advisorRepository.findById(id).orElse(null);
    }

    @Override
    public Advisor save(Advisor advisor) {
        return advisorRepository.save(advisor);
    }

}
