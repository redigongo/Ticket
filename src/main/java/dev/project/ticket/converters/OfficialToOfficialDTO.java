package dev.project.ticket.converters;

import dev.project.ticket.DTO.OfficialDTO;
import dev.project.ticket.models.Official;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfficialToOfficialDTO implements Converter<Official, OfficialDTO> {

    @Override
    public OfficialDTO convert(Official source) {
        if (source != null) {


            OfficialDTO officialDTO = new OfficialDTO();
            officialDTO.setId(source.getId());
            officialDTO.setName(source.getName());
            officialDTO.setOfficialCode(source.getOfficialCode());
            officialDTO.setBirthDate(source.getBirthDate());
            return officialDTO;
        }
        return null;
    }
}
