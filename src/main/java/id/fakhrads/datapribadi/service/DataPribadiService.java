package id.fakhrads.datapribadi.service;

import id.fakhrads.datapribadi.exception.DataPribadiExecption;
import id.fakhrads.datapribadi.model.DataPribadi;
import id.fakhrads.datapribadi.repository.DataPribadiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class DataPribadiService {
    @Autowired private DataPribadiRepository repo;

    public List<DataPribadi> listAll() {
        List<DataPribadi> dataPribadiList = (List<DataPribadi>) repo.findAll();

        for (DataPribadi data : dataPribadiList) {
            calculateAge(data);
        }
        return dataPribadiList;
    }

    public void save(DataPribadi data) {
        repo.save(data);
    }

    public List<DataPribadi> findByName(String name) {
        return repo.findByNamaLengkapContainingIgnoreCase(name);
    }

    public DataPribadi getNamaNik(Long nik, String name) throws DataPribadiExecption {
        if (nik != null && name != null && !name.isEmpty()) {
            Optional<DataPribadi> result = repo.findByNikAndNamaLengkapContainingIgnoreCase(nik, name);
            if (result.isPresent()) {
                return result.get();
            } else {
                throw new DataPribadiExecption("Data dengan NIK " + nik + " dan nama " + name + " tidak ditemukan.");
            }
        } else {
            throw new DataPribadiExecption("NIK dan nama harus diberikan untuk pencarian.");
        }
    }

    public DataPribadi get(Long nik) throws DataPribadiExecption {
        Optional<DataPribadi> result = repo.findByNik(nik);
        if (result.isPresent()) {
            return result.get();
        }
        throw new DataPribadiExecption("Could not find any users with NIK " + nik);
    }
    public void delete(Long nik) throws DataPribadiExecption {
        Long count = repo.countByNik(nik);
        if (count == null || count == 0) {
            throw new DataPribadiExecption("Could not find any users with NIK " + nik);
        }
        repo.deleteById(nik);
    }

    private void calculateAge(DataPribadi data) {
        LocalDate tanggalLahir = data.getTanggalLahir();
        if (tanggalLahir != null) {
            LocalDate currentDate = LocalDate.now();
            int umur = Period.between(tanggalLahir, currentDate).getYears();
            data.setUmur(umur);
        }
    }
}
