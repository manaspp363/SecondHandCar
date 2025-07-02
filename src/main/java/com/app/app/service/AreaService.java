package com.app.app.service;

import com.app.app.entity.Area;
import com.app.app.payload.AreaDto;
import com.app.app.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AreaService {
    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public AreaDto addArea(AreaDto areaDto) {
        Area areaByPinCode = areaRepository.findAreaByPinCode(areaDto.getPinCode());
        if (areaByPinCode == null) {
            Area area = new Area();
            area.setPinCode(areaDto.getPinCode());
            areaRepository.save(area);
        }
        return areaDto;
    }

    public AreaDto deleteArea(AreaDto areaDto) {
        Area areaByPinCode = areaRepository.findAreaByPinCode(areaDto.getPinCode());
        if (areaByPinCode != null) {
            Area area = new Area();
            area.setPinCode(areaDto.getPinCode());
            area.setId(areaByPinCode.getId());
            areaRepository.delete(areaByPinCode);
        }
        return areaDto;
    }

    public AreaDto updateArea(String pinCode, AreaDto areaDto) {
        Area areaByPinCode = areaRepository.findAreaByPinCode(pinCode);
        if (areaByPinCode != null) {
            Area area = new Area();
            area.setPinCode(areaDto.getPinCode());
            area.setId(areaByPinCode.getId());
            areaRepository.save(area);
        }
        AreaDto areaNot = new AreaDto();
        areaNot.setPinCode(pinCode);
        return areaNot;
    }

    public Area getArea(long id) {
        Optional<Area> area = areaRepository.findById(id);
        if (area != null) {
            Area area1 = area.get();
            return area1;
        }
        return null;
    }

    public Iterable<Area> getAllAreas() {
        return areaRepository.findAll();
    }
}
