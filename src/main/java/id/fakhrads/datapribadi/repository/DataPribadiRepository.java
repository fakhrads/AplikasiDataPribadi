package id.fakhrads.datapribadi.repository;

import id.fakhrads.datapribadi.model.DataPribadi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DataPribadiRepository extends CrudRepository<DataPribadi, Long> {
    public Long countByNik(Long nik);

    Optional<DataPribadi> findByNik(Long nik);

    List<DataPribadi> findByNamaLengkapContainingIgnoreCase(String nama);

    Optional<DataPribadi> findByNikAndNamaLengkapContainingIgnoreCase(Long nik, String nama);

}
