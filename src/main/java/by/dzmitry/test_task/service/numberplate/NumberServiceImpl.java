package by.dzmitry.test_task.service.numberplate;

import by.dzmitry.test_task.dao.numberplate.NumberplateDao;
import by.dzmitry.test_task.exception.CustomException;
import by.dzmitry.test_task.model.numberplate.Numberplate;
import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
import by.dzmitry.test_task.service.util.ModelDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NumberServiceImpl implements NumberplateService {

    @Autowired
    private NumberplateDao numberplateDao;

    @Override
    public void addNumberplate(Integer region, String series, String value) {
        if (region < 0 || region > 7) {
            throw new CustomException("Incorrect region");
        }
        if (series.length() > 2) {
            throw new CustomException("Incorrect series");
        }
        Numberplate numberplate = new Numberplate(region, series, value);
        numberplateDao.save(numberplate);
    }

    @Override
    public void modifyNumberplate(Integer numberplateId, Integer region, String series, String value) {
        Optional<Numberplate> numberplate = numberplateDao.findById(numberplateId);
        if (numberplate.isEmpty()) {
            throw new CustomException("Numberplate not found");
        }
        if (region >= 0 && region < 8) {
            numberplate.get().setRegion(region);
        }
        if (!"none".equals(series) && series.length() == 2) {
            numberplate.get().setSeries(series);
        }
        if (!"none".equals(value)) {
            numberplate.get().setValue(value);
        }
        numberplateDao.save(numberplate.get());
    }

    @Override
    public void deleteNumberplate(Integer numberplateId) {
        Optional<Numberplate> numberplate = numberplateDao.findById(numberplateId);
        if (numberplate.isEmpty()) {
            throw new CustomException("Numberplate not found");
        }
        if(numberplate.get().getCar() !=null){
            throw new CustomException("Can't delete uses numberplate with");
        }
        numberplateDao.deleteById(numberplateId);
    }

    @Override
    public List<NumberplateDto> getAllNumberPlates(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Numberplate> pagedResult = numberplateDao.findAll(paging);
        if (pagedResult.hasContent()) {
            return convertNumberplatesListToDto(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public CarDto getCarByNumberplate(Integer numberplateId) {
        Optional<Numberplate> numberplate = numberplateDao.findById(numberplateId);
        if (numberplate.isEmpty()) {
            throw new CustomException("Numberplate not found");
        }
        return ModelDtoMapper.convertToCarDto(numberplate.get().getCar());
    }

    @Override
    public NumberplateDto getNumberplateById(Integer id) {
        Optional<Numberplate> numberplate = numberplateDao.findById(id);
        if (numberplate.isEmpty()) {
            throw new CustomException("Numberplate not found");
        }
        return ModelDtoMapper.convertToNumberplateDto(numberplate.get());
    }

    private List<NumberplateDto> convertNumberplatesListToDto(List<Numberplate> numberplates) {
        List<NumberplateDto> numberplateDto = new ArrayList<>();
        for (Numberplate numberplate : numberplates) {
            numberplateDto.add(ModelDtoMapper.convertToNumberplateDto(numberplate));
        }
        return numberplateDto;
    }
}
