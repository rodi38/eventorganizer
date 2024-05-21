package soft.rodi38.eventorganizer.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import soft.rodi38.eventorganizer.model.dto.EventDTO;
import soft.rodi38.eventorganizer.model.mapper.EventMapper;
import soft.rodi38.eventorganizer.repository.EventRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;

    public List<EventDTO> findAll() {
        return EventMapper.INSTANCE.eventLitToEventDtoList(eventRepository.findAll());
    }
}
