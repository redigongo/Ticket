package dev.project.ticket.services;

import dev.project.ticket.DTO.OfficialDTO;
import dev.project.ticket.converters.OfficialToOfficialDTO;
import dev.project.ticket.repositories.OfficialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfficialService {
    private final OfficialRepository officialRepository;
    private final OfficialToOfficialDTO toOfficialDTO;

    public List<OfficialDTO> findAll(){
        return officialRepository.findAll()
                .stream()
                .map(toOfficialDTO::convert)
                .collect(Collectors.toList());
    }

}
